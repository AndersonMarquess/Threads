package com.andersonmarques.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.andersonmarques.servidor.tarefa.DistribuirTarefa;

public class ServidorMain {

	public static void main(String[] args) throws Exception {
		System.out.println("---Subindo Servidor na porta 54321---");

		/**
		 * Socket - Canal de comunicação entre duas maquinas, ServerSocket (Servidor) e
		 * Socket (Cliente).
		 */
		ServerSocket serverSocket = new ServerSocket(54321);

		/**
		 * Define o tamanho maximo do pool de threads -> newFixedThreadPool Define o
		 * tamanho de pool aumentando e diminuindo de forma automática ->
		 * newCachedThreadPool
		 */
		ExecutorService threadPool = Executors.newCachedThreadPool();

		while (true) {
			Socket socketRequisicaoClient = serverSocket.accept();
			System.out.println("Cliente recebido na porta: " + socketRequisicaoClient.getPort());

			DistribuirTarefa distribuirTarefa = new DistribuirTarefa(socketRequisicaoClient);
			threadPool.execute(distribuirTarefa);
		}
	}
}
