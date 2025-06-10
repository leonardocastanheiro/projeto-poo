package br.ucs.projetosistemaprodutos.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.ucs.projetosistemaprodutos.models.itens.Product;

public class DynamicProductArray {
    private final List<Product> products;

    public DynamicProductArray() {
        products = new ArrayList<>();
    }

    public void add(Product product) throws Exception {
        for(Product productAux : products) {
            if(product != productAux && Objects.equals(product.getId(), productAux.getId())) {
                throw new Exception("Produto já existe");
            }
        }

        products.add(product);
    }

    public void delete(Product product) throws Exception {
         if(!products.remove(product)) {
             throw new Exception("Produto não encontrado");
         }
    }

	public void showArray() throws Exception {

        if (products.isEmpty()) {
            throw new Exception("Ainda não há produtos cadastrados no sistema.");
        }

        for (Product productAux : products) {
            System.out.println(productAux.toString());
        }

    }
    
    public Product getByIndex(int index) throws Exception {
        if (index >= 0 && index < products.size()) {
            return products.get(index);
        }
        throw new Exception("Index inválido");
    }

    public Product getById(int id) throws Exception {
        for(Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new Exception("ID do produto inválido");
    }

    public Product getByProductCode(String productCode) throws Exception {

        productCode = productCode.trim();

        for (Product product : products) {
            if (product != null && product.getProductCode() != null &&
            	product.getProductCode().trim().equalsIgnoreCase(productCode)) {
                	return product;
            }
        }

        throw new Exception("Código do produto inválido");
    }
    
    public void showProductById(int id) throws Exception{

        if (products.isEmpty()) {
            throw new Exception("Ainda não há produtos cadastrados no sistema.");
        }

        for (Product productAux : products) {
            if (productAux.getSupplier().getId() == id) {
                System.out.println(productAux.toString());
            }
        }

    }

    public List<Product> getByText(String text) throws Exception {
        List<Product> products = new ArrayList<>();

        if(text != null) {
        	text = text.toLowerCase();
        
	        for(Product product : this.products) {
	            if(product != null && (product.getName().toLowerCase().contains(text) ||
	                    product.getDescription().toLowerCase().contains(text))) {
	                products.add(product);
	            }
	        }
        }
        
	   if(products.isEmpty()) {
		   throw new Exception("Nenhum produto existente com essa correspondência");
	   }
        
        return products;
    }

	public List<Product> getAllProducts() {
        return products;
	}

    public int size() {
        return products.size();
    }
    
    public boolean isProductCodeExists(String productCode, Product product) {
    	for(Product p : products) {
    		if(p != null && p.getProductCode().equals(productCode) && !p.equals(product)) {
    			return true;
    		}
    	}
    	
    	return false;
    }
}
