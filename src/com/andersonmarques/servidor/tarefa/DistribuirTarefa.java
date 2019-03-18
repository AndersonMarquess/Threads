package com.andersonmarques.servidor.tarefa;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

import com.andersonmarques.servidor.service.ServidorService;
import com.andersonmarques.servidor.tarefa.comando.ComandoC1;
import com.andersonmarques.servidor.tarefa.comando.ComandoC2;

public class DistribuirTarefa implements Runnable {

	private Socket socketRequisicaoClient;
	private ServidorService servidorService;
	private ExecutorService threadPool;

	public DistribuirTarefa(ExecutorService threadPool, Socket socketRequisicaoClient, ServidorService servidorService) {
		this.threadPool = threadPool;
		this.socketRequisicaoClient = socketRequisicaoClient;
		this.servidorService = servidorService;
	}

	@Override
	public void run() {
		System.out.println("Distribuindo tarefa para socket: " + socketRequisicaoClient);
		dadosRecebidosDoCliente();
	}

	/**
	 * Imprime os dados recebido do cliente
	 */
	private void dadosRecebidosDoCliente() {
		try {
			Scanner scanner = new Scanner(socketRequisicaoClient.getInputStream());
			while (scanner.hasNextLine()) {
				PrintStream printStream = new PrintStream(socketRequisicaoClient.getOutputStream());
				executarComando(scanner.nextLine(), printStream);
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void executarComando(String comando, PrintStream printStream) throws IOException {
		System.out.println("Recebeu " + comando);

		switch (comando.toLowerCase()) {
			case "c1":
				printStream.println("Confirmação comando c1");
				threadPool.execute(new ComandoC1(printStream));
				break;
			case "c2":
				printStream.println("Confirmação comando c2");
				threadPool.execute(new ComandoC2(printStream));
				break;
			case "fim":
				printStream.println("Desligando servidor");
				servidorService.desligar();
				break;
			default:
				printStream.println("Comando inválido.");
				break;
		}
	}
}
