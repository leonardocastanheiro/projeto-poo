package projeto_sistema_produtos.models.person;

import projeto_sistema_produtos.models.address.Address;

public class Client extends User{
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
}
