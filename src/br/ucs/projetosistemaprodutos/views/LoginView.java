package br.ucs.projetosistemaprodutos.views;
import java.util.Scanner;

import br.ucs.projetosistemaprodutos.models.itens.Store;

public class LoginView {
	
	public LoginView(Store store){
	}
	
	public void show(Scanner sc, Store store) throws Exception {
		

		System.out.println("------");
		System.out.println("USUÁRIO:");
		String user = sc.nextLine();
		System.out.println("SENHA:");
		String password = sc.nextLine();
		System.out.println("-------------------------------");
		
		
		//FALTA PEGAR O VETOR DO USUÁRIOS, ENCONTRAR ESSE LOGIN E VERIFICAR A SENHA
		//Depois que tiver a permission certo, colocar dentro do .equals()
		if("ADMIN".equals("ADMIN")) {
			AdminView admin = new AdminView(store);
			admin.show(sc, store);
		}
		else {
			ClientView client = new ClientView(store);
			client.show(sc, store);
		}
		
	}
}
