package projeto_sistema_produtos.models.person;

import projeto_sistema_produtos.models.address.Address;

public class Supplier extends Person{
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Supplier(Role role, String name, String phone, String email, String description, Address address) {
        super(role, name, phone, email, address);
        this.description = description;
    }

}
