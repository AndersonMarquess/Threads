package com.andersonmarques.servidor.exemplo;

public class ServidorDeTeste {

	/* Compartilha o valor entre as threads, envitando o cache. */
	private volatile boolean estaRodando = false;

	public static void main(String[] args) throws InterruptedException {
		ServidorDeTeste servidor = new ServidorDeTeste();
		servidor.rodar();
		servidor.alterandoAtributo();
	}

	private void rodar() {
		new Thread(() -> {

			System.out.println("Servidor começando, estaRodando = " + estaRodando);

			while (!estaRodando) {}

			System.out.println("Servidor rodando, estaRodando = " + estaRodando);

			while (estaRodando) {}

			System.out.println("Servidor terminando, estaRodando = " + estaRodando);
		}).start();
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