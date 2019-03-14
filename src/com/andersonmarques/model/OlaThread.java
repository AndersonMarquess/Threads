package com.andersonmarques.model;

/**
 * É possível usar a implementação em uma classe de forma direta ou usar uma
 * lambda.
 * 
 * @author Anderson
 *
 */
public class OlaThread implements Runnable {

	@Override
	public void run() {
		imprimirString();
	}
	
	public void imprimirString() {
		System.out.println("Olá thread");
	}

}
