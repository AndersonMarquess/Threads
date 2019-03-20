package com.andersonmarques.servidor.tarefa;

import java.io.PrintStream;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Como o future.get() trava a thread até receber a resposta, é recomendado
 * fazer o tratamento do future em uma outra thread.
 * 
 * @author Anderson
 *
 */
public class TrataResultadosFuture implements Runnable {

	private Future<String> respostaWS;
	private Future<String> respostaDB;
	private PrintStream printStream;

	public TrataResultadosFuture(Future<String> respostaWS, Future<String> respostaDB, PrintStream printStream) {
		this.respostaWS = respostaWS;
		this.respostaDB = respostaDB;
		this.printStream = printStream;
	}

	@Override
	public void run() {
		System.out.println("Processando resposta do Future WB e DB");
		try {
			/* Get com timeout para espera da resposta */
			String resultadoWS = respostaWS.get(15, TimeUnit.SECONDS);
			String resultadoDB = respostaDB.get(15, TimeUnit.SECONDS);
			printStream.println(String.format("Resultado\n	WS: %s\n	DB: %s", resultadoWS, resultadoDB));
		} catch (Exception e) {
			printStream.println("Timeout - Comando C2");

			/* Interrompe a chamada do future */
			respostaWS.cancel(true);
			respostaDB.cancel(true);
		}
	}
}
