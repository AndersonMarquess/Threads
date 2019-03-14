package com.andersonmarques.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BuscarNome {
	
	private String arquivo;
	private String nome; 
	
	public BuscarNome(String arquivo, String nome) {
		this.arquivo = arquivo;
		this.nome = nome;
	}

	public void buscar() {
		try {
			Scanner sc = new Scanner(new File(arquivo));
			int numLinha = 1;
			while (sc.hasNextLine()) {
				String linha = sc.nextLine();
				
				if(linha.toLowerCase().contains(nome.toLowerCase())) {
					System.out.println(
							String.format("%d - %s - %s", 
									numLinha, arquivo, linha));
				}
				numLinha++;
			}
		
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
