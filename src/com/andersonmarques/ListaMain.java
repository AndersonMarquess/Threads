package com.andersonmarques;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.andersonmarques.model.TarefaAdicionarElemento;

public class ListaMain {

	public static void main(String[] args) throws InterruptedException {
		
		/*Não é thread safe*/
		List<String> listaNaoThreadSafe = new ArrayList<>();
		
		/*List por padrão não é sincronizada, podemos mudar este estado com o Collections.synchronizedList*/
		List<String> listaThreadSafeComCollections = Collections.synchronizedList(new ArrayList<>());
		
		/*ou*/
		Vector<String> listaThreadSafePadrao = new Vector<>();
		
		for (int i = 0; i < 10; i++) {
			new Thread(new TarefaAdicionarElemento(listaThreadSafePadrao, i)).start();
		}
		
		Thread.sleep(1000);
		
		for (int i = 0; i < listaThreadSafePadrao.size(); i++) {
			System.out.println(i+" - "+listaThreadSafePadrao.get(i));
		}
	}
}
