package com.andersonmarques.servidor.tarefa;

import java.net.Socket;

public class DistribuirTarefa implements Runnable {

	private Socket socketRequisicaoClient;

	public DistribuirTarefa(Socket socketRequisicaoClient) {
		this.socketRequisicaoClient = socketRequisicaoClient;
	}

	@Override
	public void run() {
		System.out.println("Distribuindo tarefa socket: " + socketRequisicaoClient);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
