import java.util.Scanner;

import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.views.LoginView;

public class Main {
	private Store store = new Store("Amazon");
	
    public static void main(String[] args) throws Exception {
    	Main m = new Main();
    	Scanner sc = new Scanner(System.in);
    	LoginView login = new LoginView(m.store);
        login.show(sc);
        
        System.out.println("Programa encerrado.");
        sc.close();
    }    
}
