package br.ucs.projetosistemaprodutos.views;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import br.ucs.projetosistemaprodutos.controllers.ClientController;
import br.ucs.projetosistemaprodutos.controllers.ProductController;
import br.ucs.projetosistemaprodutos.models.copies.ClientCopy;
import br.ucs.projetosistemaprodutos.models.itens.*;
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
			System.out.println("Escolha uma opção:");
			System.out.println(" 1 - Produtos");
			System.out.println(" 2 - Carrinho de compras");
			System.out.println(" 3 - Pedidos");
			System.out.println(" 4 - Dados do cliente");
			System.out.println(" 0 - Sair ");

			do {
				try {
					option = sc.nextInt();

					if (option < 0 || option > 4) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			} while (option < 0 || option > 4);

			switch (option) {
				case 1:
					this.subProducts(sc);
					break;
				case 2:
					this.subCart(sc);
					break;
				case 3:
					this.subOrders(sc);
					break;
				case 4:
					this.subClientAccount(sc);
					break;
				case 0:
					System.out.println("Saindo...");
					return;
				default:
					System.out.println("Não encontrado...");
			}
		} while (option != 0);
	}

	public void subProducts(Scanner sc) {
		int id = -1;

		do {
			System.out.println("---------------------------------------------");
			System.out.println("Buscar produto: ");
			System.out.println("Digite 0 para voltar ao menu");
			String text1 = sc.nextLine();

			List<Product> products;

			try {
				int idAux = Integer.parseInt(text1);

				if (idAux == 0) {
					id = 0;
				}
			} catch (NumberFormatException ignored) {
			}

			if (id == 0) {
				return;
			}

			try {
				products = productController.getByText(text1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return;
			}

			for (int i = 0; i < products.size(); i++) {
				System.out.println(i + 1 + " - Código: " + products.get(i).getProductCode() + " - Nome: " + products.get(i).getName() + " | Preço: " + products.get(i).getStock().getPrice());
			}
			System.out.println("0 - Sair ");
			System.out.print("Digite o ID ou código do produto para ver detalhes: ");


			String text = sc.nextLine();

			Product productDetails = null;

			try {
				productDetails = productController.getByProductCode(text);
			} catch (Exception e) {
				try {
					id = Integer.parseInt(text);
					if (id > 0 && id <= products.size()) {
						productDetails = products.get(id - 1);
					} else if (id == 0) {
						System.out.println("Saindo de pesquisa de produtos");
						return;
					}
				} catch (NumberFormatException ignored) {
				}
			}

			if (productDetails == null) {
				return;
			}

			if (Objects.equals(text, "0")) {
				System.out.println("Saindo de pesquisa de produtos");
			} else {
				int add = -1;
				System.out.println("---------- Produto  ----------");
				System.out.println("Código: " + productDetails.getProductCode());
				System.out.println("Nome: " + productDetails.getName());
				System.out.println("Descrição: " + productDetails.getDescription());
				System.out.println("Quantidade em estoque: " + productDetails.getStock().getQuantity());
				System.out.println("Preço: " + productDetails.getStock().getPrice());
				System.out.println("----------          ----------");
				System.out.println("Escolha o que deseja fazer: ");
				System.out.println("1. Adicionar ao carrinho");
				System.out.println("0. Cancelar");

				do {
					try {
						add = sc.nextInt();

						if (add != 0 && add != 1) {
							throw new InputMismatchException("Entrada inválida");
						}

					} catch (InputMismatchException e) {
						System.out.print("Entrada inválida, digite novamente: ");
					}
					sc.nextLine();
				} while (add != 0 && add != 1);

				if (add == 1) {
					int maxQuantity = productDetails.getStock().getQuantity();
					if (maxQuantity < 1) {
						System.out.println("Produto indisponível no momento.");
						return;
					}
					System.out.print("Digite quantas unidades deseja adicionar ao carrinho: ");
					int quantity = sc.nextInt();
					sc.nextLine();

					if (quantity < 1) {
						System.out.println("Nenhum produto adicionado.");
						return;
					}

					if (quantity > maxQuantity) {
						int maxQuantityOp = -1;
						System.out.println("Não temos essa quantidade disponível no momento. Adicionar " + maxQuantity + " ao carrinho? ");
						System.out.println("1 - Sim, continuar");
						System.out.println("0 - Não, cancelar");

						do {
							try {
								maxQuantityOp = sc.nextInt();

								if (maxQuantityOp != 0 && maxQuantityOp != 1) {
									throw new InputMismatchException("Entrada inválida");
								}

							} catch (InputMismatchException e) {
								System.out.print("Entrada inválida, digite novamente: ");
							}
							sc.nextLine();
						} while (maxQuantityOp != 0 && maxQuantityOp != 1);

						if (maxQuantityOp == 0) {
							System.out.println("Saindo...");
							return;
						}

						client.getShoppingCart().add(productDetails, maxQuantity);
						productController.changeStock(productDetails, -maxQuantity);
						System.out.println("Adicionando " + maxQuantity + " " + productDetails.getName() + " ao carrinho");
						return;
					}

					client.getShoppingCart().add(productDetails, quantity);
					productController.changeStock(productDetails, -quantity);
					System.out.println("Adicionando " + quantity + " " + productDetails.getName() + " ao carrinho");

				}
			}

		} while (id != 0);
	}

	public void subCart(Scanner sc) {
		System.out.println("Carrinho de Compras");

		if (client.getShoppingCart().getProducts() == null) {
			System.out.println("Nenhum produto encontrado!");
			return;
		}

		Map<Product, Integer> productQuantity = client.getShoppingCart().getProducts();

		List<Map.Entry<Product, Integer>> entries = new ArrayList<>(productQuantity.entrySet());

		for (int i = 0; i < entries.size(); i++) {
			Map.Entry<Product, Integer> entry = entries.get(i);
			System.out.println((i + 1) + " - " + entry.getKey().getName() + ": " + entry.getValue() + " unidade" + (entry.getValue() > 1 ? "s" : "") + " - Total: R$" + entry.getKey().getStock().getPrice() * entry.getValue());
		}

		System.out.println("---------------------------------------------");

		System.out.println("Escolha uma opção:\n1 - Editar Quantidade de um Produto\n2 - Finalizar Pedido\n3 - Excluir Carrinho de Compras\n0 - Sair");

		int option = -1;

		do {
			try {
				option = sc.nextInt();

				if (option < 0 || option > 3) {
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

				double value = 0;
				double valueICMS = 0;
				for (Map.Entry<Product, Integer> newEntry : client.getShoppingCart().getProducts().entrySet()) {
					double valueProduct = newEntry.getKey().getStock().getPrice();
					Integer newQuantity = newEntry.getValue();

					value += valueProduct * newQuantity;
					valueICMS = value * 1.17;
				}

				System.out.println("Cartão de Crédito: " + client.getCreditCard());
				System.out.println("Valor total (sem ICMS): R$" + String.format("%.2f", value));
				System.out.println("Valor total (com ICMS): R$ " + String.format("%.2f", valueICMS));
				System.out.println("1 - Finalizar\n0 - Cancelar");

				option = -1;

				do {
					try {
						option = sc.nextInt();

						if (option < 0 || option > 1) {
							throw new InputMismatchException("Entrada inválida");
						}

					} catch (InputMismatchException e) {
						System.out.print("Entrada inválida, digite novamente: ");
					}
					sc.nextLine();
				} while (option < 0 || option > 1);

				if (option == 0) {
					break;
				}

				List<ItemOrder> itemOrders = new ArrayList<>();
				Order order = new Order(LocalDate.now(), null, Situation.NEW, client, value, valueICMS);
				for (Map.Entry<Product, Integer> newEntry : client.getShoppingCart().getProducts().entrySet()) {
						itemOrders.add(new ItemOrder(newEntry.getValue(), newEntry.getKey().getStock().getPrice(), order, newEntry.getKey()));
				}
				order.setItemOrders(itemOrders);
				clientController.addOrder(order);
				System.out.println("Pedido Finalizado!");

				System.out.println("ID do pedido: " + order.getId());
				System.out.println("Data do pedido: " + order.getDateOrder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				System.out.println("Cliente: " + order.getOwner().getName() + " - " + order.getOwner().getEmail());
				System.out.println("Valor total (sem ICMS): R$" + String.format("%.2f", order.getTotalPrice()));
				System.out.println("Valor total (com ICMS): R$" + String.format("%.2f", order.getTotalPriceICMS()));

				client.getShoppingCart().clearCart();

				return;
			case 3:
				client.getShoppingCart().clearCart();
				System.out.println("Carrinho de compras excluído com sucesso!");
				return;
		}
	}

	public void subOrders(Scanner sc) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		List<Order> orders = new ArrayList<>();
		int op = -1;
		
		do {
			System.out.println("Pedidos");
			System.out.println("1 - Pesquisar por número");
			System.out.println("2 - Pesquisar por intervalo de data");
			System.out.println("0 - Sair");
			System.out.println("Escolha uma opção: ");
			
			try {
				op = sc.nextInt();
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println("Entrada inválida.");
			}
		}while(op < 0 ||op > 2);
		
		switch(op) {
			case 1:
				System.out.println("PESQUISAR POR NUMERO");
				break;
			case 2: 
				LocalDate startDate = null;
				LocalDate endDate = null;
				
				try {
					System.out.println("Data de início: ");
					startDate = LocalDate.parse(sc.nextLine(), dtf);
					
					System.out.println("Data final: ");
					endDate = LocalDate.parse(sc.nextLine(), dtf);
				}catch(DateTimeParseException e) {
					System.out.println("Data inválida.");
					return;
				}
				
				if(startDate.isAfter(endDate)) {
					System.out.println("Data de início maior e a data final.");
					return;
				}
				orders = productController.getByDate(startDate, endDate, client.getOrders());
				break;
			case 0:
				System.out.println("Saindo de pedidos...");
				return;				
		}

		if(orders.isEmpty()) {
			System.out.println("Nenhum pedido encontrado");
		}else {
			for(Order order : orders) {
				System.out.println("-----------  Pedido  -----------");
				System.out.println("ID do Pedido: " + order.getId());
				System.out.println("Situação: " + order.getSituation().toString());
				System.out.println("Data do pedido: " + order.getDateOrder().format(dtf));
				System.out.println("Data de envio: " + (order.getDateForward() == null ? "*AINDA NÃO ENVIADO*" : order.getDateForward().format(dtf)));
				System.out.println("Data de entrega: " + (order.getDateDeliver() == null ? "*AINDA NÃO ENTREGUE*" : order.getDateDeliver().format(dtf)));
				System.out.println("Valor total do pedido (sem ICMS): R$" + String.format("%.2f", order.getTotalPrice()));
				System.out.println("Valor total do pedido (com ICMS): R$" + String.format("%.2f", order.getTotalPriceICMS()));
			
				System.out.println("---------- Detalhes ----------");
				for(ItemOrder itemOrder : order.getItemOrders()) {
					Product product = itemOrder.getProduct(); 
					Integer quantity = itemOrder.getQuantity();
					System.out.println("Nome: " + product.getName() + " | Descrição: " + product.getDescription());
					System.out.println("Quantidade: " + quantity + " | Valor unitário: " + product.getStock().getPrice() + " | Valor Total: " + itemOrder.getPrice() * quantity); 
				}
				System.out.println("----------          ----------");
			}
			
		}
	}
		
		
	

	public void subClientAccount(Scanner sc) {
		try {
			// Dados pessoais
			System.out.println("\n---------- DADOS DO CLIENTE -----------");
			System.out.println("Nome: " + client.getName());
			System.out.println("Telefone: " + client.getPhone());
			System.out.println("E-mail: " + client.getEmail());
			System.out.println("Cartão de Crédito: "+client.getCreditCard());

			// Endereço com verificação de null
			System.out.println("\n---------- ENDEREÇO ----------");
			if (client.getAddress() != null) {
				System.out.println("Logradouro: " + client.getAddress().getStreet() + ", " + client.getAddress().getNumber());
				System.out.println("Bairro: " + client.getAddress().getNeighborhood());
				System.out.println("CEP: " + client.getAddress().getCep());
				System.out.println("Cidade: " + client.getAddress().getCity() + ", " + client.getAddress().getState());

				if (client.getAddress().getComplement() != null && !client.getAddress().getComplement().isEmpty()) {
					System.out.println("Complemento: " + client.getAddress().getComplement());
				}
			} else {
				System.out.println("Endereço não cadastrado");
			}
		}
		catch (Exception e) {
			System.out.println("Erro ao exibir dados do cliente: " + e.getMessage());
		}

		System.out.println("\n1 - Editar\n0 - Sair");

		int option = -1;

		do {
			try {
				option = sc.nextInt();

				if (option < 0 || option > 1) {
					throw new InputMismatchException("Entrada inválida");
				}

			} catch (InputMismatchException e) {
				System.out.print("Entrada inválida, digite novamente: ");
			}
			sc.nextLine();
		} while (option < 0 || option > 1);

		if(option == 0) {
			return;
		}

		ClientCopy clientCopy = new ClientCopy(client);
		int editInfo = -1;
		do {
			System.out.println("1 - Nome: "+clientCopy.getName());
			System.out.println("2 - Telefone: "+clientCopy.getPhone());
			System.out.println("3 - Email: "+clientCopy.getEmail());
			System.out.println("4 - Usuário: "+clientCopy.getLogin());
			System.out.println("5 - Senha");
			System.out.println("6 - Cartão de crédito: "+clientCopy.getCreditCard());
			System.out.println("7 - Rua: "+clientCopy.getAddress().getStreet());
			System.out.println("8 - Número: "+clientCopy.getAddress().getNumber());
			System.out.println("9 - Complemento: "+clientCopy.getAddress().getComplement());
			System.out.println("10 - Bairro: "+clientCopy.getAddress().getNeighborhood());
			System.out.println("11 - CEP: "+clientCopy.getAddress().getCep());
			System.out.println("12 - Cidade: "+clientCopy.getAddress().getCity());
			System.out.println("13 - Estado: "+clientCopy.getAddress().getState());
			System.out.println("14 - Salvar alterações");
			System.out.println("0 - Cancelar");
			System.out.println("Informe o campo que deseja editar: ");

			do {
				try {
					editInfo = sc.nextInt();

					if(editInfo < 0 || editInfo > 14) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			}while(editInfo<0 || editInfo>14);

			switch(editInfo) {
				case 1:
					System.out.println("Nome: " + clientCopy.getName());
					System.out.print("Novo nome: ");
					String newName = sc.nextLine();
					clientCopy.setName(newName);
					break;
				case 2:
					System.out.println("Telefone: " + clientCopy.getPhone());
					System.out.print("Novo telefone: ");
					String newPhone = sc.nextLine();
					clientCopy.setPhone(newPhone);
					break;
				case 3:
					System.out.println("Email: " + clientCopy.getEmail());
					System.out.print("Novo email: ");
					String newEmail = sc.nextLine();
					clientCopy.setEmail(newEmail);
					break;
				case 4:
					System.out.println("Usuário: " + clientCopy.getLogin());
					System.out.print("Novo nome de usuário: ");
					String newUsername = sc.nextLine();
					clientCopy.setLogin(newUsername);
					break;
				case 5:
					String newPasswordOne;
					String newPasswordTwo;

					do {
						System.out.print("Nova senha: ");
						newPasswordOne = sc.nextLine();

						System.out.print("Digite a senha novamente: ");
						newPasswordTwo = sc.nextLine();

						if(!newPasswordOne.equals(newPasswordTwo)) {
							System.out.println("As senha não são iguais, por favor, tente novamente");
						}
					} while (!newPasswordOne.equals(newPasswordTwo));

					clientCopy.setPassword(newPasswordOne);
					break;
				case 6:
					System.out.println("Cartão de crédito: " + clientCopy.getCreditCard());
					System.out.print("Novo cartão de crédito: ");
					String newCreditCard = sc.nextLine();
					clientCopy.setCreditCard(newCreditCard);
					break;
				case 7:
					System.out.println("Rua: " + clientCopy.getAddress().getStreet());
					System.out.print("(Endereço) Nova rua: ");
					String newStreet = sc.nextLine();
					clientCopy.getAddress().setStreet(newStreet);
					break;
				case 8:
					System.out.println("Número: " + clientCopy.getAddress().getNumber());
					System.out.print("(Endereço) Novo número: ");
					String newNumber = sc.nextLine();
					clientCopy.getAddress().setNumber(newNumber);
					break;
				case 9:
					System.out.println("Complemento: " + clientCopy.getAddress().getComplement());
					System.out.print("(Endereço) Novo complemento: ");
					String newComplement = sc.nextLine();
					clientCopy.getAddress().setComplement(newComplement);
					break;
				case 10:
					System.out.println("Bairro: " + clientCopy.getAddress().getNeighborhood());
					System.out.print("(Endereço) Novo bairro: ");
					String newNeighborhood = sc.nextLine();
					clientCopy.getAddress().setNeighborhood(newNeighborhood);
					break;
				case 11:
					System.out.println("CEP: " + clientCopy.getAddress().getCep());
					System.out.print("(Endereço) Novo CEP: ");
					String newCep = sc.nextLine();
					clientCopy.getAddress().setCep(newCep);
					break;
				case 12:
					System.out.println("Cidade: " + clientCopy.getAddress().getCity());
					System.out.print("(Endereço) Nova cidade: ");
					String newCity = sc.nextLine();
					clientCopy.getAddress().setCity(newCity);
					break;
				case 13:
					System.out.println("Estado: " + clientCopy.getAddress().getState());
					System.out.print("(Endereço) Novo estado: ");
					String newState = sc.nextLine();
					clientCopy.getAddress().setState(newState);
					break;
				case 14:
					try {
						clientController.edit(client,clientCopy);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
					break;
				case 0:
					System.out.println("Alterações não realizadas...");
			}
		}while(editInfo != 0 && editInfo != 14);

	}
}
