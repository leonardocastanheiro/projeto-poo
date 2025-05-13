package br.ucs.projetosistemaprodutos.models.person;

public enum Role {
    ADMIN,
    SUPPLIER,
    CLIENT;

    @Override
    public String toString() {
        return this.name();
    }
}
