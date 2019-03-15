package com.andersonmarques.servidor;

import java.net.Socket;

public class ClientMain {
	
	public static void main(String[] args) throws Exception {
		Socket socketRespostaServidor = new Socket("localhost", 54321);
		System.out.println("---Conectado com o servidor na porta 54321---");
		
		socketRespostaServidor.close();
	}
}
