package com.andersonmarques.model;

public class Lista {
	private String[] elementos = new String[1000];
	private int index;

	// synchronized mesma coisa que usar o synchronized(this) {}
	public synchronized void addElemento(String elemento) {
		elementos[index] = elemento;
		index++;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (elementos.length == index) {
			System.out.println("Notificando thread para prosseguir com impressão");
			this.notify();
		}
	}

	public int tamanho() {
		return index;
	}

	public String pegarElementoNaPosicao(int posicao) {
		return elementos[posicao];
	}

	public boolean isCheia() {
		return elementos.length == index;
	}
}
