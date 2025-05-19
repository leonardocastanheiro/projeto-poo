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

	private final Store store;
	private AdminController adminController;
	private final ClientController clientController;
	private SupplierController supplierController;
	private ProductController productController;

	public AdminView(Store store) {
		this.store = store;
		this.adminController = new AdminController(store);
		this.clientController = new ClientController(store);
		this.supplierController = new SupplierController(store);
		this.productController = new ProductController(store);
	}
	
	public void show(Scanner sc) throws Exception {
		int option = -1;
    	
		do {
			System.out.println("Escolha uma opção:");
			System.out.println("1  - Clientes");
			System.out.println("2  - Fornecedores");
			System.out.println("3  - Produtos");
			System.out.println("4  - Estoque");
			System.out.println("5  - Pedidos");
			System.out.println("0  - Sair");

			do {
				try {
					option = sc.nextInt();

					if(option < 0 || option > 5) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			} while (option<0 || option>5);

			switch (option) {
				case 1:
					this.subClients(sc, store);
					break;
				case 2:
					this.subSuppliers(sc, store);
					break;
				case 3:
					this.subProducts(sc, store);
					break;
				case 4:
					this.subStock(sc, store);
					break;
				case 5:
					this.subOrders(sc, store);
					break;
				case 0:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Não encotrado...");
			}
			System.out.println("------------------");
		} while (option != 0);
		sc.close();
	}

    public void subClients(Scanner sc, Store store) {
    	int subOption = -1;
    	ClientController newClient = new ClientController(store);
    	do {
			System.out.println("------------------");
			System.out.println("Escolha uma opção:");
    		System.out.println(" 1  - Adicionar cliente");
    		System.out.println(" 2  - Ver clientes");
    		System.out.println(" 3  - Atualizar cliente");
    		System.out.println(" 4  - Excluir cliente");
    		System.out.println(" 0  - Sair");

			do {
				try {
					subOption = sc.nextInt();

					if(subOption < 0 || subOption > 4) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			} while (subOption<0 || subOption>4);
			
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
					try {
						clientController.showArray();
					} catch (Exception e) {
						System.out.println(e.getMessage());
                    }
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
					int editInfo = -1;
					do {
						System.out.println("1 - Nome: ");
						System.out.println("2 - Telefone: ");
						System.out.println("3 - Email: ");
						System.out.println("4 - Usuário: ");
						System.out.println("5 - Senha: ");
						System.out.println("6 - Cartão de crédito: ");
						System.out.println("7 - Rua: ");
						System.out.println("8 - Número: ");
						System.out.println("9 - Complemento: ");
						System.out.println("10 - Bairro: ");
						System.out.println("11 - CEP: ");
						System.out.println("12 - Cidade: ");
						System.out.println("13 - Estado: ");
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
								System.out.println("Nome: " + client1.getName());
								System.out.print("Novo nome: ");
								String newName = sc.nextLine();
								clientCopy.setName(newName);
								break;
							case 2:
								System.out.println("Telefone: " + client1.getPhone());
								System.out.print("Novo telefone: ");
								String newPhone = sc.nextLine();
								clientCopy.setPhone(newPhone);
								break;
							case 3:
								System.out.println("Email: " + client1.getEmail());
								System.out.print("Novo email: ");
								String newEmail = sc.nextLine();
								clientCopy.setEmail(newEmail);
								break;
							case 4:
								System.out.println("Usuário: " + client1.getLogin());
								System.out.print("Novo nome de usuário: ");
								String newUsername = sc.nextLine();
								clientCopy.setLogin(newUsername);
								break;
							case 5:
								System.out.println("Senha: " + client1.getPassword());
								System.out.print("Nova senha: ");
								String newPassword = sc.nextLine();
								clientCopy.setPassword(newPassword);
								break;
							case 6:
								System.out.println("Cartão de crédito: " + client1.getCreditCard());
								System.out.print("Novo cartão de crédito: ");
								String newCreditCard = sc.nextLine();
								clientCopy.setCreditCard(newCreditCard);
								break;
							case 7:
								System.out.println("Rua: " + client1.getAddress().getStreet());
								System.out.print("(Endereço) Nova rua: ");
								String newStreet = sc.nextLine();
								clientCopy.getAddress().setStreet(newStreet);
								break;
							case 8:
								System.out.println("Número: " + client1.getAddress().getNumber());
								System.out.print("(Endereço) Novo número: ");
								String newNumber = sc.nextLine();
								clientCopy.getAddress().setNumber(newNumber);
								break;
							case 9:
								System.out.println("Complemento: " + client1.getAddress().getComplement());
								System.out.print("(Endereço) Novo complemento: ");
								String newComplement = sc.nextLine();
								clientCopy.getAddress().setComplement(newComplement);
								break;
							case 10:
								System.out.println("Bairro: " + client1.getAddress().getNeighborhood());
								System.out.print("(Endereço) Novo bairro: ");
								String newNeighborhood = sc.nextLine();
								clientCopy.getAddress().setNeighborhood(newNeighborhood);
								break;
							case 11:
								System.out.println("CEP: " + client1.getAddress().getCep());
								System.out.print("(Endereço) Novo CEP: ");
								String newCep = sc.nextLine();
								clientCopy.getAddress().setCep(newCep);
								break;
							case 12:
								System.out.println("Cidade: " + client1.getAddress().getCity());
								System.out.print("(Endereço) Nova cidade: ");
								String newCity = sc.nextLine();
								clientCopy.getAddress().setCity(newCity);
								break;
							case 13:
								System.out.println("Estado: " + client1.getAddress().getState());
								System.out.print("(Endereço) Novo estado: ");
								String newState = sc.nextLine();
								clientCopy.getAddress().setState(newState);
								break;
							case 14:
								try {
									clientController.edit(client1,clientCopy);
								} catch (Exception e) {
									System.out.println(e.getMessage());
									return;
								}
								break;
							case 0:
								System.out.println("Alterações não realizadas...");
						}
					}while(editInfo != 0 && editInfo != 14);
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
					break;
				default:
					System.out.println("Erro...");
			}
    	}while(subOption != 0);
    }
    
    public void subSuppliers(Scanner sc, Store store) throws Exception {
    	int subOption = -1;
    	SupplierController newSupplier = new SupplierController(store);
    	do {
			System.out.println("------------------");
			System.out.println("Escolha uma opção:");
    		System.out.println(" 1  - Adicionar fornecedor");
    		System.out.println(" 2  - Ver fornecedores");
    		System.out.println(" 3  - Atualizar fornecedor");
    		System.out.println(" 4  - Excluir fornecedor");
    		System.out.println(" 0  - Sair");

			do {
				try {
					subOption = sc.nextInt();

					if(subOption < 0 || subOption > 4) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			} while (subOption<0 || subOption>4);
			
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
					break;
				default:
					System.out.println("Erro...");
			}
    	}while(subOption != 0);
		
	}
    
    public void subProducts(Scanner sc, Store store) {
    	int subOption = -1;
    	do {
			System.out.println("------------------");
			System.out.println("Escolha uma opção:");
    		System.out.println(" 1  - Adicionar produto");
    		System.out.println(" 2  - Ver produtos");
    		System.out.println(" 3  - Atualizar produto");
    		System.out.println(" 4  - Excluir produto");
    		System.out.println(" 0  - Sair");

			do {
				try {
					subOption = sc.nextInt();

					if(subOption < 0 || subOption > 4) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			} while (subOption<0 || subOption>4);
			
			switch (subOption) {
				case 1:
			    	System.out.print("Nome: ");
			    	String name = sc.nextLine();
			    	System.out.print("Descrição: ");
			    	String description = sc.nextLine();
			    	System.out.print("Nome do fornecedor: ");
			    	String nameSupplier = sc.nextLine();

					try {
						Product product = new Product(name, description, supplierController.getByName(nameSupplier));
						productController.create(product);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.println(productController.toString());
					break;
				case 3:
					System.out.println(productController.toString());
					System.out.print("Digite o ID do produto que deseja editar: ");

					int id=-1;

					do {
						try {
							id = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.print("Tipo digitado não corresponde a um ID, digite novamente: ");
						}
					}while (id == -1);

					Product updateProduct = null;

					try {
						updateProduct = productController.getById(id);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

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
					    	System.out.print("Escolha o campo que deseja editar: ");
					    	updateOp = -1;

							do {
								try {
									updateOp = sc.nextInt();

									if(updateOp < 0 || updateOp > 5) {
										throw new InputMismatchException("Entrada inválida");
									}

								} catch (InputMismatchException e) {
									System.out.print("Entrada inválida, digite novamente: ");
								}
								sc.nextLine();
							} while (updateOp<0 || updateOp>5);
					    	
					    	switch(updateOp) {
					    		case 1:
					    			System.out.print("Novo nome: ");
					    			updateProductCopy.setName(sc.nextLine());
					    			break;
					    		case 2:
					    			System.out.print("Nova descrição: ");
					    			updateProductCopy.setDescription(sc.nextLine());
					    			break;
					    		case 3: 
					    			SupplierController newSupplier = new SupplierController(store);
					    			System.out.print("Nome do novo fornecedor: ");

									Supplier foundSupplier = null;

									try {
										foundSupplier = newSupplier.getByName(sc.nextLine());
									} catch (Exception e) {
										System.out.println(e.getMessage());
									}
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
									try {
										productController.update(updateProductCopy, updateProduct);
									} catch (Exception e) {
										System.out.println(e.getMessage());
									}
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
					System.out.println(productController.toString());

					System.out.print("Digite o ID do produto que deseja excluir: ");
					int id1 = -1;

					do {
						try {
							id1 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("Tipo digitado não corresponde a um ID, digite novamente: ");
						}
					} while (id1 == -1);

					try {
						Product product = productController.getById(id1);
						productController.delete(product);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

					break;
				case 0:
					System.out.println("Saindo de 'Produtos'...");
					break;
				default:
					System.out.println("Erro...");
			}
    	}while(subOption != 0);
	}
    
    public void subStock(Scanner sc, Store store) {
    	int subOption = -1;
    	    	
    	do {
			System.out.println("------------------");
			System.out.println("Escolha uma opção:");
    		System.out.println(" 1  - Adicionar estoque");
    		System.out.println(" 2  - Atualizar estoque");
    		System.out.println(" 0  - Sair");

			do {
				try {
					subOption = sc.nextInt();

					if(subOption < 0 || subOption > 2) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			} while (subOption<0 || subOption>2);
			
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
					break;
				default:
					System.out.println("Erro...");
			}
    	}while(subOption != 0);
		
	}
    
    public void subOrders(Scanner sc, Store store) {
		System.out.println("------------------");
		System.out.println("EM PRODUÇÃO");
	}
}
