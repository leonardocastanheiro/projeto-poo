package br.ucs.projetosistemaprodutos.models.person;

import br.ucs.projetosistemaprodutos.models.address.Address;

public class Supplier extends Person{
    private DynamicProductArray products;
    private String description;

    public Supplier(Role role, String name, String phone, String email, Address address) {
    	super(role, name, phone, email, address);
    }
    
    public Supplier(Role role, String name, String phone, String email, String description, Address address) {
        super(role, name, phone, email, address);
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
