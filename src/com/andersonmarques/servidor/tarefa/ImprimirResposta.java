package com.andersonmarques.servidor.tarefa;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ImprimirResposta implements Runnable {

	private Socket socketResposta;

	public ImprimirResposta(Socket socketResposta) {
		this.socketResposta = socketResposta;
	}

	/**
	 * Imprime os dados recebidos pelo socket
	 */
	@Override
	public void run() {
		try {
			System.out.println("===[Dados recebidos]===");
			Scanner scanner = new Scanner(socketResposta.getInputStream());
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
