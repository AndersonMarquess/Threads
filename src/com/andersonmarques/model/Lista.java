package com.andersonmarques.model;

public class Lista {
	private String[] elementos = new String[1000];
	private int index;
	
	//synchronized mesma coisa que usar o synchronized(this) {}
	public synchronized void addElemento(String elemento) {
		elementos[index] = elemento;
		index++;
	}
	
	public int tamanho() {
		return index;
	}
	
	public String pegarElementoNaPosicao(int posicao) {
		return elementos[posicao];
	}
}
