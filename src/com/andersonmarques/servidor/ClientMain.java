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
		
		enviarInfo(socketRespostaServidor);

		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		scanner.close();
		socketRespostaServidor.close();
	}
	
	/**
	 * Envia informação para o servidor.
	 * @param socketRespostaServidor
	 * @throws Exception
	 */
	private static void enviarInfo(Socket socketRespostaServidor) throws Exception {
		OutputStream outputStream = socketRespostaServidor.getOutputStream();
		PrintStream saida = new PrintStream(outputStream);
		saida.println("Cliente 1");
	}
}
