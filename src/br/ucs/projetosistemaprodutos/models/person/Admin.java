package br.ucs.projetosistemaprodutos.models.person;

import br.ucs.projetosistemaprodutos.models.address.Address;

import java.io.Serial;
import java.io.Serializable;

public class Admin extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    public Admin(String login, String password, Role role, String name, String phone, String email, Address address) {
        super(login, password, role, name, phone, email, address);
    }

}
