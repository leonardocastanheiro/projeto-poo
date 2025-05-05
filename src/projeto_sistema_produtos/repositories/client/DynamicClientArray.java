package projeto_sistema_produtos.repositories.client;

import projeto_sistema_produtos.models.person.Client;
import projeto_sistema_produtos.models.person.User;

import java.util.Arrays;
import java.util.List;

public class DynamicClientArray {
    private Client[] clients;
    private int count;

    public DynamicClientArray(int initialCapacity) {
        clients = new Client[initialCapacity];
        count = 0;
    }

    public void add(Client client) {
        if(count == clients.length) {
            int newCapacity = clients.length * 2;
            clients = Arrays.copyOf(clients, newCapacity);
        }
        clients[count++] = client;
    }

    public void delete(Client client) throws Exception {
        int indexToRemove = -1;
        for (int i = 0; i < count; i++) {
            if (clients[i] == client) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            throw new Exception("Client not found.");
        }

        for (int i = indexToRemove; i < count - 1; i++) {
            clients[i] = clients[i + 1];
        }

        clients[count - 1] = null;
        count--;
    }

    public User getByIndex(int index) throws Exception {
        if (index >= 0 && index < count) {
            return clients[index];
        }
        throw new Exception("Invalid index");
    }

    public Client getByUser(User user) throws Exception {
        for(Client clientTemporary : clients) {
            if(clientTemporary != null) {
                if (clientTemporary == user) {
                    return clientTemporary;
                }
            }
        }
        throw new Exception("Invalid User");
    }

    public List<User> toList() {
        return Arrays.asList(Arrays.copyOf(clients, count));
    }

}
