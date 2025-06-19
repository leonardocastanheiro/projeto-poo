package br.ucs.projetosistemaprodutos.models.itens;

import br.ucs.projetosistemaprodutos.collections.DynamicProductArray;
import br.ucs.projetosistemaprodutos.collections.DynamicStockArray;
import br.ucs.projetosistemaprodutos.collections.DynamicSupplierArray;
import br.ucs.projetosistemaprodutos.collections.DynamicUserArray;
import br.ucs.projetosistemaprodutos.controllers.ProductController;
import br.ucs.projetosistemaprodutos.controllers.SupplierController;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.person.Admin;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;

import java.io.Serial;
import java.io.Serializable;

public class Store implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    private final DynamicProductArray productArray;
    private final DynamicStockArray stockArray;
    private final DynamicSupplierArray supplierArray;
    private final DynamicUserArray userArray;
    
    private final String name;

    public Store(String name) {
        this.name = name;
        this.productArray = new DynamicProductArray();
        this.stockArray = new DynamicStockArray();
        this.supplierArray =  new DynamicSupplierArray();
        this.userArray = new DynamicUserArray();
    }
    
    public DynamicProductArray getProductArray() {
        return productArray;
    }

    public DynamicStockArray getStockArray() {
        return stockArray;
    }

	public DynamicUserArray getUserArray() {
		return userArray;
	}

    public DynamicSupplierArray getSupplierArray() {
        return supplierArray;
    }
    
    public String getName() {
        return name;
    }
}
