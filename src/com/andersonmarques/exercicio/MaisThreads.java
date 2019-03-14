package com.andersonmarques.exercicio;

public class MaisThreads {

	public static void main(String[] args) {
		new Thread(() -> imprimirDadosThread(Thread.currentThread()), "THR N1").start();
		new Thread(() -> imprimirDadosThread(Thread.currentThread()), "THR N1++").start();
	}

	private static void imprimirDadosThread(Thread t) {
		for (int i = 1; i <= 1000; i++) {
			System.out.println(String.format("%d - %s - %d", i, t.getName(), t.getId()));
		}
	}
}
