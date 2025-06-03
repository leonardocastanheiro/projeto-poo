package br.ucs.projetosistemaprodutos.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ucs.projetosistemaprodutos.models.itens.Product;

public class ProductRepository {
    private Product [] products;
    private Integer count;

    public ProductRepository(Integer initialCapacity) {
        products = new Product[initialCapacity];
        count = 0;
    }

    public void add(Product product) {
        if(count == products.length) {
            int newCapacity = products.length * 2;
            products = Arrays.copyOf(products, newCapacity);
        }
        products[count++] = product;
    }

    public void delete(Product product) throws Exception {
        int indexToRemove = -1;
        for (int i = 0; i < count; i++) {
            if (products[i] == product) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            throw new Exception("Product not found.");
        }

        for (int i = indexToRemove; i < count - 1; i++) {
            products[i] = products[i + 1];
        }

        products[count - 1] = null;
        count--;
    }
    
    public Integer getCount() {
		return count;
	}

	public void showArray() throws Exception {
        boolean existsProduct = false;

        for (Product productAux : products) {
            if (productAux != null) {
                System.out.println(productAux.toString());
                existsProduct = true;
            }
        }

        if (!existsProduct) {
            throw new Exception("Ainda não há produtos cadastrados no sistema.");
        }
    }
    
    public Product getByIndex(int index) throws Exception {
        if (index >= 0 && index < count) {
            return products[index];
        }
        throw new Exception("Index inválido");
    }

    public Product getById(int id) throws Exception {
        for(Product product : products) {
            if(product != null) {
                if (product.getId() == id) {
                    return product;
                }
            }
        }
        throw new Exception("Id do produto inválido");
    }

    public void showProductsArray(int id) throws Exception{
    	boolean existsProduct = false;

        for (Product productAux : products) {
            if (productAux != null && productAux.getSupplier().getId() == id) {
                System.out.println(productAux.toString());
                existsProduct = true;
            }
        }

        if (!existsProduct) {
            throw new Exception("Ainda não há produtos cadastrados no sistema.");
        }
    }

    public List<Product> getByText(String text) throws Exception {
        List<Product> products = new ArrayList<>();

        text = text.toLowerCase();

        for(Product product : this.products) {
            if(product != null && (product.getName().toLowerCase().contains(text) ||
                    product.getDescription().toLowerCase().contains(text))) {
                products.add(product);
            }
        }

        if(products.isEmpty()) {
            throw new Exception("Nenhum produto existente com essa correspondência");
        }

        return products;
    }

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>(count);

        for(Product product : this.products) {
            if(product != null) {
                products.add(product);
            }
        }

        return products;
	}


    public int size() {
        return count;
    }
    
}
