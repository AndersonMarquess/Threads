package com.andersonmarques;

import com.andersonmarques.model.OlaThread;

public class Main {

	public static void main(String[] args) {
		
		new Thread(new OlaThread(), "Thread Exercício").start();
	}

}
