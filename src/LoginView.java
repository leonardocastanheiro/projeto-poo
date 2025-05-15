import java.util.Scanner;

public class LoginView {

	public LoginView(Store store){
		this.store = store;
	}
	
	public void show() throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println("------");
		System.out.println("USUÁRIO:");
		String user = sc.nextLine();
		System.out.println("SENHA:");
		String password = sc.nextLine();
		System.out.println("-------------------------------");
		
		
		//FALTA PEGAR O VETOR DO USUÁRIOS, ENCONTRAR ESSE LOGIN E VERIFICAR A SENHA
		//Depois que tiver a permission certo, colocar dentro do .equals()
		if("ADMIN".equals("ADMIN")) {
			AdminView admin = new AdminView();
			admin.show();
		}
		else {
			ClientView client = new ClientView();
			client.show();
		}
		sc.close();
	}
}
