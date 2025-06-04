package br.ucs.projetosistemaprodutos.views;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.ucs.projetosistemaprodutos.controllers.ClientController;
import br.ucs.projetosistemaprodutos.controllers.ProductController;
import br.ucs.projetosistemaprodutos.models.itens.Product;
import br.ucs.projetosistemaprodutos.models.itens.Store;

public class ClientView {

	private Store store;
	private final ClientController clientController;
	private final ProductController productController;
	
	public ClientView(Store store) {
		this.store = store;
		this.clientController = new ClientController(store);
		this.productController = new ProductController(store);
	}
	
	public void show(Scanner sc) {
		int option = -1;
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Escolha uma opção:");
			System.out.println(" 1 - Produtos");
			System.out.println(" 2 - Carrinho de compras");
			System.out.println(" 3 - Pedidos");
			System.out.println(" 4 - Dados do cliente");
			System.out.println(" 0 - Sair ");
			
			do {
				try {
					option = sc.nextInt();

					if(option < 0 || option > 4) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			} while (option<0 || option>4);
			
			switch (option) {
				case 1:
					this.subProducts(sc, store);
					break;
				case 2:
					this.subCart(sc, store);
					break;
				case 3: 
					this.subOrders(sc, store);
					break;
				case 4: 
					this.subClientAccount(sc, store);
					break;
				case 0:
					System.out.println("Saindo...");
					System.exit(0);
				default:
					System.out.println("Não encontrado...");
			}
			System.out.println("---------------------------------------------");
		} while (option != 0);
		sc.close();
	}
	
	public void subProducts(Scanner sc, Store store) {	
		int productId = -1;
				
		do {
			System.out.println("---------------------------------------------");
			System.out.println("Buscar produto:");
			String text1 = sc.nextLine();

			List<Product> products;

			try {
				products = productController.getByText(text1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return;
			}

			for(int i = 0; i<products.size();i++) {
				System.out.println(i+1+" - Código: "+ products.get(i).getProductCode() +" - Nome: "+products.get(i).getName() + " | Preço: " + products.get(i).getStock().getPrice());
			}
			System.out.println("0 - Sair ");
			System.out.println(" Digite o ID ou código do produto para ver detalhes ");
				
			String text = sc.nextLine();
			
			Product productDetails;

			try {
				productDetails = productController.getByProductCode(text);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return;
			}
			
			//ESTÁ PEGANDO SÓ O CÓDIGO, FALTA CONSEGUIR BUSCAR PELA POSIÇÃO NA LISTA (products) TAMBÉM
			/*
			 * do { try { productId = sc.nextInt();
			 * 
			 * if(productId < 0 || productId > products.size()) { throw new
			 * InputMismatchException("Entrada inválida"); }
			 * 
			 * } catch (InputMismatchException e) {
			 * System.out.print("Entrada inválida, digite novamente: "); } sc.nextLine(); }
			 * while (productId<0 || productId>products.size());
			 */
			
			if(text == "0") {
				System.out.println("Saindo de pesquisa de produtos");
			}else {
				//Product productDetails = products.get(productId - 1);
				int add = -1;
				
				System.out.println("PRODUTO: ");
				System.out.println("Código: " + productDetails.getProductCode());
				System.out.println("Nome: " + productDetails.getName());
		    	System.out.println("Descrição: " + productDetails.getDescription());
		    	System.out.println("Quantidade em estoque: " + productDetails.getStock().getQuantity());
		    	System.out.println("Preço: "+ productDetails.getStock().getPrice());
		    	System.out.println("Escolha o que deseja fazer: ");
		    	System.out.println("1. Adicionar ao carrinho");
		    	System.out.println("0. Cancelar");
		    	
		    	do {
					try {
						add = sc.nextInt();

						if(add != 0 && add != 1) {
							throw new InputMismatchException("Entrada inválida");
						}

					} catch (InputMismatchException e) {
						System.out.print("Entrada inválida, digite novamente: ");
					}
					sc.nextLine();
				} while (add != 0 && add != 1);
		    	
		    	if(add == 1) {
		    		int maxQuantity = productDetails.getStock().getQuantity();
		    		if(maxQuantity <  1) {
		    			System.out.println("Produto indisponível no momento.");
		    			return;
		    		}
		    		System.out.print("Digite quantas unidades deseja adicionar ao carrinho: ");
		    		int quantity = sc.nextInt();
		    		sc.nextLine();
		    		if(quantity > maxQuantity) {
		    			int maxQuantityOp = -1;
		    			System.out.println("Não temos essa quantidade disponível no momento. Adicionar " + maxQuantity + " ao carrinho? ");
		    			System.out.println("1 - Sim, continuar");
		    			System.out.println("0 - Não, cancelar");
		    			
		    			do {
							try {
								maxQuantityOp = sc.nextInt();

								if(maxQuantityOp != 0 && maxQuantityOp != 1) {
									throw new InputMismatchException("Entrada inválida");
								}

							} catch (InputMismatchException e) {
								System.out.print("Entrada inválida, digite novamente: ");
							}
							sc.nextLine();
						} while (maxQuantityOp != 0 && maxQuantityOp != 1);
		    			
		    			if(maxQuantityOp == 0) {
		    				System.out.println("Saindo...");
		    				return;
		    			}
		    			
		    			//ADICIONAR PRODUTO COM MAXQUANTITY AO CARRINHO
		    			System.out.println("Adicionando " + maxQuantity + productDetails.getName() + " ao carrinho");
		    			return;
		    		}
		    		
			    	//ADICIONAR QUANTITY AO CARRINHO
	    			System.out.println("Adicionando " + quantity + productDetails.getName() + " ao carrinho");

		    	}
			}
			System.out.println("---------------------------------------------");
			
		}while(productId != 0);
	}
	
	public void subCart(Scanner sc, Store store) {
		
	}
	
	public void subOrders(Scanner sc, Store store) {
		
	}
	
	public void subClientAccount(Scanner sc, Store store) {
		
	}
}
