package br.ucs.projetosistemaprodutos.models.itens;

import br.ucs.projetosistemaprodutos.collections.DynamicProductArray;
import br.ucs.projetosistemaprodutos.collections.DynamicStockArray;
import br.ucs.projetosistemaprodutos.collections.DynamicSupplierArray;
import br.ucs.projetosistemaprodutos.collections.DynamicUserArray;

public class Store {
    private final DynamicProductArray productArray = new DynamicProductArray(10);
    private final DynamicStockArray stockArray = new DynamicStockArray(10);
    private final DynamicSupplierArray supplierArray = new DynamicSupplierArray(10);
    private final DynamicUserArray userArray = new DynamicUserArray(10);

    private final String name;

    public Store(String name) {
        this.name = name;
    }

    public DynamicProductArray getProductArray() {
        return productArray;
    }

    public DynamicStockArray getStockArray() {
        return stockArray;
    }

    public DynamicSupplierArray getSupplierArray() {
        return supplierArray;
    }

    public DynamicUserArray getUserArray() {
        return userArray;
    }

    public String getName() {
        return name;
    }
}
