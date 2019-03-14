package com.andersonmarques.model;

import java.util.List;

public class TarefaAdicionarElemento implements Runnable {

	private int numeroThread;
	private List<String> lista;

	public TarefaAdicionarElemento(List<String> lista, int numeroThread) {
		this.lista = lista;
		this.numeroThread = numeroThread;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			lista.add(String.format("Thread %d - elemento %d", numeroThread, i));
		}
	}
}
