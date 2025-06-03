package br.ucs.projetosistemaprodutos.models.copies;

import br.ucs.projetosistemaprodutos.repositories.DynamicProductArray;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;

public class SupplierCopy extends Supplier{
    private Integer id;
    private Role role;
    private String name;
    private String phone;
    private String email;
    private Address address;
    private String description;
    private DynamicProductArray products;

    public SupplierCopy(Supplier supplier) {
        this.id = supplier.getId();
        this.role = supplier.getRole();
        this.name = supplier.getName();
        this.phone = supplier.getPhone();
        this.email = supplier.getEmail();
        this.address = new Address(supplier.getAddress().getStreet(),supplier.getAddress().getNumber(),
        		supplier.getAddress().getComplement(), supplier.getAddress().getNeighborhood(),
        		supplier.getAddress().getCep(), supplier.getAddress().getCity(), supplier.getAddress().getState());
        this.description = supplier.getDescription();
        this.products = supplier.getProducts();
    }

	public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setProducts(DynamicProductArray products) {
		this.products = products;
	}
	
    public Integer getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
    
    public String getDescription() {
 		return description;
 	}
    
    public DynamicProductArray getProducts() {
		return products;
	}

}
