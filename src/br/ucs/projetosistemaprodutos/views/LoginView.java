package br.ucs.projetosistemaprodutos.views;
import java.util.Scanner;

import br.ucs.projetosistemaprodutos.controllers.UserController;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.User;

public class LoginView {

	private final Store store;
	private final UserController controller;

	public LoginView(Store store){
		this.store = store;
		controller = new UserController(store);
	}
	
	public void show(Scanner sc) throws Exception {
		
		User user = null;

		do {
			System.out.println("------");
			System.out.print("Login: ");
			String login = sc.nextLine();
			System.out.print("Senha: ");
			String password = sc.nextLine();
			System.out.println("------");

			try {
				user = controller.verifyLoginAndPassword(login, password);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (user==null);

		if(user.getRole() == Role.ADMIN) {
			AdminView admin = new AdminView(store);
			admin.show(sc);
		}
		else if(user.getRole() == Role.CLIENT){
			ClientView client = new ClientView(store);
			client.show(sc);
		}
		
	}
}
