package com.andersonmarques.servidor.tarefa;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EnviarInformacoesParaSocket implements Runnable {

	private Socket socketRespostaSocket;

	public EnviarInformacoesParaSocket(Socket socketRespostaSocket) {
		this.socketRespostaSocket = socketRespostaSocket;
	}

	@Override
	public void run() {
		try {
			PrintStream saida = new PrintStream(socketRespostaSocket.getOutputStream());
			lerInputTeclado(saida);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recebe dados do teclado e imprime dentro de um {@PrintStream}
	 * 
	 * @param saida
	 */
	private void lerInputTeclado(PrintStream saida) {
		System.out.println("[===Dados enviado===]");
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNextLine()) {
			String linha = scanner.nextLine();

			/* Para a leitura se for enviado uma string vazia. */
			if (linha.trim().isEmpty()) {
				scanner.close();
				saida.close();
				break;
			}
			saida.println(linha);
		}
	}
}
