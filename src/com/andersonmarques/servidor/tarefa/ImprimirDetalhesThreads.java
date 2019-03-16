package com.andersonmarques.servidor.tarefa;

import java.util.Set;

public class ImprimirDetalhesThreads implements Runnable {

	@Override
	public void run() {
		Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();

		for (Thread thread : todasAsThreads) {
			System.out.println("Nome thread: " + thread.getName());
		}
		System.out.println("======");
		printQtdProcessadoresDisponiveis();
		System.out.println("======");
	}

	private void printQtdProcessadoresDisponiveis() {
		Runtime runtime = Runtime.getRuntime();
		int qtdProcessadores = runtime.availableProcessors();
		long qtdMemoriaMaxima = runtime.maxMemory();
		long qtdMemoriaUso = runtime.totalMemory();
		long qtdMemoriaLivre = runtime.freeMemory();
		System.out.printf(
				"Quantidade de processadores: %d\nMáximo de memória para uso: %d MB\nMemória na JVM: %d MB\n"
						+ "Memória livre na JVM: %d MB\n",
				qtdProcessadores, converterBytesParaMB(qtdMemoriaMaxima), converterBytesParaMB(qtdMemoriaUso),
				converterBytesParaMB(qtdMemoriaLivre));
	}

	private long converterBytesParaMB(long bytes) {
		return bytes / 1000 / 1000;
	}
}
