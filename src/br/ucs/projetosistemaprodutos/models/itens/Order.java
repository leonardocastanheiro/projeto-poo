package br.ucs.projetosistemaprodutos.models.itens;

import br.ucs.projetosistemaprodutos.models.person.Client;

import java.util.Date;

public class Order {
    private static Integer lastId = 1;

    private Integer id;
    private Date dateOrder;
    private Date dataDeliver;
    private Situation situation;
    private Client owner;

    public Order(Date dateOrder, Date dataDeliver, Situation situation, Client owner) {
        this.id = lastId++;
        this.dateOrder = dateOrder;
        this.dataDeliver = dataDeliver;
        this.situation = situation;
        this.owner = owner;
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
}
