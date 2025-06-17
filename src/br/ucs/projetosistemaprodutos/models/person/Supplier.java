package br.ucs.projetosistemaprodutos.models.person;

import br.ucs.projetosistemaprodutos.collections.DynamicProductArray;
import br.ucs.projetosistemaprodutos.models.address.Address;

import java.io.Serial;
import java.io.Serializable;

public class Supplier extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    private String description;
    private DynamicProductArray products;

    public Supplier() {
        this.products = new DynamicProductArray();
    }

    public Supplier(Role role, String name, String phone, String email, Address address) {
        super(role, name, phone, email, address);
        this.products = new DynamicProductArray();
    }

    public Supplier(Role role, String name, String phone, String email, String description, Address address) {
        super(role, name, phone, email, address);
        this.description = description;
        this.products = new DynamicProductArray();
    }

    public Supplier(Role role, String name, String phone, String email, String description, Address address, DynamicProductArray products) {
        super(role, name, phone, email, address);
        this.description = description;
        this.products = (products != null) ? products : new DynamicProductArray();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DynamicProductArray getProducts() {
        return products;
    }

    public void setProducts(DynamicProductArray products) {
        this.products = products;
    }

    public String toString() {
        return super.toString() + "| Descrição: " + this.description;
    }
}

