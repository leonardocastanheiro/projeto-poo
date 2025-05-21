package br.ucs.projetosistemaprodutos.views;
import java.util.Scanner;

import br.ucs.projetosistemaprodutos.controllers.ClientController;
import br.ucs.projetosistemaprodutos.models.itens.Store;

public class ClientView {

	private Store store;
	private ClientController controller;

	public ClientView(Store store) {
		this.store = store;
		this.controller = new ClientController(store);
	}
	
	public void show(Scanner sc) {
		int option = 0;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Escolha uma opção:");
			System.out.println(" 1 - Produtos");
			System.out.println(" 2 - Meus pedidos");
			System.out.println(" 0 - Sair ");
			option = sc.nextInt();
			sc.nextLine();
			switch (option) {
			case 1:
			case 2:
			case 0:
				//System.out.println("Saindo...");
				//System.exit(0);
			default:
				System.out.println("---------------------------------------------");
				System.out.println("EM PRODUÇÃO, VOLTE OUTRA HORA");
				System.out.println("---------------------------------------------");
				System.exit(0);
			}
			System.out.println("---------------------------------------------");
		} while (option != 0);
		sc.close();
	}
}
