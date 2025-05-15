package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.collections.DynamicProductArray;
import br.ucs.projetosistemaprodutos.models.itens.Product;
import br.ucs.projetosistemaprodutos.models.itens.Store;

public class ProductController {
	private DynamicProductArray productArray;
	
	public ProductController(Store store){
		this.productArray = store.getProductArray();
	}
	
	public void create(Product product) throws Exception {
		productArray.add(product);
	}
	 
	public void delete(Product product) throws Exception {
		productArray.delete(product);
	}
	 
	public void update(Product copy, Product original) throws Exception{
		productArray.update(copy, original);
	}

	public Product getById(int id) throws Exception{
		return productArray.getById(id);
	}
}