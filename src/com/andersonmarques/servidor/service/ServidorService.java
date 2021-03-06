package com.andersonmarques.servidor.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import com.andersonmarques.servidor.tarefa.DistribuirTarefa;

public class ServidorService {

	private ServerSocket serverSocket;
	private ExecutorService threadPool;
	private BlockingQueue<String> filaComandos;
	/**
	 * Volatile ou Atomic -> Atributo que pode ser alterado em todas as threads, seu
	 * valor real é compartilhado entre elas.
	 */
	private AtomicBoolean isServidorLigado;

	public ServidorService(ServerSocket serverSocket, ExecutorService threadPool) {
		this.serverSocket = serverSocket;
		this.threadPool = threadPool;
		this.isServidorLigado = new AtomicBoolean(true);
		this.filaComandos = new ArrayBlockingQueue<>(2);
	}

	/**
	 * Distribui tarefas a todos os clientes conectados
	 * 
	 * @throws IOException
	 */
	public void distribuirTarefas() throws IOException {
		while (isServidorLigado.get()) {
			try {
				Socket socketRequisicaoClient = serverSocket.accept();

				DistribuirTarefa distribuirTarefa = new DistribuirTarefa(threadPool, socketRequisicaoClient, filaComandos, this);
				threadPool.execute(distribuirTarefa);
			} catch (SocketException e) {
				System.out.println("SocketException -> status servidor: " + isServidorLigado.get());
			}
		}
	}

	/**
	 * Fecha as conexões
	 * 
	 * @throws IOException
	 */
	public void desligar() throws IOException {
		isServidorLigado.set(false);
		threadPool.shutdown();
		serverSocket.close();
	}
}
