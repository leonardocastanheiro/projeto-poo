package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Admin;
import br.ucs.projetosistemaprodutos.models.person.Role;

public class AdminController extends UserController {
	
    public AdminController(Store store) {
        super(store);
    }

    public void create(Admin admin) throws Exception {
        admin.setRole(Role.ADMIN);
        super.create(admin);
    }

    public void delete(Admin admin) throws Exception {
        super.delete(admin);
    }
    
    public void showArray() throws Exception {
    	super.showArray(Role.ADMIN);
    }
}
