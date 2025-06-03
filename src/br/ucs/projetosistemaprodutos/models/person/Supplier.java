package br.ucs.projetosistemaprodutos.models.person;

import br.ucs.projetosistemaprodutos.repositories.ProductRepository;
import br.ucs.projetosistemaprodutos.models.address.Address;

public class Supplier extends Person {
    private String description;
    private ProductRepository products;

    public Supplier() {
        this.products = new ProductRepository(10);
    }

    public Supplier(Role role, String name, String phone, String email, Address address) {
        super(role, name, phone, email, address);
        this.products = new ProductRepository(10);
    }

    public Supplier(Role role, String name, String phone, String email, String description, Address address) {
        super(role, name, phone, email, address);
        this.description = description;
        this.products = new ProductRepository(10);
    }

    public Supplier(Role role, String name, String phone, String email, String description, Address address, ProductRepository products) {
        super(role, name, phone, email, address);
        this.description = description;
        this.products = (products != null) ? products : new ProductRepository(10);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductRepository getProducts() {
        return products;
    }

    public void setProducts(ProductRepository products) {
        this.products = products;
    }

    public String toString() {
        return super.toString() + "| Descrição: " + this.description;
    }
}

