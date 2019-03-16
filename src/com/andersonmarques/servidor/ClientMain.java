package com.andersonmarques.servidor;

import java.io.OutputStream;
import java.io.PrintStream;
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

		enviarInfo(socketRespostaServidor.getOutputStream());

		socketRespostaServidor.close();
	}

	/**
	 * Envia informação para o servidor.
	 * 
	 * @param outputStream
	 * @throws Exception
	 */
	private static void enviarInfo(OutputStream outputStream) throws Exception {
		PrintStream saida = new PrintStream(outputStream);
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNextLine()) {
			String linha = scanner.nextLine();

			if (linha.trim().isEmpty()) {
				scanner.close();
				saida.close();
				break;
			}
			saida.println(linha);
		}
	}
}
