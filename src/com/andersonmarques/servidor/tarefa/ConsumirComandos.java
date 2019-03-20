package com.andersonmarques.servidor.tarefa;

import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;

public class ConsumirComandos implements Runnable {

	private BlockingQueue<String> filaComandos;
	private PrintStream printStream;

	public ConsumirComandos(BlockingQueue<String> filaComandos, PrintStream printStream) {
		this.filaComandos = filaComandos;
		this.printStream = printStream;
	}

	@Override
	public void run() {
		try {
			String resultado;
			while ((resultado = filaComandos.take()) != null) {
				printInfoProcesso(resultado);
				Thread.sleep(20000);
				printStream.println("Comando " + resultado + " Concluido.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void printInfoProcesso(String resultado) {
		System.out.println(String.format("%s - executando processo para comando: %s",
				Thread.currentThread().getName(), resultado));
	}
}
