package projeto_sistema_produtos.repositories.client;

import projeto_sistema_produtos.controllers.UserController;
import projeto_sistema_produtos.models.person.Client;
import projeto_sistema_produtos.models.person.User;
import projeto_sistema_produtos.repositories.user.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    private static final String FILE_PATH = "clients.db";
    private DynamicClientArray clients;

    UserRepository userRepository;

    public ClientRepository() throws Exception {
        List<Client> existing = loadAllClients();
        this.clients = new DynamicClientArray(Math.max(existing.size(), 10));
        existing.forEach(c -> {
            try {
                clients.add(c);
            } catch (Exception ignored) { }
        });
    }

    public void create(Client client) throws Exception {
        clients.add(client);
        saveAllClients();
    }

    public void delete(Client client) throws Exception {
        userRepository.delete(client);
        clients.delete(client);
        saveAllClients();
    }


    public void save() throws Exception {
        userRepository.save();
        saveAllClients();
    }

    public Client getByUser(User user) throws Exception {
        return clients.getByUser(user);
    }


    @SuppressWarnings("unchecked")
    private List<Client> loadAllClients() throws Exception {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Client>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Erro ao ler clientes do arquivo.", e);
        }
    }

    private void saveAllClients() throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(clients.toList());
        } catch (IOException e) {
            throw new Exception("Erro ao salvar clientes no arquivo.", e);
        }
    }
}
