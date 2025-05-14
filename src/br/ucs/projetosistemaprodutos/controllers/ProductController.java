package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.collections.DynamicProductArray;
import br.ucs.projetosistemaprodutos.models.itens.Product;
import br.ucs.projetosistemaprodutos.models.itens.Store;

public class ProductController {
	private DynamicProductArray productArray;
	
	ProductController(Store store){
		this.productArray = store.getProductArray();
	}
	
	 protected void create(Product product) throws Exception {
	        productArray.add(product);
	    }
	 
	 protected void delete(Product product) throws Exception {
		 productArray.delete(product);
	 }
	 
	 public Product getById(int id) throws Exception{
		 return productArray.getById(id);
	 }
}