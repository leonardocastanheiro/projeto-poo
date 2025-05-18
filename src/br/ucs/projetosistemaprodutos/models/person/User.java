package br.ucs.projetosistemaprodutos.models.person;

import br.ucs.projetosistemaprodutos.models.address.Address;

public abstract class User extends Person {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public User(String login, String password, Role role, String name, String phone, String email, Address address) {
        super(role, name, phone, email, address);
        this.login = login;
        this.password = password;
    }
    
    @Override
    public String toString() {
    	return  "Login: " + this.login + "Senha: " + this.password + ", " +super.toString();
    }
}
