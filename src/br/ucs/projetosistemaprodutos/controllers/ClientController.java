package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.models.person.Role;

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
