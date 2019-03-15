package com.andersonmarques.arquivo;

public class Main {

	public static void main(String[] args) {
		new Thread(new OlaThread(), "Thread Exercício").start();
		buscarNomeAutores();
	}

	private static void buscarNomeAutores() {
		String assinatura1 = "./data/assinaturas1.txt";
		String assinatura2 = "./data/assinaturas2.txt";
		String autores = "./data/autores.txt";
		String nome = "a";

		// Novo thread
		new Thread(() -> new BuscarNome(assinatura1, nome).buscar()).start();

		new Thread(() -> new BuscarNome(assinatura2, nome).buscar()).start();

		new Thread(() -> new BuscarNome(autores, nome).buscar()).start();
	}
}
