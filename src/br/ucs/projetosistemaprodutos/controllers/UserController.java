package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.collections.DynamicUserArray;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.User;

public class UserController {
    private DynamicUserArray userArray;

    UserController(Store store) {
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
}
