package com.andersonmarques;

import com.andersonmarques.model.Lista;
import com.andersonmarques.model.TarefaAdicionarElemento;

public class ListaMain {

	public static void main(String[] args) throws InterruptedException {
		Lista lista = new Lista();
		
		for (int i = 0; i < 10; i++) {
			new Thread(new TarefaAdicionarElemento(lista, i)).start();
		}
		
		Thread.sleep(1000);
		
		for (int i = 0; i < lista.tamanho(); i++) {
			System.out.println(i+" - "+lista.pegarElementoNaPosicao(i));
		}
	}
}
