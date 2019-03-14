package com.andersonmarques.model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Banheiro {

	private boolean isBanheiroSujo = true;
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

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

		if (isBanheiroSujo) {
			esperarLimpeza(nome);
		}

		System.out.println(nome + " fazendo coisa " + tempoDescrito);

		try {
			Thread.sleep(tempoEstimado);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(nome + " dando descarga");
		System.out.println(nome + " lavando a mao");
		System.out.println(nome + " saindo do banheiro");
	}

	private void esperarLimpeza(String nome) {
		System.out.println(nome + " o banheiro está sujo.");
		try {
			/* faz o thread esperar */
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void limpar() {
		String nome = Thread.currentThread().getName();
		System.out.println(nome + " bateu na porta");
		System.out.println(nome + " entrando no banheiro");

		lock.lock();

		if (!isBanheiroSujo) {
			return;
		}

		System.out.println("Limpando banheiro");

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		isBanheiroSujo = false;

		/* Notifica a thread no estado WAIT para continuar */
		//condition.signal();
		/* Notifica todas as threads */
		condition.signalAll();

		// this.notify();
		// this.notifyAll();

		System.out.println("Banheiro limpo");
		lock.unlock();
	}
}
