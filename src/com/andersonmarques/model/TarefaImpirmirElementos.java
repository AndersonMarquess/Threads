package com.andersonmarques.model;

public class TarefaImpirmirElementos implements Runnable {

	private Lista lista;

	public TarefaImpirmirElementos(Lista lista) {
		this.lista = lista;
	}

	@Override
	public void run() {
		//Lista como chave (mutex), espera o notify
		synchronized (lista) {
			//Espera o notify da lista
			try {
				System.out.println("Esperando notificação da lista para imprimir");
				lista.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < lista.tamanho(); i++) {
				System.out.println(i + " - " + lista.pegarElementoNaPosicao(i));
			}
		}
	}
}
