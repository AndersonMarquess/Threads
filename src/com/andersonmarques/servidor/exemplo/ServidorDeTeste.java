package com.andersonmarques.servidor.exemplo;

import com.andersonmarques.servidor.exception.TratadorDeException;

public class ServidorDeTeste {

	/* Compartilha o valor entre as threads, envitando o cache. */
	private volatile boolean estaRodando = false;

	public static void main(String[] args) throws InterruptedException {
		ServidorDeTeste servidor = new ServidorDeTeste();
		servidor.rodar();
		servidor.alterandoAtributo();
	}

	private void rodar() {
		Thread thread01 = new Thread(() -> {

			System.out.println("Servidor começando, estaRodando = " + estaRodando);

			while (!estaRodando) {}

			if (estaRodando) {
				throw new RuntimeException("Exception na thread "+Thread.currentThread().getName());
			}

			System.out.println("Servidor rodando, estaRodando = " + estaRodando);

			while (estaRodando) {}

			System.out.println("Servidor terminando, estaRodando = " + estaRodando);
		});
		
		/* Trata exceções da thread */
		thread01.setUncaughtExceptionHandler(new TratadorDeException());
		thread01.start();
	}

	private void alterandoAtributo() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Main alterando estaRodando ->> true");
		estaRodando = true;

		Thread.sleep(3000);
		System.out.println("Main alterando estaRodando ->> false");
		estaRodando = false;
	}
}
