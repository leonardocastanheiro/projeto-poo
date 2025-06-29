package br.ucs.projetosistemaprodutos.views;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import br.ucs.projetosistemaprodutos.controllers.ClientController;
import br.ucs.projetosistemaprodutos.controllers.ProductController;
import br.ucs.projetosistemaprodutos.exceptions.InsufficientStockException;
import br.ucs.projetosistemaprodutos.models.copies.ClientCopy;
import br.ucs.projetosistemaprodutos.models.itens.*;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.utils.StoreManager;

public class ClientView {

	private Store store;
	private final ClientController clientController;
	private final ProductController productController;
	private final Client client;
	private final StoreManager storeManager;

	public ClientView(Store store, Client client) {
		this.client = client;
		this.store = store;
		this.clientController = new ClientController(store);
		this.productController = new ProductController(store);
		this.storeManager = new StoreManager();
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
			System.out.print("Buscar produto (Digite '0' para voltar ao menu): ");
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
				System.out.println(i + 1 + " - Código: " + products.get(i).getProductCode() + " - Nome: " + products.get(i).getName() + " | Preço: R$ " + String.format("%.2f", products.get(i).getStock().getPrice()));
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
				System.out.println("Preço: R$ " + String.format("%.2f", productDetails.getStock().getPrice()));
				System.out.println("----------          ----------");
				try {
					productController.emptyStock(productDetails.getStock());
				}catch(InsufficientStockException e) {
					System.out.println(e.getMessage());
					return;
				}
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
					int quantity = -1;
					do {
						System.out.print("Digite quantas unidades deseja adicionar ao carrinho: ('0' para cancelar)");
						try {
							quantity = sc.nextInt();
							sc.nextLine();
						}catch(InputMismatchException e) {
							System.out.println("Entrada inválida ");
						}
					}while(quantity < 0);		

					if (quantity == 0) {
						System.out.println("Nenhum produto adicionado.");
						return;
					}

					try {
						productController.stockQuantity(productDetails.getStock(), quantity);
						
						client.getShoppingCart().add(productDetails, quantity);
						System.out.println("Adicionando " + quantity + " " + productDetails.getName() + " ao carrinho");
					}catch(InsufficientStockException ise) {
						System.out.println(ise.getMessage());
						int maxQuantityOp = -1;
						System.out.println("Adicionar " + maxQuantity + " ao carrinho? ");
						System.out.println("1 - Sim, continuar");
						System.out.println("0 - Não, cancelar");

						do {
							try {
								maxQuantityOp = sc.nextInt();

								if (maxQuantityOp != 0 && maxQuantityOp != 1) {
									System.out.println("Entrada inválida");
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
						System.out.println("Adicionando " + maxQuantity + " " + productDetails.getName() + " ao carrinho");
					}
					try {
						storeManager.save(store);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
				}
			}

		} while (id != 0);
	}

	public void subCart(Scanner sc) {
		System.out.println("Carrinho de Compras");

		if (client.getShoppingCart().getProducts().isEmpty()) {
			System.out.println("Nenhum produto encontrado!");
			return;
		}

		Map<Product, Integer> productQuantity = client.getShoppingCart().getProducts();

		List<Map.Entry<Product, Integer>> entries = new ArrayList<>(productQuantity.entrySet());

		for (int i = 0; i < entries.size(); i++) {
			Map.Entry<Product, Integer> entry = entries.get(i);
			System.out.println((i + 1) + " - " + entry.getKey().getName() + ": " + entry.getValue() + " unidade" + (entry.getValue() > 1 ? "s" : "") + " - Total: R$" + String.format("%.2f", (entry.getKey().getStock().getPrice() * entry.getValue())));
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
							+ String.format("%.2f", (entry.getKey().getStock().getPrice() * entry.getValue())));
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

				if(entry.getValue() < 1) {
					System.out.println("Erro, nenhum produto desse no carrinho.");
					return;
				}
				System.out.println(entry.getKey().getName() + ": " + entry.getValue() + " unidade" + (entry.getValue() > 1 ? "s" : ""));
				
				Stock productStock = entry.getKey().getStock();
				try {
					productController.emptyStock(productStock);
				}catch(InsufficientStockException ise) {
					System.out.println("Produto indisponível no momento.");
					return;
				}
				
				int quantity = -1;
				do {
					System.out.print("Digite a nova quantidade (Caso queira excluir o item, digite '0'): ");
					try {
						quantity = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Entrada inválida");
					}
					sc.nextLine();
				} while (quantity < 0);

				try {
					productController.stockQuantity(productStock, quantity);
				}catch(InsufficientStockException e) {
					System.out.println(e.getMessage());
					System.out.println("Adicionar " + productStock.getQuantity() + " unidade(s) no carrinho?");					
					System.out.println("1 - Sim, continuar");
					System.out.println("0 - Não, cancelar");
					int maxQuantityOp = -1;
					do {
						try {
							maxQuantityOp = sc.nextInt();

							if (maxQuantityOp != 0 && maxQuantityOp != 1) {
								System.out.println("Entrada inválida");
							}

						} catch (InputMismatchException im) {
							System.out.print("Entrada inválida, digite novamente: ");
						}
						sc.nextLine();
					} while (maxQuantityOp != 0 && maxQuantityOp != 1);

					if (maxQuantityOp == 0) {
						System.out.println("Saindo...");
						return;
					}
					quantity = productStock.getQuantity();
				}
				
				try {
					productController.editCartItem(client, entry.getKey(), quantity);
					storeManager.save(store);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return;
				}

				return;
			case 2:
				Map<Product,Integer> products = client.getShoppingCart().getProducts();
				Iterator<Map.Entry<Product,Integer>> it = products.entrySet().iterator();

				double value = 0;
				double valueICMS = 0;

				while (it.hasNext()) {
					entry = it.next();
					Product product = entry.getKey();
					int desiredQty = entry.getValue();
					int stockQty = product.getStock().getQuantity();


					if (stockQty < desiredQty) {

						if (stockQty < 1) {
							System.out.println(product.getName() + " sem estoque no momento, prosseguindo a compra sem ele.");
							try {
								it.remove();
								storeManager.save(store);
							} catch (Exception e) {
								System.out.println(e.getMessage());
								return;
							}
							continue;
						}


						System.out.println("O produto " + product.getName() +
								" tem apenas " + stockQty + " unidade" + (stockQty > 1 ? "s" : "") +
								" no estoque, deseja comprar essa quantidade?");
						System.out.println("1 - Comprar " + stockQty + " unidade" + (stockQty > 1 ? "s" : ""));
						System.out.println("2 - Excluir o produto '" + product.getName() + "' do Carrinho de Compras");

						option = -1;
						do {
							try {
								option = sc.nextInt();
							} catch (InputMismatchException ignored) {
								sc.nextLine();
							}
							if (option < 1 || option > 2) {
								System.out.println("Entrada inválida, digite novamente: ");
							}
						} while (option < 1 || option > 2);

						switch (option) {
							case 1:
								try {
									productController.editCartItem(client, product, stockQty);
									entry.setValue(stockQty);
								} catch (Exception e) {
									System.out.println(e.getMessage());
									return;
								}
								break;
							case 2:
								try {
									productController.editCartItem(client, product, 0);
								} catch (Exception e) {
									System.out.println(e.getMessage());
									return;
								}
								it.remove();
								continue;
						}
					}

					double price = product.getStock().getPrice();
					value += price * entry.getValue();
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
				Collections.sort(itemOrders);
				order.setItemOrders(itemOrders);
				try {
					clientController.addOrder(order);
				}catch (Exception e) {
					System.out.println(e.getMessage());
					return;
				}

				for (Map.Entry<Product, Integer> newEntry : client.getShoppingCart().getProducts().entrySet()) {
					Product product = newEntry.getKey();
					quantity = newEntry.getValue();

					try {
						productController.changeStock(product, -quantity);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
				}

				System.out.println("Pedido Finalizado!");

				System.out.println("ID do pedido: " + order.getId());
				System.out.println("Data do pedido: " + order.getDateOrder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				System.out.println("Cliente: " + order.getOwner().getName() + " - " + order.getOwner().getEmail());
				System.out.println("Valor total (sem ICMS): R$" + String.format("%.2f", order.getTotalPrice()));
				System.out.println("Valor total (com ICMS): R$" + String.format("%.2f", order.getTotalPriceICMS()));

				try {
					clientController.clearCart(client);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return;
				}

				return;
			case 3:
				try {
					clientController.clearCart(client);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

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
			System.out.println("1 - Pesquisar por número do pedido");
			System.out.println("2 - Pesquisar por data de realização");
			System.out.println("3 - Pesquisar por intervalo de data");
			System.out.println("0 - Sair");
			System.out.println("Escolha uma opção: ");
			
			try {
				op = sc.nextInt();
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println("Entrada inválida.");
			}
		}while(op < 0 ||op > 3);
		
		switch(op) {
			case 1:
				int id = -1;
				System.out.print("Informe o número do pedido (Digite '0' para voltar ao menu): ");
				try {
					id = sc.nextInt();
					sc.nextLine();
					
					if(id == 0) {
						return;
					}
				}catch(InputMismatchException e) {
					System.out.println("Entrada inválida.");
				}
				
				if(id == -1) {
					System.out.println("Algo deu errado.");
				}else {
					Optional<Order> orderOptional = clientController.getOrderById(id, client);
					if (orderOptional.isEmpty()) {
						System.out.println("Nenhum pedido encontrado com esse ID");
						return;
					}
					orders.add(orderOptional.get());
				}
				break;
			case 2:
				LocalDate date = null;
				
				try {
					System.out.print("Informe a data desejada (Digite '0' para voltar ao menu): ");
					String text = sc.nextLine();
					
					try {
						int exitSub = Integer.parseInt(text);

						if (exitSub == 0) {
							return;
						}
					} catch (NumberFormatException ignored) {
					}
					
					date = LocalDate.parse(text, dtf);
				}catch(DateTimeParseException e) {
					System.out.println("Data inválida.");
					return;
				}
				orders = productController.getOrdersByDate(date, client.getOrders());
				break;
			case 3: 
				LocalDate startDate = null;
				LocalDate endDate = null;
				
				try {
					System.out.print("Data de início (Digite '0' para voltar ao menu): ");
					String text1 = sc.nextLine();
					
					try {
						int exitSub = Integer.parseInt(text1);

						if (exitSub == 0) {
							return;
						}
					} catch (NumberFormatException ignored) {}

					startDate = LocalDate.parse(text1, dtf);

					System.out.print("Data final (Digite '0' para voltar ao menu): ");
					String text2 = sc.nextLine();
					
					try {
						int exitSub = Integer.parseInt(text2);

						if (exitSub == 0) {
							return;
						}
					} catch (NumberFormatException ignored) {}
					endDate = LocalDate.parse(text2, dtf);

				}catch(DateTimeParseException e) {
					System.out.println("Data inválida.");
					return;
				}
				
				if(startDate.isAfter(endDate)) {
					System.out.println("Data de início maior e a data final.");
					return;
				}
				orders = productController.getOrdersByDate(startDate, endDate, client.getOrders());
				break;
			case 0:
				System.out.println("Saindo de pedidos...");
				return;				
		}

		if(orders.isEmpty()) {
			System.out.println("Nenhum pedido encontrado");
			return;
		}

		for(Order order: orders) {
			System.out.println(" - ID do Pedido: "+order.getId()+" | Data do Pedido: "+order.getDateOrder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+" | Situação: "+order.getSituation());
		}
		System.out.println("Digite o ID do Pedido que deseja acessar (Digite '0' para voltar ao menu): ");

		Integer id = -1;

		do {
			try {
				id = sc.nextInt();
				sc.nextLine();
				if(id == 0) {
					return;
				}
			} catch (InputMismatchException ignored) {}
		} while (id == -1);

		Order order;

		try {
			order = productController.getOrderByList(id, orders);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

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
			System.out.println("---------- Produto ----------");
			System.out.println("Nome: " + product.getName() + " | Descrição: " + product.getDescription());
			System.out.println("Quantidade: " + quantity + " | Valor unitário: R$ " + String.format("%.2f", product.getStock().getPrice())  + " | Valor Total: R$ " + String.format("%.2f", (itemOrder.getPrice() * quantity)));
		}
		System.out.println("----------          ----------");

		this.editDetailsOrder(order, sc);
	}

	private void editDetailsOrder(Order order, Scanner sc) {
		System.out.println("1 - Editar Situação\n0 - Sair");

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

		if (option == 0) {
			return;
		}

		System.out.println("Situação Atual: " + order.getSituation().toString());

		switch (order.getSituation()) {
			case NEW -> {
				System.out.println("1 - Cancelar\n0 - Sair");
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
				
				List<ItemOrder>products = new ArrayList<ItemOrder>(order.getItemOrders());
				try {
					for(int i = 0; i < products.size(); i++) {
						Product p = products.get(i).getProduct();
						productController.changeStock(p, products.get(i).getQuantity());
					}
					order.setSituation(Situation.CANCELED);
					order.setDateForward(null);
					System.out.println("Pedido Cancelado com sucesso!");
				}catch(Exception e) {
					System.out.println("Erro ao cancelar pedido");
				}
			}

			case FORWARD -> {
				System.out.println("\n1 - Confirmar Entrega\n0 - Sair");
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

				order.setDateDeliver(LocalDate.now());
				order.setSituation(Situation.DELIVERED);
				break;
			}

			case CANCELED -> {
				System.out.println("\nPedido já cancelado!");
				break;
			}

			case DELIVERED -> {
				System.out.println("\nProduto já entregue!");
				break;
			}
		}
	}

	public void subClientAccount(Scanner sc) {
		int option = -1;

		do {
			System.out.println("\n========== MEUS DADOS ==========");
			System.out.println("1 - Dados Pessoais");
			System.out.println("2 - Cartão de Crédito Cadastrado");
			System.out.println("3 - Dados de Login");
			System.out.println("4 - Editar Dados");
			System.out.println("0 - Voltar para o Menu Principal");
			System.out.print("Escolha uma Opção: ");

			try {
				option = sc.nextInt();
				sc.nextLine();

				System.out.println("\n-------------------------------------");

				switch(option) {
					case 1:
						showPersonalData();
						break;

					case 2:
						showCreditCardData();
						break;

					case 3:
						showLoginData();
						break;

					case 4:
						editDataMenu(sc);
						break;

					case 0:
						System.out.println("Voltando ao menu principal...");
						break;

					default:
						System.out.println("Opção inválida! Por favor, escolha entre 0 e 4.");
				}

				if (option != 0) {
					System.out.println("\nPressione Enter para continuar...");
					sc.nextLine();
				}

			} catch (InputMismatchException e) {
				System.out.println("Erro: Por favor, digite um número válido.");
				sc.nextLine(); // Limpar input inválido
				option = -1; // Continuar loop
			} catch (Exception e) {
				System.out.println("Erro inesperado: " + e.getMessage());
				option = 0; // Sair do loop em caso de erro grave
			}

		} while (option != 0);
	}
	//--------------------------------------------------------------------------------------------------------
	private void showPersonalData() {
		System.out.println("\n---------- DADOS PESSOAIS -----------");
		System.out.println("Nome: " + client.getName());
		System.out.println("Telefone: " + client.getPhone());
		System.out.println("E-mail: " + client.getEmail());

		System.out.println("\n---------- ENDEREÇO ----------");
		if (client.getAddress() != null) {
			System.out.println("Logradouro: " + client.getAddress().getStreet() +
					", " + client.getAddress().getNumber());
			System.out.println("Bairro: " + client.getAddress().getNeighborhood());
			System.out.println("CEP: " + client.getAddress().getCep());
			System.out.println("Cidade: " + client.getAddress().getCity() +
					", " + client.getAddress().getState());

			if (client.getAddress().getComplement() != null &&
					!client.getAddress().getComplement().isEmpty()) {
				System.out.println("Complemento: " + client.getAddress().getComplement());
			}
		} else {
			System.out.println("Endereço não cadastrado");
		}
	}
//--------------------------------------------------------------------------------------------------------
	private void showCreditCardData() {
		System.out.println("\n---------- DADOS DO CARTÃO ----------");
		if (client.getCreditCard() != null && !client.getCreditCard().isEmpty()) {
			// Mostra apenas os últimos 4 dígitos por segurança
			String maskedNumber = "**** **** **** " +
					client.getCreditCard().substring(client.getCreditCard().length() - 4);
			System.out.println("Número: " + maskedNumber);
		} else {
			System.out.println("Nenhum cartão de crédito cadastrado");
		}
	}
//--------------------------------------------------------------------------------------------------------
	private void editDataMenu(Scanner sc) {
		int option;
		ClientCopy clientCopy = new ClientCopy(client);

		do {
			try {
				System.out.println("\n---------- EDITAR DADOS ----------");
				System.out.println("1 - Editar Dados Pessoais");
				System.out.println("2 - Editar Cartão de Crédito");
				System.out.println("3 - Editar Senha");
				System.out.println("4 - Editar Endereço");
				System.out.println("0 - Voltar");
				System.out.print("Escolha uma Opção: ");

				option = sc.nextInt();
				sc.nextLine(); // Limpa o buffer

				switch(option) {
					case 1:
						editPersonalData(sc, clientCopy);
						break;
					case 2:
						editCreditCard(sc, clientCopy);
						break;
					case 3:
						editPassword(sc, clientCopy);
						break;
					case 4:
						editAddress(sc, clientCopy);
						break;
					case 0:
						break;
					default:
						System.out.println("Opção inválida!");
				}

			} catch (InputMismatchException e) {
				System.out.println("Erro: Digite um número válido.");
				sc.nextLine(); // Limpa o input inválido
				option = -1; // Força continuar no loop
			} catch (Exception e) {
				System.out.println("Erro inesperado: " + e.getMessage());
				option = 0; // Sai do menu em caso de erro grave
			}

		} while (option != 0);
	}
//--------------------------------------------------------------------------------------------------------
	private void editPersonalData(Scanner sc, ClientCopy clientCopy) {
		int editInfo;
		do {
			System.out.println("\n---------- DADOS PESSOAIS ----------");
			System.out.println("1 - Nome: " + clientCopy.getName());
			System.out.println("2 - Telefone: " + clientCopy.getPhone());
			System.out.println("3 - Email: " + clientCopy.getEmail());
			System.out.println("4 - Usuário: " + clientCopy.getLogin());
			System.out.println("5 - Salvar alterações");
			System.out.println("0 - Cancelar");
			System.out.print("Informe o campo que deseja editar: ");

			editInfo = getValidInput(sc, 0, 5);

			switch(editInfo) {
				case 1:
					System.out.println("Nome: " + clientCopy.getName());
					System.out.print("Novo nome: ");
					clientCopy.setName(sc.nextLine());
					break;
				case 2:
					System.out.println("Telefone: " + clientCopy.getPhone());
					System.out.print("Novo telefone: ");
					clientCopy.setPhone(sc.nextLine());
					break;
				case 3:
					System.out.println("Email: " + clientCopy.getEmail());
					System.out.print("Novo email: ");
					clientCopy.setEmail(sc.nextLine());
					break;
				case 4:
					System.out.println("Usuário: " + clientCopy.getLogin());
					System.out.print("Novo nome de usuário: ");
					clientCopy.setLogin(sc.nextLine());
					break;
				case 5:
					try {
						clientController.edit(client, clientCopy);
						System.out.println("Dados pessoais atualizados com sucesso!");
						return;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
			}
		} while (editInfo != 0);
		System.out.println("Alterações não salvas...");
	}
//--------------------------------------------------------------------------------------------------------
	private void editCreditCard(Scanner sc, ClientCopy clientCopy) {
		System.out.println("\n---------- CARTÃO DE CRÉDITO ----------");
		System.out.println("Cartão atual: " + clientCopy.getCreditCard());
		System.out.print("Novo cartão de crédito (deixe em branco para cancelar): ");
		String newCreditCard = sc.nextLine();

		if (!newCreditCard.isEmpty()) {
			clientCopy.setCreditCard(newCreditCard);
			try {
				clientController.edit(client, clientCopy);
				System.out.println("Cartão de crédito atualizado com sucesso!");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Operação cancelada.");
		}
	}
//--------------------------------------------------------------------------------------------------------
	private void editPassword(Scanner sc, ClientCopy clientCopy) {
		System.out.println("\n---------- ALTERAR SENHA ----------");
		String newPasswordOne;
		String newPasswordTwo;

		do {
			System.out.print("Nova senha: ");
			newPasswordOne = sc.nextLine();

			System.out.print("Digite a senha novamente: ");
			newPasswordTwo = sc.nextLine();

			if (!newPasswordOne.equals(newPasswordTwo)) {
				System.out.println("As senhas não coincidem, por favor, tente novamente");
			}
		} while (!newPasswordOne.equals(newPasswordTwo));

		clientCopy.setPassword(newPasswordOne);
		try {
			clientController.edit(client, clientCopy);
			System.out.println("Senha alterada com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
//--------------------------------------------------------------------------------------------------------
	private void editAddress(Scanner sc, ClientCopy clientCopy) {
		int editInfo;
		do {
			System.out.println("\n---------- ENDEREÇO ----------");
			System.out.println("1 - Rua: " + clientCopy.getAddress().getStreet());
			System.out.println("2 - Número: " + clientCopy.getAddress().getNumber());
			System.out.println("3 - Complemento: " + clientCopy.getAddress().getComplement());
			System.out.println("4 - Bairro: " + clientCopy.getAddress().getNeighborhood());
			System.out.println("5 - CEP: " + clientCopy.getAddress().getCep());
			System.out.println("6 - Cidade: " + clientCopy.getAddress().getCity());
			System.out.println("7 - Estado: " + clientCopy.getAddress().getState());
			System.out.println("8 - Salvar alterações");
			System.out.println("0 - Cancelar");
			System.out.print("Informe o campo que deseja editar: ");

			editInfo = getValidInput(sc, 0, 8);

			switch(editInfo) {
				case 1:
					System.out.println("Rua: " + clientCopy.getAddress().getStreet());
					System.out.print("Nova rua: ");
					clientCopy.getAddress().setStreet(sc.nextLine());
					break;
				case 2:
					System.out.println("Número: " + clientCopy.getAddress().getNumber());
					System.out.print("Novo número: ");
					clientCopy.getAddress().setNumber(sc.nextLine());
					break;
				case 3:
					System.out.println("Complemento: " + clientCopy.getAddress().getComplement());
					System.out.print("Novo complemento: ");
					clientCopy.getAddress().setComplement(sc.nextLine());
					break;
				case 4:
					System.out.println("Bairro: " + clientCopy.getAddress().getNeighborhood());
					System.out.print("Novo bairro: ");
					clientCopy.getAddress().setNeighborhood(sc.nextLine());
					break;
				case 5:
					System.out.println("CEP: " + clientCopy.getAddress().getCep());
					System.out.print("Novo CEP: ");
					clientCopy.getAddress().setCep(sc.nextLine());
					break;
				case 6:
					System.out.println("Cidade: " + clientCopy.getAddress().getCity());
					System.out.print("Nova cidade: ");
					clientCopy.getAddress().setCity(sc.nextLine());
					break;
				case 7:
					System.out.println("Estado: " + clientCopy.getAddress().getState());
					System.out.print("Novo estado: ");
					clientCopy.getAddress().setState(sc.nextLine());
					break;
				case 8:
					try {
						clientController.edit(client, clientCopy);
						System.out.println("Endereço atualizado com sucesso!");
						return;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
			}
		} while (editInfo != 0);
		System.out.println("Alterações não salvas...");
	}
//--------------------------------------------------------------------------------------------------------
	private int getValidInput(Scanner sc, int min, int max) {
		int input;
		while (true) {
			try {
				input = sc.nextInt();
				sc.nextLine(); // Limpar o buffer

				if (input < min || input > max) {
					throw new InputMismatchException();
				}
				return input;
			} catch (InputMismatchException e) {
				System.out.print("Entrada inválida, digite um número entre " + min + " e " + max + ": ");
				sc.nextLine(); // Limpar o input inválido
			}
		}
	}
//--------------------------------------------------------------------------------------------------------

	private void showLoginData() {
		System.out.println("\n---------- DADOS DE LOGIN ----------");
		System.out.println("Login: " + client.getLogin());
		System.out.println("Senha: ********");
	}
//--------------------------------------------------------------------------------------------------------

}

