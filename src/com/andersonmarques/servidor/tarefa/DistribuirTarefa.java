package com.andersonmarques.servidor.tarefa;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.andersonmarques.servidor.service.ServidorService;
import com.andersonmarques.servidor.tarefa.comando.ComandoC1;
import com.andersonmarques.servidor.tarefa.comando.ComandoC2AcessaBanco;
import com.andersonmarques.servidor.tarefa.comando.ComandoC2ChamaWebService;

public class DistribuirTarefa implements Runnable {

	private Socket socketRequisicaoClient;
	private ServidorService servidorService;
	private ExecutorService threadPool;
	private BlockingQueue<String> filaComandos;

	public DistribuirTarefa(ExecutorService threadPool, Socket socketRequisicaoClient,
			BlockingQueue<String> filaComandos, ServidorService servidorService) {
		this.threadPool = threadPool;
		this.socketRequisicaoClient = socketRequisicaoClient;
		this.filaComandos = filaComandos;
		this.servidorService = servidorService;
	}

	@Override
	public void run() {
		System.out.println("Distribuindo tarefa para socket: " + socketRequisicaoClient);
		dadosRecebidosDoCliente();
	}

	/**
	 * Imprime os dados recebido do cliente
	 */
	private void dadosRecebidosDoCliente() {
		try {
			Scanner scanner = new Scanner(socketRequisicaoClient.getInputStream());
			while (scanner.hasNextLine()) {
				PrintStream printStream = new PrintStream(socketRequisicaoClient.getOutputStream());
				executarComando(scanner.nextLine(), printStream);
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void executarComando(String comando, PrintStream printStream) throws Exception {
		System.out.println("Recebeu " + comando);

		switch (comando.toLowerCase()) {
			case "c1":
				printStream.println("Confirmação comando c1");
				threadPool.execute(new ComandoC1(printStream));
				break;
				
			case "c2":
				printStream.println("Confirmação comando c2");
				tarefaComRetorno(printStream);
				break;
				
			case "c3":
				printStream.println("Confirmação comando c3");
				processoEmFila(comando, printStream);
				break;
				
			case "fim":
				printStream.println("Desligando servidor");
				servidorService.desligar();
				break;
				
			default:
				printStream.println("Comando inválido.");
				break;
		}
	}

	private void tarefaComRetorno(PrintStream printStream) {
		/* submit equivalente ao execute para Runnable */
		Future<String> respostaWS = threadPool.submit(new ComandoC2ChamaWebService());
		Future<String> respostaDB = threadPool.submit(new ComandoC2AcessaBanco());
		threadPool.execute(new TrataResultadosFuture(respostaWS, respostaDB, printStream));
	}

	private void processoEmFila(String comando, PrintStream printStream) throws InterruptedException {
		filaComandos.put(comando);
		printStream.println("Comando c3 adicionado na fila "+Thread.currentThread().getName());
		threadPool.execute(new ConsumirComandos(filaComandos, printStream));
		threadPool.execute(new ConsumirComandos(filaComandos, printStream));
	}
}
