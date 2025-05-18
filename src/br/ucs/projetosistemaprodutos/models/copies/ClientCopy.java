package br.ucs.projetosistemaprodutos.models.copies;

import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.models.person.Role;

public class ClientCopy {

    private Integer id;
    private Role role;
    private String name;
    private String phone;
    private String email;
    private Address address;
    private String login;
    private String password;
    private String creditCard;

    public ClientCopy(Client client) {
        this.id = client.getId();
        this.role = client.getRole();
        this.name = client.getName();
        this.phone = client.getPhone();
        this.email = client.getEmail();
        this.address = new Address(client.getAddress().getStreet(),client.getAddress().getNumber(),
                client.getAddress().getComplement(), client.getAddress().getNeighborhood(),
                client.getAddress().getCep(), client.getAddress().getCity(), client.getAddress().getState());
        this.login = client.getLogin();
        this.password = client.getPassword();
        this.creditCard = client.getCreditCard();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public Integer getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getCreditCard() {
        return creditCard;
    }
}
