package com.andersonmarques.servidor.exception;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Trata exceções das threads especificadas.
 * 
 * @author Anderson
 *
 */
public class TratadorDeException implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Ocorreceu uma exceção na thread: "+t.getName()+" - "+e.getMessage());
	}

}
