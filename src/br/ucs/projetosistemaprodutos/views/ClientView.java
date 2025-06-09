package br.ucs.projetosistemaprodutos.views;
import java.text.SimpleDateFormat;
import java.util.*;

import br.ucs.projetosistemaprodutos.controllers.ClientController;
import br.ucs.projetosistemaprodutos.controllers.ProductController;
import br.ucs.projetosistemaprodutos.models.itens.Order;
import br.ucs.projetosistemaprodutos.models.itens.Product;
import br.ucs.projetosistemaprodutos.models.itens.Situation;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Client;

public class ClientView {

	private Store store;
	private final ClientController clientController;
	private final ProductController productController;
	private final Client client;

	public ClientView(Store store, Client client) {
		this.client = client;
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
		int id = -1;
				
		do {
			System.out.println("---------------------------------------------");
			System.out.print("Buscar produto: ");
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
			System.out.print(" Digite o ID ou código do produto para ver detalhes: ");


			String text = sc.nextLine();
			
			Product productDetails = null;

			try {
				id = Integer.parseInt(text);
			} catch (Exception ignored) {}

			if(id == -1) {
				try {
					productDetails = productController.getByProductCode(text);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return;
				}
			}
			else {
				if (id > 0 && id <= products.size()) {
					productDetails = products.get(id - 1);
				}
			}

			if(productDetails == null) {
				return;
			}
			
			if(Objects.equals(text, "0")) {
				System.out.println("Saindo de pesquisa de produtos");
			}else {
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
		    			
		    			client.getShoppingCart().add(productDetails,maxQuantity);
						productController.changeStock(productDetails,-maxQuantity);
		    			System.out.println("Adicionando " + maxQuantity +" "+ productDetails.getName() + " ao carrinho");
		    			return;
		    		}

					client.getShoppingCart().add(productDetails,quantity);
					productController.changeStock(productDetails,-quantity);
	    			System.out.println("Adicionando " + quantity +" "+ productDetails.getName() + " ao carrinho");

		    	}
			}
			System.out.println("---------------------------------------------");
			
		}while(id != 0);
	}
	
	public void subCart(Scanner sc, Store store) {
		System.out.println("Carrinho de Compras");

		if(client.getShoppingCart().getProducts() == null) {
			System.out.println("Nenhum produto encontrado!");
			return;
		}

		Map<Product, Integer> productQuantity = client.getShoppingCart().getProducts();

		List<Map.Entry<Product, Integer>> entries = new ArrayList<>(productQuantity.entrySet());

		for (int i = 0; i < entries.size(); i++) {
			Map.Entry<Product, Integer> entry = entries.get(i);
			System.out.println((i + 1) + " - " + entry.getKey().getName() + ": " + entry.getValue() +" unidade"+ (entry.getValue() > 1 ? "s" : "") + " - Total: R$"+entry.getKey().getStock().getPrice()* entry.getValue());
		}

		System.out.println("---------------------------------------------");

		System.out.println("Escolha uma opção:\n1 - Editar Quantidade de um Produto\n2 - Finalizar Pedido\n3 - Excluir Carrinho de Compras\n0 - Sair");

		int option = -1;

		do {
			try {
				option = sc.nextInt();

				if(option < 0 || option > 3) {
					throw new InputMismatchException("Entrada inválida");
				}

			} catch (InputMismatchException e) {
				System.out.print("Entrada inválida, digite novamente: ");
			}
			sc.nextLine();
		} while (option < 0 || option > 3);

		switch (option) {
			case 0:
				System.out.println("Saindo do carrinho de compras...");
				return;
			case 1:

				for (int i = 0; i < entries.size(); i++) {
					Map.Entry<Product, Integer> entry = entries.get(i);
					System.out.println((i + 1) + " - " + entry.getKey().getName() + ": " + entry.getValue()
							+ " unidade" + (entry.getValue() > 1 ? "s" : "") + " - Total: R$"
							+ entry.getKey().getStock().getPrice() * entry.getValue());
				}
				System.out.println("0 - Sair");

				System.out.print("Digite o ID do item que deseja editar: ");

				option = -1;

				do {
					try {
						option = sc.nextInt();

						if (option < 0 || option > entries.size()) {
							throw new InputMismatchException("Entrada inválida");
						}

					} catch (InputMismatchException e) {
						System.out.print("Entrada inválida, digite novamente: ");
					}
					sc.nextLine();
				} while (option < 0 || option > entries.size());

				if (option == 0) {
					break;
				}

				Map.Entry<Product, Integer> entry = entries.get(option - 1);

				System.out.println(entry.getKey().getName() + ": " + entry.getValue() + " unidade" + (entry.getValue() > 1 ? "s" : ""));

				System.out.print("Digite a nova quantidade (Caso queira excluir o item, digite '0'): ");

				int quantity = -1;
				int quantityInCart = entry.getValue();
				int stockAvailable = entry.getKey().getStock().getQuantity();
				int maxAllowed = quantityInCart + stockAvailable;

				do {
					try {
						quantity = sc.nextInt();

						if (quantity < 0 || quantity > maxAllowed) {
							throw new InputMismatchException("Quantidade inválida. Digite um valor entre 0 e " + maxAllowed);
						}

					} catch (InputMismatchException e) {
						System.out.println("Quantidade inválida, digite novamente (máximo: " + maxAllowed + "): ");
					}
					sc.nextLine();
				} while (quantity < 0 || quantity > maxAllowed);

				try {
					productController.editCartItem(client, entry.getKey(), quantity);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return;
				}

				return;
			case 2:
				System.out.println("Finalizar Pedido\n1 - Finalizar\n0 - Sair");

				option = -1;

				do {
					try {
						option = sc.nextInt();

						if(option < 0 || option > 1) {
							throw new InputMismatchException("Entrada inválida");
						}

					} catch (InputMismatchException e) {
						System.out.print("Entrada inválida, digite novamente: ");
					}
					sc.nextLine();
				} while (option < 0 || option > 1);

				if(option == 0) {
					break;
				}

				System.out.println("Cartão de Crédito: "+client.getCreditCard());
				System.out.println("1 - Finalizar\n0 - Cancelar");

				option = -1;

				do {
					try {
						option = sc.nextInt();

						if(option < 0 || option > 1) {
							throw new InputMismatchException("Entrada inválida");
						}

					} catch (InputMismatchException e) {
						System.out.print("Entrada inválida, digite novamente: ");
					}
					sc.nextLine();
				} while (option < 0 || option > 1);

				if(option == 0) {
					break;
				}

				System.out.println("Pedido Finalizado!");

				Order order = new Order(new Date(), null, Situation.NEW,client, new HashMap<>(client.getShoppingCart().getProducts()));
				clientController.addOrder(order);

				System.out.println("ID do pedido: "+order.getId());
				System.out.println("Data do pedido: "+new SimpleDateFormat("dd/MM/yyyy").format(order.getDateOrder()));
				System.out.println("Cliente: "+order.getOwner().getName()+" - "+order.getOwner().getEmail());

				client.getShoppingCart().clearCart();

				return;
			case 3:
				client.getShoppingCart().clearCart();
				System.out.println("Carrinho de compras excluído com sucesso!");
				return;
		}
	}
	
	public void subOrders(Scanner sc, Store store) {
		
	}
	
	public void subClientAccount(Scanner sc, Store store) {
		
	}
}
