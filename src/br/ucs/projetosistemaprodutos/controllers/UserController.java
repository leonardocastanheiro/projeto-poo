package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.collections.DynamicUserArray;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.User;
import br.ucs.projetosistemaprodutos.utils.StoreManager;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final DynamicUserArray userArray;
    private final StoreManager storeManager;
    private final Store store;

    public UserController(Store store) {
        this.storeManager = new StoreManager();
        this.store = store;
        this.userArray = store.getUserArray();
    }

    protected void create(User user) throws Exception {
        userArray.add(user);
        storeManager.save(store);
    }

    protected void delete(User user) throws Exception {
        userArray.delete(user);
        storeManager.save(store);
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
