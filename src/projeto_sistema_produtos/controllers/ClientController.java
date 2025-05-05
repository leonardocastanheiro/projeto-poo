package projeto_sistema_produtos.controllers;

import projeto_sistema_produtos.models.person.Client;
import projeto_sistema_produtos.models.person.User;
import projeto_sistema_produtos.repositories.client.ClientRepository;

import java.util.Scanner;

public class ClientController {
    UserController userController = new UserController();
    ClientRepository clientRepository = new ClientRepository();

    public ClientController() throws Exception {
    }

    public void createClient() throws Exception {
        User user = userController.createUser();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Credit Card: ");
        String creditCard = scanner.nextLine();

        Client client = new Client(user,creditCard);

        clientRepository.create(client);
    }

    public void getByUser(User user) throws Exception {
        clientRepository.getByUser(user);
    }

    public void save() throws Exception {
        clientRepository.save();
    }
}
