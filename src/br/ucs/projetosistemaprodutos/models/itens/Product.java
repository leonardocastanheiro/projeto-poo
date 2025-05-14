package br.ucs.projetosistemaprodutos.models.itens;

public class Product {
    private String name;
    private String description;
    //private Byte [] photo;
    private byte photo;
    private Stock stock;
    private Integer id;

    private static Integer lastId = 1;

    public Product() {
    }
    public Product(String name, String description, byte photo) {
    	this.id = lastId++;
        this.name = name;
        this.description = description;
        this.photo = photo;
    }
    public Product(String name, String description, byte photo, Stock stock) {
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

    public byte getPhoto() {
        return photo;
    }

    public void setPhoto(byte photo) {
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
}
