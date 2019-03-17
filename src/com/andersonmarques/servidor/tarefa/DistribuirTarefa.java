package com.andersonmarques.servidor.tarefa;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import com.andersonmarques.servidor.service.ServidorService;

public class DistribuirTarefa implements Runnable {

	private Socket socketRequisicaoClient;
	private ServidorService servidorService;

	public DistribuirTarefa(Socket socketRequisicaoClient, ServidorService servidorService) {
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
				String resposta = verificarComando(scanner.nextLine());
				printStream.println(resposta);
				
				if(resposta.equalsIgnoreCase("Desligando servidor")) {
					servidorService.desligar();
				}
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String verificarComando(String comando) {
		System.out.println("Recebeu " + comando);

		switch (comando.toLowerCase()) {
			case "c1":
				return "Confirmação comando c1";
			case "c2":
				return "Confirmação comando c2";
			case "fim":
				return "Desligando servidor";
			default:
				return "Comando inválido.";
		}
	}
}
