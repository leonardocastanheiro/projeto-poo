package br.ucs.projetosistemaprodutos.models.itens;

import br.ucs.projetosistemaprodutos.models.person.Supplier;
import br.ucs.projetosistemaprodutos.utils.IdManager;

import java.io.Serial;
import java.io.Serializable;

public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    private String name;
    private String description;
    private Byte [] photo;
    private Stock stock;
    private Integer id;
    private Supplier supplier;
    private String productCode;

    private static final IdManager idManager = new IdManager("product");

    public Product() {
    }
    public Product(String name, String description) {
        int lastId = idManager.loadLastId();
        this.id = lastId++;
        idManager.saveLastId(lastId);
        this.name = name;
        this.description = description;
    }
    public Product(String name, String description, Supplier supplier, String productCode) {
        int lastId = idManager.loadLastId();
        this.id = lastId++;
        idManager.saveLastId(lastId);
        this.name = name;
        this.description = description;
        this.supplier = supplier;
        this.stock = new Stock(0, 0.0);
        this.productCode = productCode;
    }
	public Product(String name, String description, Stock stock, Supplier supplier, String productCode) {
        int lastId = idManager.loadLastId();
        this.id = lastId++;
        idManager.saveLastId(lastId);
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.supplier = supplier;
        this.productCode = productCode;
    }
    public Product(String name, String description, Byte[] photo, Stock stock) {
        int lastId = idManager.loadLastId();
        this.id = lastId++;
        idManager.saveLastId(lastId);
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.stock = stock;
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
	
	public String toString() {
		return "ID: " + this.getId() + " | Produto: " + this.getName();
	}
}
