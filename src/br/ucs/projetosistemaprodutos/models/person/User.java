package br.ucs.projetosistemaprodutos.models.person;

import br.ucs.projetosistemaprodutos.models.address.Address;

import java.io.Serial;
import java.io.Serializable;

public abstract class User extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

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
    
    public User() {
    }
    public User(String login, String password, Role role, String name, String phone, String email, Address address) {
        super(role, name, phone, email, address);
        this.login = login;
        this.password = password;
    }


    @Override
    public String toString() {
    	return  "ID: "+this.getId()+ " | Nome: " + this.getName() + " | Login: "+this.getLogin();
    }
}
