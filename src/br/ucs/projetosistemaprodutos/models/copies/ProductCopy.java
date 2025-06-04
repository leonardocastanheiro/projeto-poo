package br.ucs.projetosistemaprodutos.models.copies;

import br.ucs.projetosistemaprodutos.models.itens.Product;
import br.ucs.projetosistemaprodutos.models.itens.Stock;
import br.ucs.projetosistemaprodutos.models.person.Supplier;

public class ProductCopy {
    private String name;
    private String description;
    private Byte [] photo;
    private Stock stock;
    private Integer id;
    private Supplier supplier;
    private String productCode;
    
   
	public ProductCopy(Product product) {
    	this.name = product.getName();
    	this.description = product.getDescription();
    	this.stock = new Stock(product.getStock().getQuantity(), product.getStock().getPrice());
    	this.id = product.getId();
    	this.supplier = product.getSupplier();
    	this.productCode = product.getProductCode();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}
