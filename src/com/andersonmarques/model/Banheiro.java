package com.andersonmarques.model;

public class Banheiro {

	public void fazerNumero1() {
		String nome = Thread.currentThread().getName();
		System.out.println(nome + " bateu na porta");

		/**
		 * synchronized -> Apenas um thread por vez, sua execução ocorre de forma
		 * atômica, ela não pode ser interrompida na metade.
		 */
		synchronized (this) {
			usarBanheiro(nome, 5000);
		}
	}

	public void fazerNumero2() {
		String nome = Thread.currentThread().getName();
		System.out.println(nome + " bateu na porta");

		synchronized (this) {
			usarBanheiro(nome, 15000);
		}
	}

	private void usarBanheiro(String nome, long tempoEstimado) {
		System.out.println(nome + " entrando no banheiro");
		System.out.println(nome + " fazendo coisa rapida");

		try {
			Thread.sleep(tempoEstimado);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(nome + " dando descarga");
		System.out.println(nome + " lavando a mao");
		System.out.println(nome + " saindo do banheiro");
	}
}
