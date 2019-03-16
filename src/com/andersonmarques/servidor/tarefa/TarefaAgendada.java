package com.andersonmarques.servidor.tarefa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TarefaAgendada implements Runnable {

	@Override
	public void run() {
		System.out.println("Tarefa agendada acontecendo na data e hora: " + getTempoAtual());
	}

	private String getTempoAtual() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
}
