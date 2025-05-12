package projeto_sistema_produtos.models.person;

import projeto_sistema_produtos.models.address.Address;

public class Admin extends User{
    public Admin(String login, String password, Role role, String name, String phone, String email, Address address) {
        super(login, password, role, name, phone, email, address);
    }
}
