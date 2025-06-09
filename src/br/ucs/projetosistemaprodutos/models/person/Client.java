package br.ucs.projetosistemaprodutos.models.person;

import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.itens.Order;
import br.ucs.projetosistemaprodutos.models.itens.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private String creditCard;
    private ShoppingCart shoppingCart;
    private List<Order> orders;

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public Client() {
    }
    public Client(String login, String password, Role role, String name, String phone, String email, String creditCard, Address address) {
        super(login, password, role, name, phone, email, address);
        this.creditCard = creditCard;
        this.shoppingCart = new ShoppingCart(this);
        this.orders = new ArrayList<>();
    }

    public Client(User user, String creditCard) {
        super(user.getLogin(), user.getPassword(), user.getRole(), user.getName(), user.getPhone(), user.getEmail(), user.getAddress());
        this.creditCard = creditCard;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

}
