package com.andersonmarques.servidor.tarefa;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistribuirTarefa implements Runnable {

	private Socket socketRequisicaoClient;

	public DistribuirTarefa(Socket socketRequisicaoClient) {
		this.socketRequisicaoClient = socketRequisicaoClient;
	}

	@Override
	public void run() {
		System.out.println("Distribuindo tarefa para socket: " + socketRequisicaoClient);
		dadosRecebidosDoCliente();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Imprime os dados recebido do cliente
	 */
	private void dadosRecebidosDoCliente() {
		try {
			Scanner scanner = new Scanner(socketRequisicaoClient.getInputStream());
			while (scanner.hasNextLine()) {
				PrintStream printStream = new PrintStream(socketRequisicaoClient.getOutputStream());
				printStream.println(verificarComando(scanner.nextLine()));
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String verificarComando(String comando) {
		System.out.println("Recebeu " + comando);

		switch (comando) {
			case "c1":
				return "Confirmação comando c1";
			case "c2":
				return "Confirmação comando c2";
			default:
				return "Comando inválido.";
		}
	}
}
