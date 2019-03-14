package com.andersonmarques;

import com.andersonmarques.model.Banheiro;

public class BanheiroMain {

	public static void main(String[] args) {
		Banheiro banheiro = new Banheiro();

		new Thread(() -> banheiro.fazerNumero1(), "João").start();
		new Thread(() -> banheiro.fazerNumero2(), "Pedro").start();
		Thread limpeza = new Thread(() -> verificarEstadoBanheiro(banheiro), "Limpeza");
		
		/*Para a thread caso não exista nenhuma outra thread principal rodando*/
		limpeza.setDaemon(true);
		limpeza.start();
	}

	private static void verificarEstadoBanheiro(Banheiro banheiro) {
		while (true) {
			banheiro.limpar();
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
