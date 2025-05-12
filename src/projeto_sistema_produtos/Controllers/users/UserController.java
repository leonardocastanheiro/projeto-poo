package projeto_sistema_produtos.controllers.users;

import projeto_sistema_produtos.collections.DynamicUserArray;
import projeto_sistema_produtos.models.itens.Store;
import projeto_sistema_produtos.models.person.User;

public class UserController {
    private DynamicUserArray userArray;

    UserController(Store store) {
        this.userArray = store.getUserArray();
    }

    protected void create(User user) throws Exception {
        userArray.add(user);
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

    protected void delete(User user) throws Exception {
        userArray.delete(user);
    }

}
