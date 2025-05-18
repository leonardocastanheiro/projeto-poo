package br.ucs.projetosistemaprodutos.models.person;

import br.ucs.projetosistemaprodutos.models.address.Address;

public class Admin extends User{
    public Admin(String login, String password, Role role, String name, String phone, String email, Address address) {
        super(login, password, role, name, phone, email, address);
    }
    
    @Override
    public String toString() {
    	return super.toString();
    }
}
