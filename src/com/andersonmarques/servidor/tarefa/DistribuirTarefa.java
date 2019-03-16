package com.andersonmarques.servidor.tarefa;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DistribuirTarefa implements Runnable {

	private Socket socketRequisicaoClient;

	public DistribuirTarefa(Socket socketRequisicaoClient) {
		this.socketRequisicaoClient = socketRequisicaoClient;
	}

	@Override
	public void run() {
		System.out.println("Distribuindo tarefa socket: " + socketRequisicaoClient);
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
			while(scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
