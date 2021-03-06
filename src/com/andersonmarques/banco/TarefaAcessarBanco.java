package com.andersonmarques.banco;

public class TarefaAcessarBanco implements Runnable {

	private PoolDeConexao pool;
	private GerenciadorDeTransacao tx;

	public TarefaAcessarBanco(PoolDeConexao pool, GerenciadorDeTransacao tx) {
		this.pool = pool;
		this.tx = tx;
	}

	@Override
	public void run() {
		synchronized (pool) {
			System.out.println("Sincronizado com o POOL");
			pool.getConnection();
			
			synchronized (tx) {
				System.out.println("Sincronizado com a TX");
				tx.begin();
			}
		}
	}
}
