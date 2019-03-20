package com.andersonmarques.servidor.tarefa.comando;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Implementação que permite devolver objetos.
 * 
 * @author Anderson
 *
 */
public class ComandoC2ChamaWebService implements Callable<String> {

	/* Equivalente ao Run() mas com o tipo de retorno especificado no Callable */
	@Override
	public String call() throws Exception {
		System.out.println("Executando comando c2 - WebService");
		Thread.sleep(20000);
		System.out.println("C2 - WebService executado com sucesso");

		int numeroAleatorio = new Random().nextInt(50);

		return String.format("Fim chamada WebService %d", numeroAleatorio);
	}
}
