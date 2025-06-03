package br.ucs.projetosistemaprodutos.models.person;

import br.ucs.projetosistemaprodutos.repositories.DynamicProductArray;
import br.ucs.projetosistemaprodutos.models.address.Address;

public class Supplier extends Person {
    private String description;
    private DynamicProductArray products;

    public Supplier() {
        this.products = new DynamicProductArray(10);
    }

    public Supplier(Role role, String name, String phone, String email, Address address) {
        super(role, name, phone, email, address);
        this.products = new DynamicProductArray(10);
    }

    public Supplier(Role role, String name, String phone, String email, String description, Address address) {
        super(role, name, phone, email, address);
        this.description = description;
        this.products = new DynamicProductArray(10);
    }

    public Supplier(Role role, String name, String phone, String email, String description, Address address, DynamicProductArray products) {
        super(role, name, phone, email, address);
        this.description = description;
        this.products = (products != null) ? products : new DynamicProductArray(10);
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

