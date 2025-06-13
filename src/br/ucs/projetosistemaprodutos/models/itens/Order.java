package br.ucs.projetosistemaprodutos.models.itens;

import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.utils.IdManager;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Comparable<Order>, Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    private static final IdManager idManager = new IdManager("order");

    private Integer id;
    private Date dateOrder;
    private Date dateDeliver;
    private Date dateForward;
    private Situation situation;
    private Client owner;
    private double totalPrice;
    private double totalPriceICMS;
	private List<ItemOrder> itemOrders;

    public Order(Date dateOrder, Date dateDeliver, Situation situation, Client owner, double totalPrice, double totalPriceICMS) {
        int lastId = idManager.loadLastId();
        this.id = lastId++;
        idManager.saveLastId(lastId);
        this.dateOrder = dateOrder;
        this.dateDeliver = dateDeliver;
        this.situation = situation;
        this.owner = owner;
        this.totalPrice = totalPrice;
        this.totalPriceICMS = totalPriceICMS;
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

    public List<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public void setItemOrders(List<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }
}
