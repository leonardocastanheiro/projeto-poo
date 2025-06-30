package br.ucs.projetosistemaprodutos.models.person;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.itens.Order;
import br.ucs.projetosistemaprodutos.utils.IdManager;

import java.io.Serial;
import java.io.Serializable;

public abstract class Person implements Comparable<Person>, Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    private Role role;
    private String name;
    private String phone;
    private String email;
    private Address address;
    private Integer id;

    private static IdManager idManager = new IdManager("person");

    public Person() {
    }

    public Person(Role role, String name, String phone, String email, Address address) {
        int lastId = idManager.loadLastId();
        this.id = lastId++;
        idManager.saveLastId(lastId);
        this.role = role;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getRoleString() {
    	return role.toString();
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }
    
    public String toString() {
    	return "ID: " +this.getId()+ "| Nome: " + this.getName();
    }
    
    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

}
