package com.andersonmarques.banco;

public class TarefaAcessarBancoProcedimentoDeadlock implements Runnable {
	private PoolDeConexao pool;
	private GerenciadorDeTransacao tx;

	public TarefaAcessarBancoProcedimentoDeadlock(PoolDeConexao pool, GerenciadorDeTransacao tx) {
		this.pool = pool;
		this.tx = tx;
	}

	@Override
	public void run() {
		synchronized (tx) {
			System.out.println("Sincronizado com a TX");
			tx.begin();

			synchronized (pool) {
				System.out.println("Sincronizado com o POOL");
				pool.getConnection();
			}
		}
	}
}
