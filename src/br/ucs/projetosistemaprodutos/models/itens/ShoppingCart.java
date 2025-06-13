package br.ucs.projetosistemaprodutos.models.itens;

import br.ucs.projetosistemaprodutos.collections.DynamicProductArray;
import br.ucs.projetosistemaprodutos.models.person.Client;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    private Map<Product, Integer> products;
    private Client owner;

    public ShoppingCart(Client owner) {
        products = new HashMap<>();
        this.owner = owner;
    }

    public void add(Product product, Integer quantity) {
        if (quantity <= 0) {
            products.remove(product);
        } else {
            products.put(product, quantity);
        }
    }

    public Map<Product, Integer> getProducts() {
        return this.products;
    }

    public void remove(Product product, Integer quantity) {
        if (products.containsKey(product)) {
            int current = products.get(product);
            if (current <= quantity) {
                products.remove(product);
            } else {
                products.put(product, current - quantity);
            }
        }
    }

    public void clearCart() {
        products.clear();
    }

    public int getQuantity(Product product) {
        return products.getOrDefault(product, 0);
    }

    public void setQuantity(Product product, int newQuantity) {
        if (newQuantity <= 0) {
            products.remove(product);
        } else {
            products.put(product, newQuantity);
        }
    }
}
