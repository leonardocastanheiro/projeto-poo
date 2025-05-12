package projeto_sistema_produtos.controllers.users;

import projeto_sistema_produtos.models.itens.Store;
import projeto_sistema_produtos.models.person.Client;
import projeto_sistema_produtos.models.person.Role;

public class ClientController extends UserController{

    public ClientController(Store store) {
        super(store);
    }

    public void create(Client client) throws Exception {
        client.setRole(Role.CLIENT);
        super.create(client);
    }

    public void delete(Client client) throws Exception {
        super.delete(client);
    }
}
