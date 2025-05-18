package br.ucs.projetosistemaprodutos.views;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.ucs.projetosistemaprodutos.controllers.*;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.copies.ClientCopy;
import br.ucs.projetosistemaprodutos.models.itens.*;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;
import br.ucs.projetosistemaprodutos.models.person.User;

public class AdminView {

	private Store store;
	private AdminController adminController;
	private ClientController clientController;

	public AdminView(Store store) {
		this.store = store;
		this.adminController = new AdminController(store);
		this.clientController = new ClientController(store);
	}
	
	public void show(Scanner sc) throws Exception {
		int option = 0;
    	
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Escolha uma opção:");
			System.out.println("1  - Iniciar dados");
			System.out.println("2  - Clientes");
			System.out.println("3  - Fornecedores");
			System.out.println("4  - Produtos");
			System.out.println("5  - Estoque");
			System.out.println("6  - Pedidos");
			System.out.println("0  - Sair");
			
			option= sc.nextInt();
			sc.nextLine();
			AdminView submenu = new AdminView(store);
			switch (option) {
				case 1:
					startData();
					break;
				case 2:
					submenu.subClients(sc, store);
					break;
				case 3:
					submenu.subSuppliers(sc, store);
					break;
				case 4:
					submenu.subProducts(sc, store);
					break;
				case 5:
					submenu.subStock(sc, store);
					break;
				case 6:
					submenu.subOrders(sc, store);
					break;
				case 0:
					System.out.println("Saindo...");
					System.exit(0);
				default:
					System.out.println("Não encotrado...");
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
    
    public void subClients(Scanner sc, Store store) {
    	int subOption = 0;
    	ClientController newClient = new ClientController(store);
    	do {
			System.out.println("---------------------------------------------");
			System.out.println("Escolha uma opção:");
    		System.out.println(" 1  - Adicionar cliente");
    		System.out.println(" 2  - Ver clientes");
    		System.out.println(" 3  - Atualizar cliente");
    		System.out.println(" 4  - Excluir cliente");
    		System.out.println(" 0  - Sair");
    		
    		subOption = sc.nextInt();
			sc.nextLine();
			
			switch (subOption) {
				case 1:
					System.out.print("Nome: ");
					String name = sc.nextLine();
					System.out.print("Telefone: ");
					String phone = sc.nextLine();
					System.out.print("Email: ");
					String email = sc.nextLine();
					System.out.print("Usuário: ");
					String login = sc.nextLine();
					System.out.print("Senha: ");
					String password = sc.nextLine();
					System.out.print("Cartão de crédito: ");
					String creditCard = sc.nextLine();
					System.out.println("ENDEREÇO");
					System.out.print("Rua: ");
					String street = sc.nextLine();
					System.out.print("Número: ");
					String number = sc.nextLine();
					System.out.print("Complemento: ");
					String complement = sc.nextLine();
					System.out.print("Bairro: ");
					String neighborhood = sc.nextLine();
					System.out.print("CEP: ");
					String cep = sc.nextLine();
					System.out.print("Cidade: ");
					String city = sc.nextLine();
					System.out.print("Estado: ");
					String state = sc.nextLine();
					
					Address address = new Address( street, number, complement, neighborhood, cep, city, state);
					Client client = new Client(login, password, Role.CLIENT, name, phone, email, creditCard, address);
					try {
						clientController.create(client);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						clientController.showArray();
					} catch (Exception e) {
						System.out.println(e.getMessage());
                    }
                    break;
				case 3:
					System.out.println("--------");
					System.out.println("Digite o ID do cliente que deseja editar: ");

					int id;
					Client client1;

					try {
						id = sc.nextInt();

						client1 = (Client) clientController.getById(id);

					} catch (InputMismatchException e) {
						System.out.println("Tipo digitado não corresponde a um ID.");
						return;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					ClientCopy clientCopy = new ClientCopy(client1);


					//REALIZAR VIEWS DE MOSTRAR OS DADOS DO CLIENT (COPIA) E O QUE QUER EDITAR


					try {
						clientController.edit(client1,clientCopy);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					break;

				case 4:
					System.out.println("--------");
					System.out.println("Clientes: ");

					try {
						clientController.showArray(Role.CLIENT);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					System.out.println("--------");
					System.out.println("Selecione o ID do cliente que deseja excluir: ");

					int id1;

					try {
						id1 = sc.nextInt();

						User user = clientController.getById(id1);

						newClient.delete((Client) user);
					} catch (InputMismatchException e) {
						System.out.println("Tipo digitado não corresponde a um ID.");
						return;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
                    }

					System.out.println("Cliente excluído com sucesso!");

					break;
				case 0:
					System.out.println("Saindo de 'Clientes'...");
					System.exit(0);
				default:
					System.out.println("Erro...");
					System.exit(0);
			}
    	}while(subOption != 0);
		System.out.println("---------------------------------------------");
    }
    
    public void subSuppliers(Scanner sc, Store store) throws Exception {
    	int subOption = 0;
    	SupplierController newSupplier = new SupplierController(store);
    	do {
			System.out.println("---------------------------------------------");
			System.out.println("Escolha uma opção:");
    		System.out.println(" 1  - Adicionar fornecedor");
    		System.out.println(" 2  - Ver fornecedores");
    		System.out.println(" 3  - Atualizar fornecedor");
    		System.out.println(" 4  - Excluir fornecedor");
    		System.out.println(" 0  - Sair");
    		
    		subOption= sc.nextInt();
			sc.nextLine();
			
			switch (subOption) {
				case 1:
					System.out.println("Nome: ");
					String name = sc.nextLine();
					System.out.println("Telefone: ");
					String phone = sc.nextLine();
					System.out.println("Email: ");
					String email = sc.nextLine();
					System.out.println("Descrição: ");
					String description = sc.nextLine();
					System.out.println("ENDEREÇO ");
					System.out.println("Rua: ");
					String street = sc.nextLine();
					System.out.println("Número: ");
					String number = sc.nextLine();
					System.out.println("Complemento: ");
					String complement = sc.nextLine();
					System.out.println("Bairro: ");
					String neighborhood = sc.nextLine();
					System.out.println("CEP: ");
					String cep = sc.nextLine();
					System.out.println("Cidade: ");
					String city = sc.nextLine();
					System.out.println("Estado: ");
					String state = sc.nextLine();
					
					Address address = new Address( street, number, complement, neighborhood, cep, city, state);
					Supplier supplier = new Supplier(Role.SUPPLIER, name, phone, email, description, address);
					newSupplier.create(supplier);
					break;
				case 2:
					//MOSTRAR LISTA
					break;
				case 3:
					//ATUALIZAR
					break;
				case 4:
					newSupplier.delete(null);
					break;
				case 0:
					System.out.println("Saindo de 'Fornecedores'...");
					System.exit(0);
				default:
					System.out.println("Erro...");
					System.exit(0);
			}
    	}while(subOption != 0);
		System.out.println("---------------------------------------------");
		
	}
    
    public void subProducts(Scanner sc, Store store) throws Exception {
    	int subOption = 0;
    	ProductController newProduct = new ProductController(store);
    	do {
			System.out.println("---------------------------------------------");
			System.out.println("Escolha uma opção:");
    		System.out.println(" 1  - Adicionar produto");
    		System.out.println(" 2  - Ver produtos");
    		System.out.println(" 3  - Atualizar produto");
    		System.out.println(" 4  - Excluir produto");
    		System.out.println(" 0  - Sair");
    		
    		subOption= sc.nextInt();
			sc.nextLine();
			
			switch (subOption) {
				case 1:
			    	System.out.println("Nome: ");
			    	String name = sc.nextLine();
			    	System.out.println("Descrição: ");
			    	String description = sc.nextLine();
			    	System.out.println("Nome do fornecedor: ");
			    	String nameSupplier = sc.nextLine();
			    	
			    	SupplierController supplier = new SupplierController(store);
					Product product = new Product(name, description, supplier.getByName(nameSupplier));
					newProduct.create(product);
					break;
				case 2:
					//MOSTRAR LISTA
					break;
				case 3:
					//LISTA PRODUTOS (newProduct.read());
					newProduct.getById(1); 
					System.out.println("Qual produto você deseja editar?");
					
					int id = sc.nextInt();
					Product updateProduct = newProduct.getById(id);
					if(updateProduct != null) {		    	
				    	int updateOp = 0;
				    	Product updateProductCopy = new Product();
				    	do {
					    	System.out.println("EDITAR PRODUTO: ");
							System.out.println("1. Nome: " + updateProduct.getName());
					    	System.out.println("2. Descrição: " + updateProduct.getDescription());
					    	System.out.println("3. Nome do fornecedor: " + updateProduct.getSupplier().getName());
					    	System.out.println("4. Estoque: \n"
					    			+ "Quantidade - " + updateProduct.getStock().getQuantity() + "\n"
					    			+ "Preço - R$ " + updateProduct.getStock().getPrice());
					    	System.out.println("5. Salvar");
					    	System.out.println("0. Cancelar");
					    	System.out.println("Escolha o campo que deseja editar: ");
					    	updateOp = sc.nextInt();
					    	sc.nextLine();
					    	
					    	switch(updateOp) {
					    		case 1:
					    			System.out.println("Novo nome: ");
					    			updateProductCopy.setName(sc.nextLine());
					    			break;
					    		case 2:
					    			System.out.println("Nova descrição: ");
					    			updateProductCopy.setDescription(sc.nextLine());
					    			break;
					    		case 3: 
					    			SupplierController newSupplier = new SupplierController(store);
					    			System.out.println("Nome do novo fornecedor: ");
					    			Supplier foundSupplier = newSupplier.getByName(sc.nextLine());
					    			if(foundSupplier != null) {
						    			updateProductCopy.setSupplier(foundSupplier);
					    			}else {
					    				System.out.println("Fornecedor não encontrado");
					    			}
					    			break;
					    		case 4: 
					    			System.out.println("Nova quantidade em estoque: ");
					    			int newQuantity = sc.nextInt();
					    			System.out.println("Novo preço: ");
					    			double newPrice = sc.nextDouble();
					    			Stock newStock = new Stock(newQuantity, newPrice);
					    			updateProductCopy.setStock(newStock);
					    			break;
					    		case 5:
					    			newProduct.update(updateProductCopy, updateProduct);
					    		case 0:
					    			System.out.println("Saindo de 'Atualizar produto'...");
					    			break;
								default:
									System.out.println("Opção inválida");
					    	}
					  		
				    	}while(updateOp != 0);
					}else {
						System.out.println("Produto não encontrado");
					}
					break;
				case 4:
					//MOSTRAR LISTA E ESCOLHER QUAL APAGAR
					newProduct.delete(null);
					break;
				case 0:
					System.out.println("Saindo de 'Produtos'...");
					break;
				default:
					System.out.println("Erro...");
			}
    	}while(subOption != 0);
		System.out.println("---------------------------------------------");  	
	}
    
    public void subStock(Scanner sc, Store store) {
    	int subOption = 0;
    	    	
    	do {
			System.out.println("---------------------------------------------");
			System.out.println("Escolha uma opção:");
    		System.out.println(" 1  - Adicionar estoque");
    		System.out.println(" 2  - Atualizar estoque");
    		System.out.println(" 0  - Sair");
    		
    		subOption= sc.nextInt();
			sc.nextLine();
			
			switch (subOption) {
				case 1:
					//SELECIONAR O PRODUTO
					Product product = new Product();
			    	System.out.println("Quantidade: ");
			    	Integer quantity = sc.nextInt();
			    	System.out.println("Preço: ");
			    	Double price = sc.nextDouble();
			    	
			    	Stock stock = new Stock(quantity, price);
			    	product.setStock(stock);
					break;
				case 2:
					//ATUALIZAR
					//SELECIONAR O PRODUTO
					Product newProduct = new Product();
					System.out.println("Quantidade atual: " + newProduct.getStock().getQuantity());
			    	System.out.println("Nova quantidade: ");
			    	Integer newQuantity = sc.nextInt();
			    	System.out.println("Último preço: " + newProduct.getStock().getPrice());
			    	System.out.println("Novo preço: ");
			    	Double newPrice = sc.nextDouble();
			    	
			    	Stock updateStock = new Stock(newQuantity, newPrice);
			    	newProduct.setStock(updateStock);
					break;
				case 0:
					System.out.println("Saindo de 'Estoque'...");
					System.exit(0);
				default:
					System.out.println("Erro...");
					System.exit(0);
			}
    	}while(subOption != 0);
		System.out.println("---------------------------------------------");  
		
	}
    
    public void subOrders(Scanner sc, Store store) {
    	System.out.println("---------------------------------------------");
		System.out.println("EM PRODUÇÃO");
		System.out.println("---------------------------------------------");
	}
}
