import java.util.Scanner;

import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.views.LoginView;

public class Main {
	private static Scanner sc;
	private static Store store = new Store("Amazon");
	
    public static void main(String[] args) throws Exception {
    	LoginView login = new LoginView(store);
        login.show(sc, store);
    }    
}
