package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.repositories.DynamicUserArray;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private DynamicUserArray userArray;

    public UserController(Store store) {
        this.userArray = store.getUserArray();
    }

    protected void create(User user) throws Exception {
        userArray.add(user);
    }

    protected void delete(User user) throws Exception {
        userArray.delete(user);
    }
    
    public User getById(int id) throws Exception {
        return userArray.getById(id);
    }

    public User getByEmail(String email) throws Exception {
        return userArray.getByEmail(email);
    }

    public User getByLogin(String login) throws Exception {
        return userArray.getByLogin(login);
    }
    
    public void showArray(Role role) throws Exception {
    	userArray.showArray(role);
    }

    public User verifyLoginAndPassword(String login, String password) throws Exception {
        User user = this.getByLogin(login);

        if(user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    protected List<User> getByText(String text, Role role) throws Exception {
        int id = -1;
        List<User> users = new ArrayList<>();

        try {
            id = Integer.parseInt(text);
            text = null;
        } catch (Exception ignored) {}

        if(text == null) {
            users.add(this.getById(id));

            return users;
        }

        return userArray.getByText(text, role);

    }
}
