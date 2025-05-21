package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.collections.DynamicProductArray;
import br.ucs.projetosistemaprodutos.collections.DynamicSupplierArray;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.copies.ProductCopy;
import br.ucs.projetosistemaprodutos.models.copies.SupplierCopy;
import br.ucs.projetosistemaprodutos.models.itens.Product;
import br.ucs.projetosistemaprodutos.models.itens.Stock;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;

public class ProductController {
	private DynamicProductArray productArray;
		
	public ProductController(Store store){
		this.productArray = store.getProductArray();
	}
	
	public void create(Product product) throws Exception {
		productArray.add(product);
		product.getSupplier().getProducts().add(product);
	}
	 
	public void delete(Product product) throws Exception {
		productArray.delete(product);
		product.getSupplier().getProducts().delete(product);
	}
	 
	public void edit(Product product, ProductCopy copy) throws Exception { 
		product.setName(copy.getName());
        product.setDescription(copy.getDescription());
        product.setSupplier(copy.getSupplier());
        
        Stock stock = product.getStock();
        Stock copyStock = copy.getStock();
        
        stock.setQuantity(copyStock.getQuantity());
        stock.setPrice(copy.getStock().getPrice());
                
    }
	
	public void showArray() throws Exception{
	    productArray.showArray();
	}

	public Product getById(int id) throws Exception{
		return productArray.getById(id);
	}

	@Override
	public String toString() {
		return productArray.toString();
	}
}