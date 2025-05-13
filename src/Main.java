import java.util.Scanner;

import br.ucs.projetosistemaprodutos.collections.DynamicProductArray;
import br.ucs.projetosistemaprodutos.collections.DynamicSupplierArray;
import br.ucs.projetosistemaprodutos.models.itens.Product;
import br.ucs.projetosistemaprodutos.models.itens.Stock;

public class Main {
	private static Scanner sc;
	private static DynamicProductArray dpa = new DynamicProductArray(10);
	public Main() {
	}

    public static void main(String[] args) throws Exception {
        Main login = new Main();
        login.showLogin();
    }
    
    public void showLogin() {
    	Scanner inicialSc = new Scanner(System.in);

		System.out.println("---WELCOME TO THE SYSTEM---");
		System.out.println("USER:");
		String user = inicialSc.nextLine();
		System.out.println("PASSWORD:");
		String password = inicialSc.nextLine();
		System.out.println("-------------------------------");
		
		
		//FALTA PEGAR O VETOR DO USUÁRIOS, ENCONTRAR ESSE LOGIN E VERIFICAR A SENHA
		//String permission = newAccess.getRoleString();
		Main menu = new Main();
		//Depois que tiver a permission certo, colocar dentro do .equals()
		if("ADMIN".equals("ADMIN")) {
			menu.showMenuAdm();
		}
		else {
			menu.showMenuClient();
		}
		inicialSc.close();
    }
    
    public void showMenuAdm() {
    	int option = 0;
    	sc = new Scanner(System.in);

		do {
			System.out.println("---------------------------------------------");
			System.out.println("Choose one:");
			System.out.println(" 1  - Start data");
			//SEPARAR?
			System.out.println(" 2  - Create client");
			System.out.println(" 3  - Clients");
			System.out.println(" 4  - Update client");
			System.out.println(" 5  - Delete client");
			//
			System.out.println(" 6  - Create supplier");
			System.out.println(" 7  - Suppliers");
			System.out.println(" 8  - Update supplier");
			System.out.println(" 9  - Delete supplier");
			//
			System.out.println(" 10 - Create product");
			System.out.println(" 11 - Products");
			System.out.println(" 12 - Update product");
			System.out.println(" 13 - Delete product");
			//
			System.out.println(" 14 - Create stock");
			System.out.println(" 15 - Stock");
			System.out.println(" 16 - Update stock");
			System.out.println(" 17 - Delete stock");
			//
			System.out.println(" 18 - Search orders");
			System.out.println(" 19 - Edit order");
			System.out.println(" 0  - Exit ");
			option= sc.nextInt();
			sc.nextLine();
			switch (option) {
				case 1:
					startData();
					break;
				case 2:
					createClient();
					break;
				case 3:
					showClients();
					break;
				case 4:
					updateClient();
					break;
				case 5:
					deleteClient();
					break;
				case 6:
					createSupplier();
					break;
				case 7:
					showSuppliers();
					break;
				case 8:
					updateSupplier();
					break;
				case 9:
					deleteSupplier();
					break;
				case 10:
					createProduct();
					break;
				case 11:
					showProducts();
					break;
				case 12:
					updateProduct();
					break;
				case 13:
					deleteProduct();
					break;
				case 0:
					System.out.println("Exiting...");
					System.exit(0);
				default:
					System.out.println("Not found...");
					System.exit(0);
			}
			System.out.println("---------------------------------------------");
		} while (option != 0);
		sc.close();
    }
    
    public void showMenuClient() {
    	int option = 0;

		do {
			System.out.println("---------------------------------------------");
			System.out.println("Choose one::");
			System.out.println(" 1 - Products");
			System.out.println(" 2 - My orders");
			System.out.println(" 0 - Exit ");
			option = sc.nextInt();
			sc.nextLine();
			switch (option) {
			case 1:
				System.out.println("PRODUCTS");
				break;
			case 2:
				System.out.println("ORDERS");
				break;
			case 0:
				System.out.println("Exiting...");
				System.exit(0);
			}
			System.out.println("---------------------------------------------");
		} while (option != 0);
		sc.close();
    }
    
    public void startData() {
    	System.out.println("---------------------------------------------");
    	System.out.println("INICIA OS DADOS");
    	System.out.println("---------------------------------------------");
    }
    
    public void createClient() {
    	System.out.println("---------------------------------------------");
    	System.out.println("CRIAR CLIENTE");
    	System.out.println("---------------------------------------------");
    }
    public void showClients() {
    	System.out.println("---------------------------------------------");
    	System.out.println("MOSTRA OS CLIENTES");
    	System.out.println("---------------------------------------------");
    }
    public void updateClient() {
    	System.out.println("---------------------------------------------");
    	System.out.println("ATUALIZAR O CLIENTE");
    	System.out.println("---------------------------------------------");
    }
    public void deleteClient() {
    	System.out.println("---------------------------------------------");
    	System.out.println("APAGAR O CLIENTE");
    	System.out.println("---------------------------------------------");
    }
    
    public void createSupplier() {	
    	System.out.println("---------------------------------------------");
    	System.out.println("CRIAR FORNECEDOR");
    	System.out.println("---------------------------------------------");
    }
    public void showSuppliers() {
    	System.out.println("---------------------------------------------");
    	System.out.println("MOSTRA OS FORNECEDORES");
    	System.out.println("---------------------------------------------");
    }
    public void updateSupplier() {
    	System.out.println("---------------------------------------------");
    	System.out.println("ATUALIZAR O FORNECEDOR");
    	System.out.println("---------------------------------------------");
    }
    public void deleteSupplier() {
    	System.out.println("---------------------------------------------");
    	System.out.println("APAGAR O FORNECEDOR");
    	System.out.println("---------------------------------------------");
    }
    
    public void createProduct() {
    	sc = new Scanner(System.in);
    	System.out.println("---------------------------------------------");
    	System.out.println("ADD A NEW PRODUCT");
    	System.out.println("Name: ");
    	String name = sc.nextLine();
    	System.out.println("Description: ");
    	String description = sc.nextLine();
    	System.out.println("Add photo: ");
    	byte photo = sc.nextByte();
    	System.out.println("Quantity: ");
    	int quantity = sc.nextInt();
    	System.out.println("Price: ");
    	double price = sc.nextDouble();
    	sc.nextLine();
    	System.out.println("---------------------------------------------");
    	Stock stock = new Stock(quantity, price);
    	Product product = new Product(name, description, stock);
    	dpa.add(product);
    	sc.close();
    } 	
    public void showProducts() {
    	
    }
    public void updateProduct() {
    	
    }
    public void deleteProduct() {
    	
    }
    
    public void createStock() {
    	
    }
    public void showStock() {
    	
    }
    public void updateStock() {
    	
    }
    public void deleteStock() {
    	
    }
    
}