package com.andersonmarques.servidor.factory;

import java.util.concurrent.ThreadFactory;

import com.andersonmarques.servidor.exception.TratadorDeException;

/**
 * Permite alterar características da thread que será criada.
 * 
 * @author Anderson
 *
 */
public class FabricaThreads implements ThreadFactory {

	private static int numero;

	@Override
	public Thread newThread(Runnable r) {
		System.out.println("Fabrica - Criando thread");
		Thread thread = new Thread(r, "Thread manual factory " + numero++);
		thread.setUncaughtExceptionHandler(new TratadorDeException());
		return thread;
	}
}
