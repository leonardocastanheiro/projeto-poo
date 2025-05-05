package projeto_sistema_produtos.models.collections;

import projeto_sistema_produtos.models.itens.Product;
import projeto_sistema_produtos.models.person.User;

import java.util.Arrays;

public class DynamicProductArray {
    private Product [] products;
    private Integer count;

    private DynamicProductArray(Integer initialCapacity) {
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

    public Product getByIndex(int index) throws Exception {
        if (index >= 0 && index < count) {
            return products[index];
        }
        throw new Exception("Invalid index");
    }

    public Product getById(int id) throws Exception {
        for(Product product : products) {
            if(product != null) {
                if (product.getId() == id) {
                    return product;
                }
            }
        }
        throw new Exception("Invalid product ID.");
    }
}
