package com.andersonmarques.servidor.tarefa.comando;

import java.util.concurrent.Callable;

/**
 * Implementação que permite devolver objetos.
 * 
 * @author Anderson
 *
 */
public class ComandoC2AcessaBanco implements Callable<String> {

	/* Equivalente ao Run() mas com o tipo especificado no Callable */
	@Override
	public String call() throws Exception {
		System.out.println("Acessando banco C2");
		Thread.sleep(20000);
		System.out.println("Acesso a banco realizado com sucesso");

		return "Fim acesso ao banco.";
	}
}
