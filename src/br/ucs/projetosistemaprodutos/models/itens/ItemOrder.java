package br.ucs.projetosistemaprodutos.models.itens;

public class ItemOrder {
    private Integer quantity;
    private Double price;

    private Order order;
    private Product product;

    public ItemOrder(Integer quantity, Double price, Order order, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
