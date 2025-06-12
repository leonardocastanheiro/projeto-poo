package br.ucs.projetosistemaprodutos.models.itens;

import br.ucs.projetosistemaprodutos.serialize.IdManager;

public class Stock {
    private Integer quantity;
    private Double price;
    private Integer id;
    
    private static IdManager idManager = new IdManager("stock");
    
    public Stock() {
    }
    public Stock(Integer quantity, Double price) {
        int lastId = idManager.loadLastId();
        this.id = lastId++;
        idManager.saveLastId(lastId);
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
