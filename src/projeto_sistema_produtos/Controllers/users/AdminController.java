package projeto_sistema_produtos.controllers.users;

import projeto_sistema_produtos.models.itens.Store;
import projeto_sistema_produtos.models.person.Admin;
import projeto_sistema_produtos.models.person.Client;
import projeto_sistema_produtos.models.person.Role;

public class AdminController extends UserController {
    AdminController(Store store) {
        super(store);
    }

    public void create(Admin admin) throws Exception {
        admin.setRole(Role.ADMIN);
        super.create(admin);
    }

    public void delete(Admin admin) throws Exception {
        super.delete(admin);
    }
}
