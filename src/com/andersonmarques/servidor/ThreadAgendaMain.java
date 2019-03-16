package com.andersonmarques.servidor;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.andersonmarques.servidor.tarefa.TarefaAgendada;

/**
 * Executa um runnable no intervalo de tempo escolhido.
 * 
 * @author Anderson
 *
 */
public class ThreadAgendaMain {

	public static void main(String[] args) {
		System.out.println("Agenda de tarefa iniciada");

		ScheduledExecutorService scheduledService = threadAgendada();
		Scanner sc = new Scanner(System.in);

		sc.nextLine();
		sc.close();
		scheduledService.shutdown();
		System.out.println("Agenda de tarefa finalizada");
	}

	/**
	 * Agenda a thread para executar a tarefa a cada 5 segundos, só interrompe ao
	 * chamar o método de desligar.
	 * 
	 * @return {@link ScheduledExecutorService}
	 */
	private static ScheduledExecutorService threadAgendada() {
		ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(1);

		scheduledService.scheduleAtFixedRate(new TarefaAgendada(), 0, 5, TimeUnit.SECONDS);
		return scheduledService;
	}
}
