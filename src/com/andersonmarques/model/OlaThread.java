package com.andersonmarques.model;

/**
 * � poss�vel usar a implementa��o em uma classe de forma direta ou usar uma
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
		System.out.println("Ol� thread");
	}

}
