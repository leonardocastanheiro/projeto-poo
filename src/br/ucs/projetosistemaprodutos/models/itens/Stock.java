package br.ucs.projetosistemaprodutos.models.itens;

public class Stock {
    private Integer quantity;
    private Double price;
    private Integer id;
    
    private static Integer lastId = 1;
    
    public Stock() {
    }
    public Stock(Integer quantity, Double price) {
    	this.id = lastId++;
        this.quantity = quantity;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Integer getId() {
        return id;
    }


}
