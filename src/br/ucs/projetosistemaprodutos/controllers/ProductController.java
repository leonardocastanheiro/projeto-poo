package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.collections.DynamicProductArray;
import br.ucs.projetosistemaprodutos.exceptions.InsufficientStockException;
import br.ucs.projetosistemaprodutos.models.copies.ProductCopy;
import br.ucs.projetosistemaprodutos.models.itens.Order;
import br.ucs.projetosistemaprodutos.models.itens.Product;
import br.ucs.projetosistemaprodutos.models.itens.Stock;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.utils.StoreManager;

import java.time.LocalDate;
import java.util.*;

public class ProductController {
	private DynamicProductArray productArray;
	private Store store;
	private StoreManager storeManager;
		
	public ProductController(Store store){
		this.storeManager = new StoreManager();
		this.store = store;
		this.productArray = store.getProductArray();
	}
	
	public void create(Product product) throws Exception {
		productArray.add(product);
		
		product.getSupplier().getProducts().add(product);

		storeManager.save(store);
	}
	 
	public void delete(Product product) throws Exception {
		
		product.getSupplier().getProducts().delete(product);
		productArray.delete(product);

		storeManager.save(store);
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

		storeManager.save(store);
    }
	
	public void showArray() throws Exception{
	    productArray.showArray();
	}

	public void showProductById(int id) throws Exception{
		productArray.showProductById(id);
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
			products.add(this.getByProductCode(text));
			return products;
		}catch(Exception ignored) {}
		
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

	public Map<Product, Integer> mapProducts(List<Product> products) {
		Map<Product, Integer> mapProduct = new HashMap<>();

		for(Product product : products) {
			mapProduct.put(product, mapProduct.getOrDefault(product, 0) + 1);
		}

		return mapProduct;
	}

		public void changeStock(Product product, int delta) throws Exception {
			int currentStock = product.getStock().getQuantity();
			System.out.println("[DEBUG] Alterando estoque de " + product.getName() +
					" | Delta: " + delta +
					" | Estoque anterior: " + currentStock);

			int updatedStock = currentStock + delta;

			if (updatedStock < 0) {
				throw new IllegalArgumentException("Estoque insuficiente. Estoque atual: " +
						currentStock + ", tentativa de alteração: " + delta);
			}

			product.getStock().setQuantity(updatedStock);
			storeManager.save(store);
		}

		public void addToStock(Product product, int quantity) throws Exception {
			changeStock(product, +Math.abs(quantity));
		}

		public void removeFromStock(Product product, int quantity) throws Exception {
			changeStock(product, -Math.abs(quantity));
		}

		public void editCartItem(Client client, Product product, int newQuantity) throws Exception {
			if (newQuantity == 0) {
				client.getShoppingCart().getProducts().remove(product);
			} else {
				client.getShoppingCart().getProducts().put(product, newQuantity);
			}
			storeManager.save(store);
		}


	public Order getOrderByList(Integer id, List<Order> orders) throws Exception {
		for(Order order : orders) {
			if(id.equals(order.getId())) {
				return order;
			}
		}

		throw new Exception("Pedido não encontrado");
	}
	
	public List<Order> getOrdersByDate(LocalDate date1, List<Order> orders){
		List<Order> ordersPeriod = new ArrayList<>();
		
		for(Order clientOrder : orders) {
			LocalDate dateOrderClient = clientOrder.getDateOrder();
			
			if(dateOrderClient.equals(date1)) {
				ordersPeriod.add(clientOrder);
			}
		}

		Collections.sort(ordersPeriod);
		return ordersPeriod;
	}
	public List<Order> getOrdersByDate(LocalDate date1, LocalDate date2, List<Order> orders){
		List<Order> ordersPeriod = new ArrayList<>();
		
		for(Order clientOrder : orders) {
			LocalDate dateOrderClient = clientOrder.getDateOrder();
			
			if(!dateOrderClient.isBefore(date1) && !dateOrderClient.isAfter(date2)) {
				ordersPeriod.add(clientOrder);
			}
		}

		Collections.sort(ordersPeriod);
		return ordersPeriod;
	}
	
    public void emptyStock(Stock stock) throws InsufficientStockException{
    	if(stock.getQuantity() == 0) {
    		throw new InsufficientStockException();
    	}
    }
    public void stockQuantity(Stock stock, int quantity) throws InsufficientStockException{
    	if(stock.getQuantity() < quantity) {
    		throw new InsufficientStockException();
    	}
    }
	@Override
	public String toString() {
		return productArray.toString();
	}
}