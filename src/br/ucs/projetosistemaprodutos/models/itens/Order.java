package br.ucs.projetosistemaprodutos.models.itens;

import br.ucs.projetosistemaprodutos.models.person.Client;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Map;

public class Order implements Comparable<Order>{
    private static Integer lastId = 1;

    private Integer id;
    private Date dateOrder;
    private Date dateDeliver;
    private Date dateForward;
    private Situation situation;
    private Client owner;
    private double totalPrice;
    private double totalPriceICMS;
	private Map<Product, Integer> products;

    public Order(Date dateOrder, Date dateDeliver, Situation situation, Client owner, double totalPrice, double totalPriceICMS, Map<Product, Integer> products) {
        this.id = lastId++;
        this.dateOrder = dateOrder;
        this.dateDeliver = dateDeliver;
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

    public Date getDateDeliver() {
        return dateDeliver;
    }

    public void setDateDeliver(Date dataDeliver) {
        this.dateDeliver = dataDeliver;
    }

    public Date getDateForward() {
        return dateForward;
    }

    public void setDateForward(Date dateForward) {
        this.dateForward = dateForward;
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

    @Override
    public int compareTo(Order other) {
        return this.id.compareTo(other.id);
    }
}
