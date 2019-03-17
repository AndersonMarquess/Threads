package com.andersonmarques.servidor;

import java.net.Socket;

import com.andersonmarques.servidor.tarefa.EnviarInformacoesParaSocket;
import com.andersonmarques.servidor.tarefa.ImprimirResposta;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		/*
		 * A porta especificada é usada só no primeiro contato com o servidor, em seguida
		 * uma nova porta é gerada para o socket.
		 */
		Socket socketRespostaServidor = new Socket("localhost", 54321);
		System.out.println("Conectado na porta: "+socketRespostaServidor.getLocalPort());

		Thread threadEnviar = new Thread(new EnviarInformacoesParaSocket(socketRespostaServidor));
		new Thread(new ImprimirResposta(socketRespostaServidor)).start();
		threadEnviar.start();
		
		/* A thread main só continua após a thread de enviar informações ser finalizada. */
		threadEnviar.join();
		
		socketRespostaServidor.close();
	}
}
