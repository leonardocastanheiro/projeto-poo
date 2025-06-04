package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.repositories.DynamicProductArray;
import br.ucs.projetosistemaprodutos.models.copies.ProductCopy;
import br.ucs.projetosistemaprodutos.models.itens.Product;
import br.ucs.projetosistemaprodutos.models.itens.Stock;
import br.ucs.projetosistemaprodutos.models.itens.Store;

import java.util.ArrayList;
import java.util.List;

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
		
		product.getSupplier().getProducts().delete(product);
		productArray.delete(product);
		
	}
	 
	public void edit(Product product, ProductCopy copy) throws Exception { 
		if(productArray.isProductCodeExists(copy.getProductCode(), product)) {
            throw new Exception("Código do produto já registrado!");
        }
		
		product.setName(copy.getName());
        product.setDescription(copy.getDescription());
        product.setSupplier(copy.getSupplier());
        product.setProductCode(copy.getProductCode());
        
        Stock stock = product.getStock();
        Stock copyStock = copy.getStock();
        
        stock.setQuantity(copyStock.getQuantity());
        stock.setPrice(copy.getStock().getPrice());
                
    }
	
	public void showArray() throws Exception{
	    productArray.showArray();
	}

	public void showProductsArray(int id) throws Exception{
		productArray.showProductsArray(id);
	}
	
	public Product getById(int id) throws Exception{
		return productArray.getById(id);
	}

	public Product getByProductCode(String productCode) throws Exception{
		return productArray.getByProductCode(productCode);
	}
	
	public List<Product> getByText(String text) throws Exception {
		int id = -1;
		List<Product> products = new ArrayList<>();

		try {
			id = Integer.parseInt(text);
			text = null;
		} catch (Exception ignored) {}

		if(text == null) {
			products.add(this.getById(id));

			return products;
		}

		return productArray.getByText(text);
	}

	@Override
	public String toString() {
		return productArray.toString();
	}
}