package com.andersonmarques.banco;

public class BancoMain {

	public static void main(String[] args) {
		GerenciadorDeTransacao tx = new GerenciadorDeTransacao();
		PoolDeConexao pool = new PoolDeConexao();

		new Thread(new TarefaAcessarBanco(pool, tx)).start();
		new Thread(new TarefaAcessarBancoProcedimento(pool, tx)).start();

		/* Evite deadlock usando a mesma ordem para sincronizar */
		new Thread(new TarefaAcessarBancoProcedimentoDeadlock(pool, tx)).start();
	}
}
