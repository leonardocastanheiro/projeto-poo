import java.util.Scanner;

import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.views.LoginView;

public class Main {
	private static Store store = new Store("Amazon");
	
    public static void main(String[] args) throws Exception {
    	Scanner sc = new Scanner(System.in);
    	LoginView login = new LoginView(store);
        login.show(sc);
    }    
}
