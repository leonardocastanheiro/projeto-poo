package br.ucs.projetosistemaprodutos.models.itens;

import java.io.Serial;
import java.io.Serializable;

public enum Situation implements Serializable {
    NEW("Novo"),
    DELIVERED("Entregue"),
    FORWARD("Enviado"),
    CANCELED("Cancelado");

    @Serial
    private static final long serialVersionUID = 1;

    private final String description;

    Situation(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

}
