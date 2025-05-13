package br.ucs.projetosistemaprodutos.models.person;

import br.ucs.projetosistemaprodutos.models.address.Address;

public class Client extends User {
    private String creditCard;

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public Client(String login, String password, Role role, String name, String phone, String email, String creditCard, Address address) {
        super(login, password, role, name, phone, email, address);
        this.creditCard = creditCard;
    }

    public Client(User user, String creditCard) {
        super(user.getLogin(), user.getPassword(), user.getRole(), user.getName(), user.getPhone(), user.getEmail(), user.getAddress());
        this.creditCard = creditCard;
    }
}
