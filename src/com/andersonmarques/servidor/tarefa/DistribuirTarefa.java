package com.andersonmarques.servidor.tarefa;

import java.net.Socket;

public class DistribuirTarefa implements Runnable {

	private Socket socketRequisicaoClient;

	public DistribuirTarefa(Socket socketRequisicaoClient) {
		this.socketRequisicaoClient = socketRequisicaoClient;
	}

	@Override
	public void run() {
		System.out.println("Distribuindo tarefa para socket: " + socketRequisicaoClient);
		dadosRecebidosDoCliente();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Imprime os dados recebido do cliente
	 */
	private void dadosRecebidosDoCliente() {
		new Thread(new ImprimirResposta(socketRequisicaoClient)).start();
	}
}
