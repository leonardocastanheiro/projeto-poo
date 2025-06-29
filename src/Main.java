import java.util.Scanner;

import br.ucs.projetosistemaprodutos.collections.DynamicSupplierArray;
import br.ucs.projetosistemaprodutos.controllers.AdminController;
import br.ucs.projetosistemaprodutos.controllers.ClientController;
import br.ucs.projetosistemaprodutos.controllers.ProductController;
import br.ucs.projetosistemaprodutos.controllers.SupplierController;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.itens.Product;
import br.ucs.projetosistemaprodutos.models.itens.Stock;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Admin;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;
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
