package br.ucs.projetosistemaprodutos.views;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.ucs.projetosistemaprodutos.controllers.*;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.copies.ClientCopy;
import br.ucs.projetosistemaprodutos.models.copies.ProductCopy;
import br.ucs.projetosistemaprodutos.models.copies.SupplierCopy;
import br.ucs.projetosistemaprodutos.models.itens.*;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;
import br.ucs.projetosistemaprodutos.models.person.User;

public class AdminView {

	private final Store store;
	private AdminController adminController;
	private final ClientController clientController;
	private final SupplierController supplierController;
	private final ProductController productController;


	public AdminView(Store store) {
		this.store = store;
		this.adminController = new AdminController(store);
		this.clientController = new ClientController(store);
		this.supplierController = new SupplierController(store);
		this.productController = new ProductController(store);
	}
	
	public void show(Scanner sc) {
		int option = -1;
    	
		do {
			System.out.println("Escolha uma opção:");
			System.out.println("1  - Clientes");
			System.out.println("2  - Fornecedores");
			System.out.println("3  - Produtos");
			System.out.println("4  - Pedidos");
			System.out.println("0  - Sair");

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
					this.subClients(sc, store);
					break;
				case 2:
					this.subSuppliers(sc, store);
					break;
				case 3:
					this.subProducts(sc, store);
					break;
				case 4:
					this.subOrders(sc, store);
					break;					
				case 0:
					System.out.println("Saindo...");
					return;
				default:
					System.out.println("Não encontrado...");
			}
			System.out.println("------------------");
		} while (option != 0);
	}

    public void subClients(Scanner sc, Store store) {
    	int subOption = -1;
    	
    	do {
			System.out.println("------------------");
			System.out.println("Escolha uma opção:");
    		System.out.println(" 1  - Adicionar cliente");
    		System.out.println(" 2  - Procurar cliente");
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
					System.out.println("Buscar Cliente: ");
					String text = sc.nextLine();

					List<Client> clients;

					try {
						clients = clientController.getByText(text);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<clients.size();i++) {
						System.out.println(i+1+" - Nome: "+clients.get(i).getName());
					}
                    break;
				case 3:
					System.out.println("Buscar Cliente: ");
					String text1 = sc.nextLine();

					List<Client> clients1;

					try {
						clients1 = clientController.getByText(text1);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<clients1.size();i++) {
						System.out.println(i+1+" - Nome: "+clients1.get(i).getName());
					}
					System.out.println("0 - Sair");

					System.out.println("--------");
					System.out.print("Digite o número do cliente: ");

					int id = -1;
					Client client1;

					do {
						try {
							id = sc.nextInt();

						} catch (InputMismatchException e) {
							System.out.print("Entrada inválida, digite novamente: ");
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return;
						}
					} while (id<0 || id>clients1.size());

					if(id == 0) {
						return;
					}

					client1 = clients1.get(id-1);

					ClientCopy clientCopy = new ClientCopy(client1);
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
								System.out.print("Nova senha: ");
								String newPassword = sc.nextLine();
								clientCopy.setPassword(newPassword);
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
					System.out.println("Buscar Cliente: ");
					String text2 = sc.nextLine();

					List<Client> clients2;

					try {
						clients2 = clientController.getByText(text2);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<clients2.size();i++) {
						System.out.println(i+1+" - Nome: "+clients2.get(i).getName());
					}
					System.out.println("0 - Sair");

					System.out.println("--------");
					System.out.print("Digite o número do cliente: ");

					int id1 = -1;
					Client client2;

					do {
						try {
							id1 = sc.nextInt();

						} catch (InputMismatchException e) {
							System.out.print("Entrada inválida, digite novamente: ");
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return;
						}
					} while (id1<0 || id1>clients2.size());

					if(id1 == 0) {
						return;
					}

					client2 = clients2.get(id1-1);

					try {
						clientController.delete(client2);
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
    
    public void subSuppliers(Scanner sc, Store store) {
    	int subOption = -1;
    	
    	do {
			System.out.println("------------------");
			System.out.println("Escolha uma opção:");
    		System.out.println(" 1  - Adicionar fornecedor");
    		System.out.println(" 2  - Buscar fornecedor");
    		System.out.println(" 3  - Atualizar fornecedor");
    		System.out.println(" 4  - Excluir fornecedor");
    		System.out.println(" 5  - Ver produtos de um fornecedor");
    		System.out.println(" 0  - Sair");

			do {
				try {
					subOption = sc.nextInt();

					if(subOption < 0 || subOption > 5) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			} while (subOption<0 || subOption>5);
			
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

					try {
						supplierController.create(supplier);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.print("Buscar Fornecedor: ");;
					String text = sc.nextLine();

					List<Supplier> suppliers;

					try {
						suppliers = supplierController.getByText(text);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<suppliers.size();i++) {
						System.out.println(i+1+" - Nome: "+suppliers.get(i).getName());
					}
					break;
				case 3:
					System.out.print("Buscar Fornecedor: ");;
					String text1 = sc.nextLine();

					List<Supplier> suppliers1;

					try {
						suppliers1 = supplierController.getByText(text1);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<suppliers1.size();i++) {
						System.out.println(i+1+" - Nome: "+suppliers1.get(i).getName());
					}
					System.out.println("0 - Sair");
					System.out.println("--------");
					System.out.print("Digite o número do fornecedor que deseja editar: ");

					int id = -1;
					Supplier supplier1;

					do {
						try {
							id = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.print("Entrada inválida, digite novamente: ");
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return;
						}
					} while (id<0 || id > suppliers1.size());

					if(id == 0) {
						return;
					}

					supplier1 = suppliers1.get(id-1);

					SupplierCopy supplierCopy = new SupplierCopy(supplier1);
					int editInfo = -1;
					do {
						System.out.println("1 - Nome: "+supplierCopy.getName());
						System.out.println("2 - Telefone: "+supplierCopy.getPhone());
						System.out.println("3 - Email: "+supplierCopy.getEmail());
						System.out.println("4 - Descrição: "+supplierCopy.getDescription());
						System.out.println("5 - Rua: "+supplierCopy.getAddress().getStreet());
						System.out.println("6 - Número: "+supplierCopy.getAddress().getNumber());
						System.out.println("7 - Complemento: "+supplierCopy.getAddress().getComplement());
						System.out.println("8 - Bairro: "+supplierCopy.getAddress().getNeighborhood());
						System.out.println("9 - CEP: "+supplierCopy.getAddress().getCep());
						System.out.println("10 - Cidade: "+supplierCopy.getAddress().getCity());
						System.out.println("11 - Estado: "+supplierCopy.getAddress().getState());
						System.out.println("12 - Salvar alterações");
						System.out.println("0 - Cancelar");
						System.out.println("Informe o campo que deseja editar: ");
						
						do {
							try {
								editInfo = sc.nextInt();

								if(editInfo < 0 || editInfo > 12) {
									throw new InputMismatchException("Entrada inválida");
								}

							} catch (InputMismatchException e) {
								System.out.print("Entrada inválida, digite novamente: ");
							}
							sc.nextLine();
						}while(editInfo<0 || editInfo>12);
						
						switch(editInfo) {
							case 1:
								System.out.println("Nome: " + supplierCopy.getName());
								System.out.print("Novo nome: ");
								String newName = sc.nextLine();
								supplierCopy.setName(newName);
								break;
							case 2:
								System.out.println("Telefone: " + supplierCopy.getPhone());
								System.out.print("Novo telefone: ");
								String newPhone = sc.nextLine();
								supplierCopy.setPhone(newPhone);
								break;
							case 3:
								System.out.println("Email: " + supplierCopy.getEmail());
								System.out.print("Novo email: ");
								String newEmail = sc.nextLine();
								supplierCopy.setEmail(newEmail);
								break;
							case 4:
								System.out.println("Descrição: " + supplierCopy.getDescription());
								System.out.print("Nova descrição: ");
								String newDescription = sc.nextLine();
								supplierCopy.setDescription(newDescription);
								break;
							case 5:
								System.out.println("Rua: " + supplierCopy.getAddress().getStreet());
								System.out.print("(Endereço) Nova rua: ");
								String newStreet = sc.nextLine();
								supplierCopy.getAddress().setStreet(newStreet);
								break;
							case 6:
								System.out.println("Número: " + supplierCopy.getAddress().getNumber());
								System.out.print("(Endereço) Novo número: ");
								String newNumber = sc.nextLine();
								supplierCopy.getAddress().setNumber(newNumber);
								break;
							case 7:
								System.out.println("Complemento: " + supplierCopy.getAddress().getComplement());
								System.out.print("(Endereço) Novo complemento: ");
								String newComplement = sc.nextLine();
								supplierCopy.getAddress().setComplement(newComplement);
								break;
							case 8:
								System.out.println("Bairro: " + supplierCopy.getAddress().getNeighborhood());
								System.out.print("(Endereço) Novo bairro: ");
								String newNeighborhood = sc.nextLine();
								supplierCopy.getAddress().setNeighborhood(newNeighborhood);
								break;
							case 9:
								System.out.println("CEP: " + supplierCopy.getAddress().getCep());
								System.out.print("(Endereço) Novo CEP: ");
								String newCep = sc.nextLine();
								supplierCopy.getAddress().setCep(newCep);
								break;
							case 10:
								System.out.println("Cidade: " + supplierCopy.getAddress().getCity());
								System.out.print("(Endereço) Nova cidade: ");
								String newCity = sc.nextLine();
								supplierCopy.getAddress().setCity(newCity);
								break;
							case 11:
								System.out.println("Estado: " + supplierCopy.getAddress().getState());
								System.out.print("(Endereço) Novo estado: ");
								String newState = sc.nextLine();
								supplierCopy.getAddress().setState(newState);
								break;
							case 12:
								try {
									supplierController.edit(supplier1,supplierCopy);
								} catch (Exception e) {
									System.out.println(e.getMessage());
									return;
								}
								break;
							case 0:
								System.out.println("Alterações não realizadas...");
						}
					}while(editInfo != 0 && editInfo != 12);
					break;
				case 4:
					System.out.print("Buscar Fornecedor: ");;
					String text2 = sc.nextLine();

					List<Supplier> suppliers2;

					try {
						suppliers2 = supplierController.getByText(text2);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<suppliers2.size();i++) {
						System.out.println(i+1+" - Nome: "+suppliers2.get(i).getName());
					}
					System.out.println("0 - Sair");
					System.out.println("--------");
					System.out.print("Digite o número do fornecedor que deseja excluir: ");

					int id1 = -1;
					Supplier supplier2;

					do {
						try {
							id1 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.print("Entrada inválida, digite novamente: ");
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return;
						}
					} while (id1<0 || id1 > suppliers2.size());

					if(id1 == 0) {
						return;
					}

					supplier2 = suppliers2.get(id1-1);

					try {
						supplierController.delete(supplier2);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
                    }

					System.out.println("Fornecedor excluído com sucesso!");
					break;
					
				case 5:
					System.out.print("Buscar Fornecedor: ");;
					String text3 = sc.nextLine();

					List<Supplier> suppliers3;

					try {
						suppliers3 = supplierController.getByText(text3);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<suppliers3.size();i++) {
						System.out.println(i+1+" - Nome: "+suppliers3.get(i).getName());
					}
					System.out.println("0 - Sair");
					System.out.println("--------");
					System.out.print("Digite o número do fornecedor que deseja visualizar os produtos: ");

					int id2 = -1;
					Supplier supplier3;

					do {
						try {
							id2 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.print("Entrada inválida, digite novamente: ");
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return;
						}
					} while (id2<0 || id2 > suppliers3.size());

					if(id2 == 0) {
						return;
					}

					supplier3 = suppliers3.get(id2-1);

				    for(Product product : supplier3.getProducts().getAllProducts()) {
						System.out.println("ID: "+product.getId() + " | Nome: "+product.getName());
					}
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
    		System.out.println(" 2  - Procurar produto");
    		System.out.println(" 3  - Atualizar produto");
    		System.out.println(" 4  - Excluir produto");
			System.out.println(" 5  - Procurar produto por fornecedor");
    		System.out.println(" 0  - Sair");

			do {
				try {
					subOption = sc.nextInt();

					if(subOption < 0 || subOption > 5) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			} while (subOption<0 || subOption>5);
			
			switch (subOption) {
				case 1:
			    	System.out.print("Nome: ");
			    	String name = sc.nextLine();
			    	System.out.print("Descrição: ");
			    	String description = sc.nextLine();
			    	System.out.print("Código: ");
			    	String productCode = sc.nextLine();
					System.out.print("Buscar Fornecedor: ");;
					String text = sc.nextLine();

					List<Supplier> suppliers;

					try {
						suppliers = supplierController.getByText(text);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<suppliers.size();i++) {
						System.out.println(i+1+" - Nome: "+suppliers.get(i).getName());
					}
					System.out.println("0 - Sair");
					System.out.println("--------");
					System.out.print("Digite o número do fornecedor que deseja relacionar: ");

					int id = -1;
					Supplier supplier;

					do {
						try {
							id = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.print("Entrada inválida, digite novamente: ");
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return;
						}
					} while (id<0 || id > suppliers.size());

					if(id == 0) {
						return;
					}

					supplier = suppliers.get(id-1);

			    	System.out.print("Quantidade em estoque: ");
			    	int quantity = -1;
			    	do {
				    	try {
				    		quantity = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.print("Entrada inválida, digite novamente: ");
							
						}
				    	sc.nextLine();
			    	}while(quantity == -1);
			    	
				    System.out.print("Preço: ");
				    double price = -1;
				    do {
					    try {
					    	price = sc.nextDouble();
						} catch (InputMismatchException e) {
							System.out.print("Entrada inválida, digite novamente: ");
						}
				    	sc.nextLine();
				    }while(price == -1);
				    
				    Stock stock = new Stock(quantity, price);
				    	
					try {
						Product product = new Product(name, description, stock, supplier, productCode);
						productController.create(product);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.print("Buscar Produto: ");;
					String text1 = sc.nextLine();

					List<Product> products;

					try {
						products = productController.getByText(text1);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<products.size();i++) {
						System.out.println(i+1+" - Código: "+ products.get(i).getProductCode() + " - Nome: "+products.get(i).getName());
					}
					break;
				case 3:
					System.out.print("Buscar Produto: ");;
					String text2 = sc.nextLine();

					List<Product> products1;

					try {
						products1 = productController.getByText(text2);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<products1.size();i++) {
						System.out.println(i+1+" - Código: "+ products1.get(i).getProductCode() + " - Nome: "+products1.get(i).getName());
					}
					System.out.println("0 - Sair");
					System.out.println("--------");
					System.out.println("Digite o número do produto que deseja editar: ");

					int id1 = -1;

					Product product;

					do {
						try {
							id1 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.print("Entrada inválida, digite novamente: ");
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return;
						}
					} while (id1<0 || id1 > products1.size());

					if(id1 == 0) {
						return;
					}

					product = products1.get(id1-1);

					ProductCopy productCopy = new ProductCopy(product);
					int newQuantity = product.getStock().getQuantity();
					double newPrice = product.getStock().getPrice();
					Stock newStock;
					
					int editInfo = -1;

					do {
						System.out.println("EDITAR PRODUTO: ");
						System.out.println("1. Código: " + productCopy.getProductCode());
						System.out.println("2. Nome: " + productCopy.getName());
				    	System.out.println("3. Descrição: " + productCopy.getDescription());
				    	System.out.println("4. Fornecedor: " + productCopy.getSupplier().getName());
				    	System.out.println("5. Quantidade em estoque: " + productCopy.getStock().getQuantity());
				    	System.out.println("6. Preço: "+ productCopy.getStock().getPrice());
				    	System.out.println("7. Salvar");
				    	System.out.println("0. Cancelar");
				    	System.out.print("Escolha o campo que deseja editar: ");
				    	
				    	do {
							try {
								editInfo = sc.nextInt();

								if(editInfo < 0 || editInfo > 7) {
									throw new InputMismatchException("Entrada inválida");
								}

							} catch (InputMismatchException e) {
								System.out.print("Entrada inválida, digite novamente: ");
							}
							sc.nextLine();
				    	}while(editInfo<0 || editInfo>7);
				    	
				    	switch(editInfo) {
				    		case 1:
				    			System.out.println("Código: " + productCopy.getProductCode());
				    			System.out.print("Novo código: ");
				    			String newProductCode = sc.nextLine();
				    			productCopy.setProductCode(newProductCode);
				    			break;
				    		case 2:
				    			System.out.println("Nome: " + productCopy.getName());
				    			System.out.print("Novo nome: ");
				    			String newName = sc.nextLine();
				    			productCopy.setName(newName);
				    			break;
				    		case 3:
				    			System.out.println("Descrição: " + productCopy.getDescription());
				    			System.out.print("Nova descrição: ");
				    			String newDescription = sc.nextLine();
				    			productCopy.setDescription(newDescription);
				    			break;
				    		case 4: 
				    			System.out.println("ID: "+ productCopy.getSupplier().getId() +" | FORNECEDOR ATUAL: " + product.getSupplier().getName());
				    			try {
									supplierController.showArray(Role.SUPPLIER);
								} catch (Exception e) {
									System.out.println(e.getMessage());
			                    }
						    	System.out.print("ID do novo fornecedor: ");
						    	int idNewSupplier;
						    	Supplier newSupplier;
						    	
						    	try {
						    		idNewSupplier = sc.nextInt();
						    		sc.nextLine();
								} catch (InputMismatchException e) {
									System.out.println("Tipo digitado não corresponde a um ID.");
									sc.nextLine();
									return;
								} catch (Exception e) {
									System.out.println(e.getMessage());
									sc.nextLine();
									return;
								}
						    									
								try {
									newSupplier = supplierController.getById(idNewSupplier);
								} catch (Exception e) {
									System.out.println(e.getMessage());
									return;
								}
					    		productCopy.setSupplier(newSupplier);			
				    			break;
				    		case 5: 
				    			System.out.println("Quantidade em estoque: " + productCopy.getStock().getQuantity());
				    			System.out.println("Nova quantidade em estoque: ");
				    			try {
				    				newQuantity = sc.nextInt();
				    			}catch (InputMismatchException e) {
									System.out.println("Entrada inválida. Quantidade não alterada. ");
								}
				    			sc.nextLine();
				    			newStock = new Stock(newQuantity, newPrice);
				    			productCopy.setStock(newStock);
				    			break;
				    		case 6:
				    			System.out.println("Preço: " + productCopy.getStock().getPrice());
				    			System.out.println("Novo preço: ");
				    			try {
				    				newPrice = sc.nextDouble();
				    			}catch(InputMismatchException e) {
				    				System.out.println("Entrada inválida. Preço não alterado");
				    			}
				    			sc.nextLine();
				    			newStock = new Stock(newQuantity, newPrice);
				    			productCopy.setStock(newStock);
								break;
				    		case 7:
								try {
									productController.edit(product, productCopy);
								} catch (Exception e) {
									System.out.println(e.getMessage());
									return;
								}
								break;
				    		case 0:
				    			System.out.println("Alterações não realizadas...");
					    }
					}while(editInfo != 0 && editInfo != 7);
					break;
				case 4:
					System.out.print("Buscar Produto: ");;
					String text3 = sc.nextLine();

					List<Product> products2;

					try {
						products2 = productController.getByText(text3);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<products2.size();i++) {
						System.out.println(i+1+" - Código: "+ products2.get(i).getProductCode() + " - Nome: "+products2.get(i).getName());
					}
					System.out.println("0 - Sair");
					System.out.println("--------");
					System.out.println("Digite o número do produto que deseja excluir: ");

					int id2 = -1;

					Product product1;

					do {
						try {
							id2 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.print("Entrada inválida, digite novamente: ");
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return;
						}
					} while (id2<0 || id2 > products2.size());

					if(id2 == 0) {
						return;
					}

					product1 = products2.get(id2-1);

					try {
						productController.delete(product1);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					System.out.println("Produto excluído com sucesso!");
					break;

				case 5:
					System.out.print("Buscar Fornecedor: ");;
					String text4 = sc.nextLine();

					List<Supplier> suppliers1;

					try {
						suppliers1 = supplierController.getByText(text4);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					for(int i = 0; i<suppliers1.size();i++) {
						System.out.println(i+1+" - Nome: "+suppliers1.get(i).getName());
					}
					System.out.println("0 - Sair");
					System.out.println("--------");
					System.out.print("Digite o número do fornecedor que deseja visualizar os produtos: ");

					int id3 = -1;
					Supplier supplier1;

					do {
						try {
							id3 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.print("Entrada inválida, digite novamente: ");
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return;
						}
					} while (id3<0 || id3 > suppliers1.size());

					if(id3 == 0) {
						return;
					}

					supplier1 = suppliers1.get(id3-1);

					for(Product productAux : supplier1.getProducts().getAllProducts()) {
						System.out.println("ID: "+productAux.getId() + " | Nome: "+productAux.getName());
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
   
    public void subOrders(Scanner sc, Store store) {
		System.out.println("------------------");
		System.out.println("EM PRODUÇÃO");
	}
}
