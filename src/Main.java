import java.util.Scanner;

import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.utils.StoreManager;
import br.ucs.projetosistemaprodutos.utils.StoreStarter;
import br.ucs.projetosistemaprodutos.views.LoginView;

public class Main {

    public static void main(String[] args) throws Exception {
        StoreManager storeManager = new StoreManager();
        Store store;

        try {
            store = storeManager.read();
        } catch (Exception e) {
        	StoreStarter ss = new StoreStarter();
            store = ss.read();
            storeManager.save(store);
        }

    	Scanner sc = new Scanner(System.in);
    	LoginView login = new LoginView(store);
        login.show(sc);

        System.out.println("Programa encerrado.");
        storeManager.save(store);
        sc.close();
    }
}
