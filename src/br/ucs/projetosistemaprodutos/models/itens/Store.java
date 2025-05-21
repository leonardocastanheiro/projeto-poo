package br.ucs.projetosistemaprodutos.models.itens;

import br.ucs.projetosistemaprodutos.collections.*;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;

public class Store {
    private final DynamicProductArray productArray = new DynamicProductArray(10);
    private final DynamicStockArray stockArray = new DynamicStockArray(10);
    private final DynamicSupplierArray supplierArray = new DynamicSupplierArray(10);
    private final DynamicUserArray userArray = new DynamicUserArray(10);

    private final String name;

    public Store(String name) {
        this.name = name;
        setUserArray(this.userArray);
        setSupplierArray(this.supplierArray);
        setProductArray(this.productArray);
    }

    private void setSupplierArray(DynamicSupplierArray supplierArray) {
    	Supplier[] suppliers = new Supplier[] {
    			new Supplier(Role.SUPPLIER, "Paralela", "54996853348", "livrosparalela@contato.com", "Livros e produtos de papelaria", 
    	    			new Address("Rua das Laranjeiras", "92", "Bloco 2", "Bairro Limoeiro", "95010-260", "Caxias do Sul", "RS")),
    			new Supplier(Role.SUPPLIER, "Estrela", "54995856646", "brinquedosestrela@contato.com", "Brinquedos", 
    	    			new Address("Rua das Mimosas", "89", "", "Bairro Moreira", "98651-880", "Porto Alegre", "RS"))
    	};
    	
    	for(Supplier supplier : suppliers) {
        	supplierArray.add(supplier);
    	}
    }
    
    private void setProductArray(DynamicProductArray productArray){ 
    	Product[] products;
    	
    	try {
  			products = new Product[] {
  	    		new Product("Livro", "Livro de colorir 50pg", new Stock(10, 49.9), supplierArray.getByIndex(0)),
  	    		new Product("Caderno", "Caderno rosa Hello Kitty", new Stock(50, 25.5), supplierArray.getByIndex(0)),
  	    		new Product("Boneca", "Boneca Baby Alive", new Stock(15, 120.9), supplierArray.getByIndex(1)),
  	    		new Product("Livro", "Livro infanto-juvenil", new Stock(15, 49.9), supplierArray.getByIndex(0)),
  	    		new Product("Carrinho", "Hot Wheels", new Stock(100, 20.5), supplierArray.getByIndex(1)),
  	    	};
  	 
    	}catch(Exception e) {
    		products = new Product[] {
      	    		new Product("Livro", "Livro de colorir 50pg"),
      	    		new Product("Caderno", "Caderno rosa Hello Kitty"),
      	    		new Product("Boneca", "Boneca Baby Alive"),
      	    		new Product("Livro", "Livro infanto-juvenil"),
      	    		new Product("Carrinho", "Hot Wheels"),
      	    };
    	}
    			
    	for(Product product : products) {
        	productArray.add(product);
    	}
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
    private void setUserArray(DynamicUserArray userArray) {
    	Client user = new Client("joaosilva", "senha123", Role.CLIENT, "João Silva", "54999999999", "joao@gmail.com", "12345",
    			new Address("Rua das Amoras", "10", "Apto 10", "Bairro Frutas", "12345-999", "Curitiba", "Paraná"));
    	try {
			userArray.add(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public String getName() {
        return name;
    }
}
