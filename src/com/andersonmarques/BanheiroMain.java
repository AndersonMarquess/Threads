package com.andersonmarques;

import com.andersonmarques.model.Banheiro;

public class BanheiroMain {
	
	public static void main(String[] args) {
		Banheiro banheiro = new Banheiro();
		
		new Thread(() -> banheiro.fazerNumero1(), "João").start();
		new Thread(() -> banheiro.fazerNumero2(), "Pedro").start();
	}
}
