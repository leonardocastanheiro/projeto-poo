package br.ucs.projetosistemaprodutos.views;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import br.ucs.projetosistemaprodutos.controllers.*;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.copies.ClientCopy;
import br.ucs.projetosistemaprodutos.models.copies.ProductCopy;
import br.ucs.projetosistemaprodutos.models.copies.SupplierCopy;
import br.ucs.projetosistemaprodutos.models.itens.*;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;

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

					if (subOption < 0 || subOption > 4) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			} while (subOption < 0 || subOption > 4);

			switch (subOption) {
				case 1:
					Client c = new Client();
					String name = "";
					String login = "";
					String password = "";
					String creditCard = "";
					
					do {
						System.out.print("Nome: ");
						name = sc.nextLine();
						if(name.isEmpty()) {
							System.out.println("Esse campo é obrigatório");
						}else {
							c.setName(name);
						}
					}while(name.isEmpty());
					
					System.out.print("Telefone: ");
					String phone = sc.nextLine();
					System.out.print("Email: ");
					String email = sc.nextLine();
					
					do {
						System.out.print("Usuário: ");
						login = sc.nextLine();
						if(login.isEmpty()) {
							System.out.println("Esse campo é obrigatório");
						}else {
							c.setLogin(login);						
						}
					}while(login.isEmpty());
					
					do {
						System.out.print("Senha: ");
						password = sc.nextLine();
						if(password.isEmpty()) {
							System.out.println("Esse campo é obrigatório");
						}else {
							c.setPassword(password);
						}
					}while(password.isEmpty());
					
					do {
						System.out.print("Cartão de crédito: ");
						creditCard = sc.nextLine();
						if(creditCard.isEmpty()) {
							System.out.println("Esse campo é obrigatório");
						}else {
							c.setCreditCard(creditCard);							
						}
					}while(creditCard.isEmpty());
					
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

					Address address = new Address(street, number, complement, neighborhood, cep, city, state);
					Client client = new Client(login, password, Role.CLIENT, name, phone, email, creditCard, address);
					try {
						clientController.create(client);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;

				case 2:
					System.out.println("Buscar cliente (Digite '0' para voltar ao menu): ");
					String text = sc.nextLine();

					try {
						int exitSub = Integer.parseInt(text);

						if (exitSub == 0) {
							return;
						}
					} catch (NumberFormatException ignored) {
					}
					
					List<Client> clients;

					try {
						clients = clientController.getByText(text);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
					Collections.sort(clients);
					for (int i = 0; i < clients.size(); i++) {
						System.out.println(i + 1 + " - Nome: " + clients.get(i).getName());
					}
					break;
				case 3:
					System.out.println("Buscar cliente (Digite '0' para voltar ao menu): : ");
					String text1 = sc.nextLine();

					try {
						int exitSub = Integer.parseInt(text1);

						if (exitSub == 0) {
							return;
						}
					} catch (NumberFormatException ignored) {
					}
					
					List<Client> clients1;

					try {
						clients1 = clientController.getByText(text1);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
					
					Collections.sort(clients1);
					for (int i = 0; i < clients1.size(); i++) {
						System.out.println(i + 1 + " - Nome: " + clients1.get(i).getName());
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
					} while (id < 0 || id > clients1.size());

					if (id == 0) {
						return;
					}

					client1 = clients1.get(id - 1);

					ClientCopy clientCopy = new ClientCopy(client1);
					int editInfo = -1;
					do {
						System.out.println("1 - Nome: " + clientCopy.getName());
						System.out.println("2 - Telefone: " + clientCopy.getPhone());
						System.out.println("3 - Email: " + clientCopy.getEmail());
						System.out.println("4 - Usuário: " + clientCopy.getLogin());
						System.out.println("5 - Senha");
						System.out.println("6 - Cartão de crédito: " + clientCopy.getCreditCard());
						System.out.println("7 - Rua: " + clientCopy.getAddress().getStreet());
						System.out.println("8 - Número: " + clientCopy.getAddress().getNumber());
						System.out.println("9 - Complemento: " + clientCopy.getAddress().getComplement());
						System.out.println("10 - Bairro: " + clientCopy.getAddress().getNeighborhood());
						System.out.println("11 - CEP: " + clientCopy.getAddress().getCep());
						System.out.println("12 - Cidade: " + clientCopy.getAddress().getCity());
						System.out.println("13 - Estado: " + clientCopy.getAddress().getState());
						System.out.println("14 - Salvar alterações");
						System.out.println("0 - Cancelar");
						System.out.println("Informe o campo que deseja editar: ");

						do {
							try {
								editInfo = sc.nextInt();

								if (editInfo < 0 || editInfo > 14) {
									throw new InputMismatchException("Entrada inválida");
								}

							} catch (InputMismatchException e) {
								System.out.print("Entrada inválida, digite novamente: ");
							}
							sc.nextLine();
						} while (editInfo < 0 || editInfo > 14);

						switch (editInfo) {
							case 1:
								String newName;
								do {
									System.out.println("Nome: " + clientCopy.getName());
									System.out.print("Novo nome: ");									
									newName = sc.nextLine();
									if(newName.isEmpty()) {
										System.out.println("Esse campo é obrigatório");
									}else {
										clientCopy.setName(newName);
									}
								}while(newName.isEmpty());
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
								String newUsername;
								do {
									System.out.println("Usuário: " + clientCopy.getLogin());
									System.out.print("Novo nome de usuário: ");									
									newUsername = sc.nextLine();
									if(newUsername.isEmpty()) {
										System.out.println("Esse campo é obrigatório");
									}else {
										clientCopy.setLogin(newUsername);
									}
								}while(newUsername.isEmpty());
								break;
							case 5:
								String newPassword;
								do {
									System.out.print("Nova senha: ");									
									newPassword = sc.nextLine();
									if(newPassword.isEmpty()) {
										System.out.println("Esse campo é obrigatório");
									}else {
										clientCopy.setPassword(newPassword);
									}
								}while(newPassword.isEmpty());								
								break;
							case 6:
								String newCreditCard;
								do {
									System.out.println("Cartão de crédito: " + clientCopy.getCreditCard());
									System.out.print("Novo cartão de crédito: ");									
									newCreditCard = sc.nextLine();
									if(newCreditCard.isEmpty()) {
										System.out.println("Esse campo é obrigatório");
									}else {
										clientCopy.setCreditCard(newCreditCard);
									}
								}while(newCreditCard.isEmpty());	
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
									clientController.edit(client1, clientCopy);
								} catch (Exception e) {
									System.out.println(e.getMessage());
									return;
								}
								break;
							case 0:
								System.out.println("Alterações não realizadas...");
						}
					} while (editInfo != 0 && editInfo != 14);
					break;
				case 4:
					System.out.println("Buscar cliente (Digite '0' para voltar ao menu): ");
					String text2 = sc.nextLine();

					try {
						int exitSub = Integer.parseInt(text2);

						if (exitSub == 0) {
							return;
						}
					} catch (NumberFormatException ignored) {
					}
					
					List<Client> clients2;

					try {
						clients2 = clientController.getByText(text2);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
					Collections.sort(clients2);
					for (int i = 0; i < clients2.size(); i++) {
						System.out.println(i + 1 + " - Nome: " + clients2.get(i).getName());
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
					} while (id1 < 0 || id1 > clients2.size());

					if (id1 == 0) {
						return;
					}

					client2 = clients2.get(id1 - 1);

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
		} while (subOption != 0);
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

					if (subOption < 0 || subOption > 5) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
				}
				sc.nextLine();
			} while (subOption < 0 || subOption > 5);

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

					Address address = new Address(street, number, complement, neighborhood, cep, city, state);
					Supplier supplier = new Supplier(Role.SUPPLIER, name, phone, email, description, address);

					try {
						supplierController.create(supplier);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.print("Buscar fornecedor (Digite '0' para voltar ao menu): ");
					String text = sc.nextLine();

					try {
						int exitSub = Integer.parseInt(text);

						if (exitSub == 0) {
							return;
						}
					} catch (NumberFormatException ignored) {
					}
					
					List<Supplier> suppliers;

					try {
						suppliers = supplierController.getByText(text);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
					Collections.sort(suppliers);
					int selectedIndex = -1;
					do {
						System.out.println("\nFornecedores:");
						for (int i = 0; i < suppliers.size(); i++) {
							System.out.println(i + 1 + " - Nome: " + suppliers.get(i).getName());
						}
						System.out.println("0 - Voltar");

						selectedIndex = -1;

						do {
							System.out.print("Digite o número do fornecedor para ver mais detalhes (ou 0 para sair): ");
							try {
								selectedIndex = sc.nextInt();
								sc.nextLine();

								if (selectedIndex < 0 || selectedIndex > suppliers.size()) {
									throw new IllegalArgumentException("Opção inválida. Tente novamente.");
								}

							} catch (InputMismatchException e) {
								System.out.println("Erro: Entrada deve ser um número inteiro.");
								sc.nextLine();
								selectedIndex = -1;
							} catch (IllegalArgumentException e) {
								System.out.println("Erro: " + e.getMessage());
								selectedIndex = -1;
							}
						} while (selectedIndex == -1);

						if (selectedIndex != 0) {
							Supplier selectedSupplier = suppliers.get(selectedIndex - 1);
							System.out.println("----- DETALHES DO FORNECEDOR -----");
							System.out.println("Nome: " + selectedSupplier.getName());
							System.out.println("Telefone: " + selectedSupplier.getPhone());
							System.out.println("Email: " + selectedSupplier.getEmail());
							System.out.println("Descrição: " + selectedSupplier.getDescription());
							System.out.println("----- PRODUTOS -----");
							for(int j = 0; j < selectedSupplier.getProducts().size(); j++) {
								System.out.println(selectedSupplier.getProducts().getAllProducts().get(j).toString());
							}
							System.out.println("----- Endereço -----");
							System.out.println(selectedSupplier.getAddress().toString());
						}

					} while (selectedIndex != 0);
					break;
				case 3:
					System.out.print("Buscar fornecedor (Digite '0' para voltar ao menu): ");
					String text1 = sc.nextLine();
					
					try {
						int exitSub = Integer.parseInt(text1);

						if (exitSub == 0) {
							return;
						}
					} catch (NumberFormatException ignored) {
					}

					List<Supplier> suppliers1;

					try {
						suppliers1 = supplierController.getByText(text1);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
					Collections.sort(suppliers1);
					for (int i = 0; i < suppliers1.size(); i++) {
						System.out.println(i + 1 + " - Nome: " + suppliers1.get(i).getName());
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
					} while (id < 0 || id > suppliers1.size());

					if (id == 0) {
						return;
					}

					supplier1 = suppliers1.get(id - 1);

					SupplierCopy supplierCopy = new SupplierCopy(supplier1);
					int editInfo = -1;
					do {
						System.out.println("1 - Nome: " + supplierCopy.getName());
						System.out.println("2 - Telefone: " + supplierCopy.getPhone());
						System.out.println("3 - Email: " + supplierCopy.getEmail());
						System.out.println("4 - Descrição: " + supplierCopy.getDescription());
						System.out.println("5 - Rua: " + supplierCopy.getAddress().getStreet());
						System.out.println("6 - Número: " + supplierCopy.getAddress().getNumber());
						System.out.println("7 - Complemento: " + supplierCopy.getAddress().getComplement());
						System.out.println("8 - Bairro: " + supplierCopy.getAddress().getNeighborhood());
						System.out.println("9 - CEP: " + supplierCopy.getAddress().getCep());
						System.out.println("10 - Cidade: " + supplierCopy.getAddress().getCity());
						System.out.println("11 - Estado: " + supplierCopy.getAddress().getState());
						System.out.println("12 - Salvar alterações");
						System.out.println("0 - Cancelar");
						System.out.println("Informe o campo que deseja editar: ");

						do {
							try {
								editInfo = sc.nextInt();

								if (editInfo < 0 || editInfo > 12) {
									throw new InputMismatchException("Entrada inválida");
								}

							} catch (InputMismatchException e) {
								System.out.print("Entrada inválida, digite novamente: ");
							}
							sc.nextLine();
						} while (editInfo < 0 || editInfo > 12);

						switch (editInfo) {
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
									supplierController.edit(supplier1, supplierCopy);
								} catch (Exception e) {
									System.out.println(e.getMessage());
									return;
								}
								break;
							case 0:
								System.out.println("Alterações não realizadas...");
						}
					} while (editInfo != 0 && editInfo != 12);
					break;
				case 4:
					System.out.print("Buscar fornecedor (Digite '0' para voltar ao menu): ");
					String text2 = sc.nextLine();
					
					try {
						int exitSub = Integer.parseInt(text2);

						if (exitSub == 0) {
							return;
						}
					} catch (NumberFormatException ignored) {
					}

					List<Supplier> suppliers2;

					try {
						suppliers2 = supplierController.getByText(text2);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
					Collections.sort(suppliers2);
					for (int i = 0; i < suppliers2.size(); i++) {
						System.out.println(i + 1 + " - Nome: " + suppliers2.get(i).getName());
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
					} while (id1 < 0 || id1 > suppliers2.size());

					if (id1 == 0) {
						return;
					}

					supplier2 = suppliers2.get(id1 - 1);

					try {
						supplierController.delete(supplier2);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}

					System.out.println("Fornecedor excluído com sucesso!");
					break;

				case 5:
					System.out.print("Buscar fornecedor (Digite '0' para voltar ao menu): ");
					String text3 = sc.nextLine();

					try {
						int exitSub = Integer.parseInt(text3);

						if (exitSub == 0) {
							return;
						}
					} catch (NumberFormatException ignored) {
					}

					
					List<Supplier> suppliers3;

					try {
						suppliers3 = supplierController.getByText(text3);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return;
					}
					Collections.sort(suppliers3);
					for (int i = 0; i < suppliers3.size(); i++) {
						System.out.println(i + 1 + " - Nome: " + suppliers3.get(i).getName());
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
					} while (id2 < 0 || id2 > suppliers3.size());

					if (id2 == 0) {
						return;
					}

					supplier3 = suppliers3.get(id2 - 1);

					for (Product product : supplier3.getProducts().getAllProducts()) {
						System.out.println("ID: " + product.getId() + " | Nome: " + product.getName());
					}
					break;
				case 0:
					System.out.println("Saindo de 'Fornecedores'...");
					break;
				default:
					System.out.println("Erro...");
			}
		} while (subOption != 0);

	}
/*---------------------------------------------------------------------------------------------*/
	public void subProducts(Scanner sc, Store store) {
		int subOption = -1;

		do{
			System.out.println("\n\n-----MENU DE GERENCIAMENTO DE PRODUTOS-----");
			System.out.println("1 - Cadastrar Novo Produto");
			System.out.println("2 - Buscar Produto(s)");
			System.out.println("3 - Editar Produto");
			System.out.println("4 - Remover Produto");
			System.out.println("0 - Sair");
			System.out.println("Por favor, escolha uma opção: ");

				do {
					try {
						subOption = sc.nextInt();

						if (subOption < 0 || subOption > 4) {
							throw new InputMismatchException("Entrada inválida");
						}

					} catch (InputMismatchException e) {
						System.out.print("Entrada inválida, digite novamente: ");
					}
					sc.nextLine();
				} while (subOption < 0 || subOption > 4);

			switch (subOption) {
				case 1:
					subAddProduct(sc);
					break;

				case 2:
					int option = -1;

					System.out.println("BUSCAR...");
					System.out.println("1 - Nome/Código");
					System.out.println("2 - Visualizar Produto de um Fornecedor");
					System.out.println("0 - Retornar ao Menu de Gerenciamento de Produtos");
					System.out.println("Por favor, digite uma opção: ");

					do{
						try{
							option = sc.nextInt();

							if(option < 0 || option > 2){
								throw new InputMismatchException("Entrada Invalida!");
							}
						} catch (InputMismatchException e) {
							System.out.println("Por favor, digite novamente:");
						}
						sc.nextLine();
					} while(option < 0 || option > 2);

				switch (option){
					case 1:
						subSearchProductByCodeAndText(sc);
					break;

					case 2:
						showProductsBySupplier(sc);
						break;

					case 0:
						System.out.println("Saindo...");
						break;
				}
				break;

				case 3:
					subEditProduct(sc);
					break;

				case 4:
					subDeleteProduct(sc);
					break;

				case 0:
					System.out.println("Saindo de 'Produtos'...");
					break;
				default:
					System.out.println("Erro...");
			}
		} while (subOption != 0);
	}

	//-------------------------------------------------------------------------------------
	private void subAddProduct(Scanner sc){

		System.out.println("Cadastrando Produto: ");
		System.out.print("Nome: ");
		String name = sc.nextLine();


		System.out.print("Descrição: ");
		String description = sc.nextLine();
		System.out.print("Código: ");
		String productCode = sc.nextLine();
		System.out.print("\nBuscar Fornecedor: ");
		;
		String text = sc.nextLine();

		List<Supplier> suppliers;

		try {
			suppliers = supplierController.getByText(text);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		Collections.sort(suppliers);
		for (int i = 0; i < suppliers.size(); i++) {
			System.out.println(i + 1 + " - Nome: " + suppliers.get(i).getName());
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
		} while (id < 0 || id > suppliers.size());

		if (id == 0) {
			return;
		}

		supplier = suppliers.get(id - 1);

		System.out.print("Quantidade em estoque: ");
		int quantity = -1;
		do {
			try {
				quantity = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.print("Entrada inválida, digite novamente: ");

			}
			sc.nextLine();
		} while (quantity == -1);

		System.out.print("Preço: ");
		double price = -1;
		do {
			try {
				price = sc.nextDouble();
			} catch (InputMismatchException e) {
				System.out.print("Entrada inválida, digite novamente: ");
			}
			sc.nextLine();
		} while (price == -1);

		Stock stock = new Stock(quantity, price);

		try {
			Product product = new Product(name, description, stock, supplier, productCode);
			productController.create(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
//-------------------------------------------------------------------------------------
	private void subSearchProductByCodeAndText(Scanner sc) {
		System.out.print("Buscar produto (Digite '0' para voltar ao menu): ");
		String searchText = sc.nextLine();

		try {
			int exitSub = Integer.parseInt(searchText);

			if (exitSub == 0) {
				return;
			}
		} catch (NumberFormatException ignored) {
		}

		List<Product> products;

		try {
			products = productController.getByText(searchText);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		if (products.isEmpty()) {
			System.out.println("Nenhum produto encontrado.");
			return;
		}
		Collections.sort(products);
		int selectedIndex;

		do {
			System.out.println("\nLista de produtos:");
			for (int i = 0; i < products.size(); i++) {
				Product product = products.get(i);
				System.out.println((i + 1) + " - Código: " + product.getProductCode() + " - Nome: " + product.getName());
			}
			System.out.println("0 - Voltar");

			selectedIndex = -1;

			do {
				System.out.print("Digite o número do produto para ver mais detalhes (ou 0 para sair): ");
				try {
					selectedIndex = sc.nextInt();
					sc.nextLine();

					if (selectedIndex < 0 || selectedIndex > products.size()) {
						throw new IllegalArgumentException("Opção inválida. Tente novamente.");
					}

				} catch (InputMismatchException e) {
					System.out.println("Erro: Entrada deve ser um número inteiro.");
					sc.nextLine();
					selectedIndex = -1;
				} catch (IllegalArgumentException e) {
					System.out.println("Erro: " + e.getMessage());
					selectedIndex = -1;
				}
			} while (selectedIndex == -1);

			if (selectedIndex != 0) {
				Product selectedProduct = products.get(selectedIndex - 1);
				showProductDetails(selectedProduct);
			}

		} while (selectedIndex != 0);

		System.out.println("Voltando ao menu anterior...");
	}
//-------------------------------------------------------------------------------------
	private void showProductsBySupplier(Scanner scanner) {

		System.out.println("\n--- Produtos por Fornecedor ---");
		System.out.print("Digite o nome do fornecedor (Digite '0' para voltar ao menu): ");
		String name = scanner.nextLine();
		
		try {
			int exitSub = Integer.parseInt(name);

			if (exitSub == 0) {
				return;
			}
		} catch (NumberFormatException ignored) {
		}
		
		List<Supplier> suppliers;

		try {
			suppliers = supplierController.getByText(name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		if (suppliers.isEmpty()) {
			System.out.println("Nenhum fornecedor encontrado com esse nome.");
			return;
		}

		Collections.sort(suppliers);
		System.out.println("\nFornecedores encontrados:");
		for (int i = 0; i < suppliers.size(); i++) {
			System.out.println((i + 1) + ". " + suppliers.get(i).getName());
		}

		System.out.print("\nDigite o número do fornecedor: ");
		int escolha;

		try {
			escolha = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Entrada inválida.");
			scanner.nextLine();
			return;
		}

		if (escolha < 1 || escolha > suppliers.size()) {
			System.out.println("Número inválido.");
			return;
		}

		Supplier fornecedor = suppliers.get(escolha - 1);
		List<Product> produtos = fornecedor.getProducts().getAllProducts();

		if (produtos.isEmpty()) {
			System.out.println("Esse fornecedor não possui produtos cadastrados.");
			return;
		}
		Collections.sort(produtos);
		int opcao;

		do {
			System.out.println("\nProdutos de " + fornecedor.getName() + ":");
			for (int i = 0; i < produtos.size(); i++) {
				Product produto = produtos.get(i);
				System.out.println((i + 1) + " - " + produto.getName() + " (ID: " + produto.getId() + ")");
			}
			System.out.println("0 - Voltar");

			System.out.print("\nDigite o número do produto para ver os detalhes ou 0 para sair: ");

			try {
				opcao = scanner.nextInt();
				scanner.nextLine();

				if (opcao < 0 || opcao > produtos.size()) {
					System.out.println("Opção inválida. Tente novamente.");
					opcao = -1;
				} else if (opcao != 0) {
					Product produto = produtos.get(opcao - 1);
					showProductDetails(produto);
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, digite um número.");
				scanner.nextLine();
				opcao = -1;
			}
		} while (opcao != 0);

		System.out.println("Voltando ao menu...");
	}
//-------------------------------------------------------------------------------------
	private void showProductDetails(Product product) {
		System.out.println("\n-------- DETALHES DO PRODUTO --------");
		System.out.println("Código: " + product.getProductCode());
		System.out.println("Nome: " + product.getName());
		System.out.println("Descrição: " + product.getDescription());
		System.out.println("Fornecedor: " + product.getSupplier().getName());
		System.out.println("Quantidade em estoque: " + product.getStock().getQuantity());
		System.out.println("Preço: R$ " + String.format("%.2f", product.getStock().getPrice()));
		System.out.println("--------------------------------------");
	}
//-------------------------------------------------------------------------------------
	private void subEditProduct(Scanner sc){
		System.out.print("Buscar produto (Digite '0' para voltar ao menu): ");
		String text2 = sc.nextLine();
		
		try {
			int exitSub = Integer.parseInt(text2);

			if (exitSub == 0) {
				return;
			}
		} catch (NumberFormatException ignored) {
		}
		
		List<Product> products1;

		try {
			products1 = productController.getByText(text2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		Collections.sort(products1);
		for (int i = 0; i < products1.size(); i++) {
			System.out.println((i + 1) + " - Código: " + products1.get(i).getProductCode() +
					" - Nome: " + products1.get(i).getName());
		}

		System.out.println("0 - Sair");
		System.out.println("--------");
		System.out.print("Digite o número do produto que deseja editar: ");

		int id1 = -1;
		Product product;

		do {
			try {
				id1 = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.print("Entrada inválida, digite novamente: ");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return;
			}
		} while (id1 < 0 || id1 > products1.size());

		if (id1 == 0) return;

		product = products1.get(id1 - 1);
		ProductCopy productCopy = new ProductCopy(product);
		int newQuantity = product.getStock().getQuantity();
		double newPrice = product.getStock().getPrice();
		Stock newStock;
		int editInfo = -1;

		do {
			System.out.println("EDITAR PRODUTO:");
			System.out.println("1. Código: " + productCopy.getProductCode());
			System.out.println("2. Nome: " + productCopy.getName());
			System.out.println("3. Descrição: " + productCopy.getDescription());
			System.out.println("4. Fornecedor: " + productCopy.getSupplier().getName());
			System.out.println("5. Quantidade em estoque: " + productCopy.getStock().getQuantity());
			System.out.println("6. Preço: " + String.format("%.2f", productCopy.getStock().getPrice()));
			System.out.println("7. Salvar");
			System.out.println("0. Cancelar");
			System.out.print("Escolha o campo que deseja editar: ");

			do {
				try {
					editInfo = sc.nextInt();
					sc.nextLine(); // consumir quebra de linha

					if (editInfo < 0 || editInfo > 7) {
						throw new InputMismatchException("Entrada inválida");
					}

				} catch (InputMismatchException e) {
					System.out.print("Entrada inválida, digite novamente: ");
					sc.nextLine();
				}
			} while (editInfo < 0 || editInfo > 7);

			switch (editInfo) {
				case 1:
					System.out.println("Código: " + productCopy.getProductCode());
					System.out.print("Novo código: ");
					productCopy.setProductCode(sc.nextLine());
					break;

				case 2:
					System.out.println("Nome: " + productCopy.getName());
					System.out.print("Novo nome: ");
					productCopy.setName(sc.nextLine());
					break;

				case 3:
					System.out.println("Descrição: " + productCopy.getDescription());
					System.out.print("Nova descrição: ");
					productCopy.setDescription(sc.nextLine());
					break;

				case 4:
					System.out.println("ID: " + productCopy.getSupplier().getId() +
							" | FORNECEDOR ATUAL: " + product.getSupplier().getName());
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
					System.out.print("Nova quantidade em estoque: ");
					try {
						newQuantity = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Entrada inválida. Quantidade não alterada.");
					}
					sc.nextLine();
					newStock = new Stock(newQuantity, newPrice);
					productCopy.setStock(newStock);
					break;

				case 6:
					System.out.println("Preço: " + String.format("%.2f", productCopy.getStock().getPrice()));
					System.out.print("Novo preço: ");
					try {
						newPrice = sc.nextDouble();
					} catch (InputMismatchException e) {
						System.out.println("Entrada inválida. Preço não alterado.");
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
					break;
			}

		} while (editInfo != 0 && editInfo != 7);
	}
//-------------------------------------------------------------------------------------
	private void subDeleteProduct(Scanner sc){
		System.out.print("Buscar produto (Digite '0' para voltar ao menu): ");
		String searchText = sc.nextLine();

		try {
			int exitSub = Integer.parseInt(searchText);

			if (exitSub == 0) {
				return;
			}
		} catch (NumberFormatException ignored) {
		}
		
		List<Product> foundProducts;

		try {
			foundProducts = productController.getByText(searchText);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		Collections.sort(foundProducts);
		for (int i = 0; i < foundProducts.size(); i++) {
			Product product = foundProducts.get(i);
			System.out.println((i + 1) + " - Código: " + product.getProductCode() + " - Nome: " + product.getName());
		}

		System.out.println("0 - Sair");
		System.out.println("--------");
		System.out.print("Digite o número do produto que deseja excluir: ");

		int selectedIndex = -1;
		Product selectedProduct;

		do {
			try {
				selectedIndex = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.print("Entrada inválida, digite novamente: ");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return;
			}
		} while (selectedIndex < 0 || selectedIndex > foundProducts.size());

		if (selectedIndex == 0) {
			return;
		}

		selectedProduct = foundProducts.get(selectedIndex - 1);

		try {
			productController.delete(selectedProduct);
			System.out.println("Produto excluído com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/*-------------------------------------------------------------------------------------*/
	public void subOrders(Scanner sc, Store store) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		List<Order> orders = new ArrayList<>();
		int option = -1;

		do {
			System.out.println("\n=== MENU DE PESQUISA DE PEDIDOS ===");
			System.out.println("1 - Pesquisar por número do pedido ou cliente");
			System.out.println("2 - Pesquisar por data de realização");
			System.out.println("3 - Pesquisar por intervalo de data");
			System.out.println("0 - Voltar");
			System.out.print("Escolha uma opção: ");

			try {
				option = sc.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Entrada inválida.");
			}catch (Exception e) {
				System.out.println("Erro inesperado: " + e.getMessage());
			}

			sc.nextLine();
			System.out.println("--------------------------------------");
		}while(option < 0 ||option > 3);


		switch (option) {
			case 1:
				searchByIdAndText(sc);
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
					break;
				}
				orders = productController.getOrdersByDate(date, clientController.getAllOrders());
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
					} catch (NumberFormatException ignored) {
					}
					startDate = LocalDate.parse(text1, dtf);

					System.out.print("Data final (Digite '0' para voltar ao menu): ");
					String text2 = sc.nextLine();
					
					try {
						int exitSub = Integer.parseInt(text2);

						if (exitSub == 0) {
							return;
						}
					} catch (NumberFormatException ignored) {
					}
					endDate = LocalDate.parse(text2, dtf);
				}catch(DateTimeParseException e) {
					System.out.println("Data inválida.");
					break;
				}

				if(startDate.isAfter(endDate)) {
					System.out.print("Data de início maior e a data final.");
					break;
				}
				orders = productController.getOrdersByDate(startDate, endDate, clientController.getAllOrders());
				break;
			case 0:
				System.out.println("Voltando ao menu principal...");
				break;

			default:
				System.out.println("Opção inválida! Por favor, escolha entre 0 a 3");
		}

		if(orders.isEmpty()) {
			System.out.println("Nenhum pedido encontrado");
		}else {
			Collections.sort(orders);
			for(Order order : orders) {
				showCompleteDetailsOrder(order);
			}
		}
	}

	private void searchByIdAndText(Scanner sc) {

		System.out.print("Digite o nome do cliente ou o número do pedido ('0' para voltar ao menu): ");

		int id = -1;
		String text;

		text = sc.nextLine();

		try {
			id = Integer.parseInt(text);
			
			if(id == 0) {
				return;
			}
		} catch (NumberFormatException ignored) {
		}

		List<Order> orders = new ArrayList<>();

		if (id == -1) {
			try {
				orders = new ArrayList<>(clientController.getAllOrderByText(text));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return;
			}
		} else {
			Optional<Order> orderOptional = clientController.getOrderById(id);
			if (orderOptional.isEmpty()) {
				System.out.println("Nenhum pedido encontrado com esse ID");
				return;
			}
			orders.add(orderOptional.get());
		}

		Order order;

		if (orders.size() == 1) {
			order = orders.getFirst();
		} else {
			Collections.sort(orders);
			for (Order orderAux : orders) {
				System.out.println("ID: " + orderAux.getId() + " | Cliente: " + orderAux.getOwner().getName());
			}

			id = -1;

			System.out.print("\nDigite o ID do pedido que você deseja acessar: ");
			do {
				try {
					id = sc.nextInt();
				} catch (InputMismatchException ignored) {
				}
				sc.nextLine();
			} while (id < 1);
			try {
				order = productController.getOrderByList(id, orders);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return;
			}
		}

		showCompleteDetailsOrder(order);
		editDetailsOrder(order, sc);
	}

	private void showCompleteDetailsOrder(Order order) {
		System.out.println("----------  Pedido  ----------");
		System.out.println("ID do Pedido: " + order.getId());
		System.out.println("Situação: " + order.getSituation().toString());
		System.out.println("Data do pedido: " + order.getDateOrder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		System.out.println("Data de envio: " + (order.getDateForward() == null ? "*AINDA NÃO ENVIADO*" : order.getDateForward().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		System.out.println("Data de entrega: " + (order.getDateDeliver() == null ? "*AINDA NÃO ENTREGUE*" : order.getDateDeliver().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		System.out.println("Valor total do pedido (sem ICMS): R$" + String.format("%.2f", order.getTotalPrice()));
		System.out.println("Valor total do pedido (com ICMS): R$" + String.format("%.2f", order.getTotalPriceICMS()));

		System.out.println("---------- Detalhes ----------");
		for (ItemOrder itemOrder : order.getItemOrders()) {
			Product product = itemOrder.getProduct();
			Integer quantity = itemOrder.getQuantity();
			System.out.println("---------- Produto ----------");
			System.out.println("Nome: " + product.getName() + " | Descrição: " + product.getDescription());
			System.out.println("Quantidade: " + quantity + " | Valor unitário: R$ " + String.format("%.2f", product.getStock().getPrice())  + " | Valor Total: R$ " + String.format("%.2f", (itemOrder.getPrice() * quantity)));
		}
		System.out.println("----------          ----------\n");
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
				System.out.println("\n1 - Cancelar\n2 - Enviar\n0 - Sair");
				option = -1;

				do {
					try {
						option = sc.nextInt();

						if (option < 0 || option > 2) {
							throw new InputMismatchException("Entrada inválida");
						}

					} catch (InputMismatchException e) {
						System.out.print("Entrada inválida, digite novamente: ");
					}
					sc.nextLine();
				} while (option < 0 || option > 2);

				if (option == 0) {
					break;
				}

				switch (option) {
					case 1:
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
						break;

					case 2:
						order.setSituation(Situation.FORWARD);
						order.setDateForward(LocalDate.now());
						System.out.println("Pedido Enviado com sucesso!");
				}
			}

			case FORWARD -> {
				System.out.println("\nAguardando a confirmação de entrega...");
				break;
			}

			case CANCELED -> {
				System.out.println("\nPedido já cancelado...");
				break;
			}

			case DELIVERED -> {
				System.out.println("\nPedido já entregue...");
				break;
			}
		}
	}

}
