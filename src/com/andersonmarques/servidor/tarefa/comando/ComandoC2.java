package com.andersonmarques.servidor.tarefa.comando;

import java.io.PrintStream;

public class ComandoC2 implements Runnable {

	// Saida do cliente
	private PrintStream printStream;

	public ComandoC2(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void run() {
		System.out.println("Executando comando c2");
		try {
			Thread.sleep(3000);
			throw new RuntimeException("Forçou exception");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Fim do comando c2");
		printStream.println("Comando c2 executado com sucesso!");
	}
}
