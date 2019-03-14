package com.andersonmarques.model;

import java.util.concurrent.locks.ReentrantLock;

public class Banheiro {
	
	private ReentrantLock lock = new ReentrantLock();

	public void fazerNumero1() {
		String nome = Thread.currentThread().getName();
		System.out.println(nome + " bateu na porta");
		
		/** lock trava o acesso de outras threads */
		lock.lock();
		
		usarBanheiro(nome, 5000, "rápida");
		
		/** libera o acesso. */
		lock.unlock();
	}

	public void fazerNumero2() {
		String nome = Thread.currentThread().getName();
		System.out.println(nome + " bateu na porta");

		lock.lock();
		
		usarBanheiro(nome, 15000, "demorada");

		lock.unlock();
	}

	private void usarBanheiro(String nome, long tempoEstimado, String tempoDescrito) {
		System.out.println(nome + " entrando no banheiro");
		System.out.println(nome + " fazendo coisa "+tempoDescrito);

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
