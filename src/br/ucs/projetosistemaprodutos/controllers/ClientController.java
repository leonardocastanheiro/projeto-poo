package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.collections.DynamicUserArray;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.copies.ClientCopy;
import br.ucs.projetosistemaprodutos.models.itens.Order;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.User;

import java.util.ArrayList;
import java.util.List;

public class ClientController extends UserController{

    DynamicUserArray userArray;

    public ClientController(Store store) {
        super(store);
        userArray = store.getUserArray();
    }

    public void create(Client client) throws Exception {
        client.setRole(Role.CLIENT);
        super.create(client);
    }

    public void delete(Client client) throws Exception {

        if(client.getRole() != Role.CLIENT) {
            throw new Exception("Esse usuário não é um cliente");
        }

        super.delete(client);
    }
    
    public void showArray() throws Exception{
    	super.showArray(Role.CLIENT);
    }

    public void edit(Client client, ClientCopy copy) throws Exception {
        if(userArray.isLoginExists(copy.getLogin(), client)) {
            throw new Exception("Login já cadastrado");
        }

        if(userArray.isEmailExists(copy.getEmail(), client)) {
            throw new Exception("E-mail já cadastrado");
        }

        client.setName(copy.getName());
        client.setPhone(copy.getPhone());
        client.setEmail(copy.getEmail());
        client.setLogin(copy.getLogin());
        client.setPassword(copy.getPassword());
        client.setCreditCard(copy.getCreditCard());

        Address address = client.getAddress();
        Address copyAddress = copy.getAddress();

        address.setStreet(copyAddress.getStreet());
        address.setNumber(copyAddress.getNumber());
        address.setComplement(copyAddress.getComplement());
        address.setNeighborhood(copyAddress.getNeighborhood());
        address.setCep(copyAddress.getCep());
        address.setCity(copyAddress.getCity());
        address.setState(copyAddress.getState());
    }

    @Override
    public User getById(int id) throws Exception {

        User user = super.getById(id);

        if(user.getRole() != Role.CLIENT) {
            throw new Exception("Esse usuário não é um cliente");
        }

        return user;
    }

    public List<Client> getByText(String text) throws Exception {
        List<Client> clients = new ArrayList<>();

        for(User user : super.getByText(text, Role.CLIENT)) {
            clients.add((Client) user);
        }

        return clients;
    }

    public void addOrder(Order order) {
        order.getOwner().getOrders().add(order);
    }
}
