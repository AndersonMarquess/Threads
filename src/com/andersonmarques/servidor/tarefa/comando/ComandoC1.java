package com.andersonmarques.servidor.tarefa.comando;

import java.io.PrintStream;

public class ComandoC1 implements Runnable {

	// Saida do cliente
	private PrintStream printStream;

	public ComandoC1(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void run() {
		System.out.println("Executando comando c1");
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fim do comando c1");
		printStream.println("Comando c1 executado com sucesso!");
	}
}
