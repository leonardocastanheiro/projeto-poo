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

    private void setProductArray(DynamicProductArray productArray){ 
    	Product product = new Product();
    	try {
    		product = new Product("Livro", "Livro de colorir 50pg", new Stock(10, 49.9), supplierArray.getByIndex(0));
    	}catch(Exception e) {
    		product = new Product("Livro", "Livro de colorir 50pg");
    	}	
    	productArray.add(product);
    }
    public DynamicProductArray getProductArray() {
        return productArray;
    }

    public DynamicStockArray getStockArray() {
        return stockArray;
    }

    private void setSupplierArray(DynamicSupplierArray supplierArray) {
    	Supplier supplier = new Supplier(Role.SUPPLIER, "Paralela", "54996853348", "livrosparalela@contato.com", "Livros e produtos de papelaria", 
    			new Address("Rua das Laranjeiras", "92", "Bloco 2", "Bairro Limoeiro", "95010-260", "Caxias do Sul", "RS"));
    	supplierArray.add(supplier);
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
