package com.andersonmarques.servidor.exemplo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TesteFila {

	public static void main(String[] args) throws Exception {
		BlockingQueue<String> fila = new ArrayBlockingQueue<>(3);
		
		/* put adiciona um elemento, se não houver mais espaço ela trava a thread até adicionar*/
		fila.put("string 1");
		fila.put("string 2");
		fila.put("string 3");
		
		System.out.println("Tamanho inicial "+fila.size());
		
		new Thread(() -> {
			try {
				Thread.sleep(5000);
				System.out.println(fila.take());
				System.out.println("Liberou espaço");
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Tamanho restante "+fila.remainingCapacity());
		}).start();
		
		System.out.println("Esperando novos elementos");
		fila.put("Elemento extra da mistura");

		System.out.println("Tamnho final "+fila.size());
	}
}
