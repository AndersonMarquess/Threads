package com.andersonmarques.servidor;

import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		/*
		 * A porta especifica é usada só no primeiro contato com o servidor, em seguida
		 * uma nova porta é gerada para o socket.
		 */
		Socket socketRespostaServidor = new Socket("localhost", 54321);
		System.out.println("Conectado na porta 54321");

		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();

		socketRespostaServidor.close();
	}
}
