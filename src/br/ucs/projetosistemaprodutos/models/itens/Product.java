package br.ucs.projetosistemaprodutos.models.itens;

import br.ucs.projetosistemaprodutos.models.person.Supplier;

public class Product {
    private String name;
    private String description;
    private Byte [] photo;
    private Stock stock;
    private Integer id;
    private Supplier supplier;

    private static Integer lastId = 1;

    public Product() {
    }
    public Product(String name, String description) {
    	this.id = lastId++;
        this.name = name;
        this.description = description;
    }
    public Product(String name, String description, Supplier supplier) {
    	this.id = lastId++;
        this.name = name;
        this.description = description;
        this.supplier = supplier;
        this.stock = new Stock(0, 0.0);
    }
    public Product(String name, String description, Stock stock, Supplier supplier) {
        this.id = lastId++;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.supplier = supplier;
    }
    public Product(String name, String description, Byte[] photo, Stock stock) {
        this.id = lastId++;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }
    
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public String toString() {
		return "ID: " + this.getId() + " | Produto: " + this.getName();
	}


}
