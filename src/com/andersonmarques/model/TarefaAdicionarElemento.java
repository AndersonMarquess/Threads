package com.andersonmarques.model;

public class TarefaAdicionarElemento implements Runnable {

	private int numeroThread;
	private Lista lista;

	public TarefaAdicionarElemento(Lista lista, int numeroThread) {
		this.lista = lista;
		this.numeroThread = numeroThread;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			lista.addElemento(String.format("Thread %d - elemento %d", numeroThread, i));
		}
	}
}
