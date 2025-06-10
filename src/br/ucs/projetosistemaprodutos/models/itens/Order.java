package br.ucs.projetosistemaprodutos.models.itens;

import br.ucs.projetosistemaprodutos.models.person.Client;

import java.util.Date;
import java.util.Map;

public class Order {
    private static Integer lastId = 1;

    private Integer id;
    private Date dateOrder;
    private Date dataDeliver;
    private Situation situation;
    private Client owner;
    private double totalPrice;
    private double totalPriceICMS;
	private Map<Product, Integer> products;

    public Order(Date dateOrder, Date dataDeliver, Situation situation, Client owner, double totalPrice, double totalPriceICMS, Map<Product, Integer> products) {
        this.id = lastId++;
        this.dateOrder = dateOrder;
        this.dataDeliver = dataDeliver;
        this.situation = situation;
        this.owner = owner;
        this.totalPrice = totalPrice;
        this.totalPriceICMS = totalPriceICMS;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Date getDataDeliver() {
        return dataDeliver;
    }

    public void setDataDeliver(Date dataDeliver) {
        this.dataDeliver = dataDeliver;
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public Client getOwner() {
        return this.owner;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
    
    public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotalPriceICMS() {
		return totalPriceICMS;
	}

	public void setTotalPriceICMS(double totalPriceICMS) {
		this.totalPriceICMS = totalPriceICMS;
	}
}
