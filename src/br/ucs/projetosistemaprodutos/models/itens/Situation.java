package br.ucs.projetosistemaprodutos.models.itens;

public enum Situation {
    NEW("Novo"),
    DELIVERED("Entregue"),
    FORWARD("Enviado"),
    CANCELED("Cancelado");

    private final String description;

    Situation(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

}
