package br.ucs.projetosistemaprodutos.models.itens;

public enum Situation {
    NEW,
    DELIVERED,
    CANCELED;

    @Override
    public String toString() {
        return this.name();
    }

}
