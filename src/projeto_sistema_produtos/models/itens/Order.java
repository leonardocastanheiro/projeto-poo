package projeto_sistema_produtos.models.itens;

import java.util.Date;

public class Order {
    private static Integer lastId = 1;

    private Integer id;
    private Date dateOrder;
    private Date dataDeliver;
    private Situation situation;

    public Order(Integer id, Date dateOrder, Date dataDeliver, Situation situation) {
        this.id = id;
        this.dateOrder = dateOrder;
        this.dataDeliver = dataDeliver;
        this.situation = situation;
    }

    public static Integer getLastId() {
        return lastId;
    }

    public static void setLastId(Integer lastId) {
        Order.lastId = lastId;
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
}
