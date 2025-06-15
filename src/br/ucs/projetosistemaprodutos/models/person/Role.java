package br.ucs.projetosistemaprodutos.models.person;

import java.io.Serial;
import java.io.Serializable;

public enum Role implements Serializable {
    ADMIN,
    SUPPLIER,
    CLIENT;

    @Serial
    private static final long serialVersionUID = 1;

    @Override
    public String toString() {
        return this.name();
    }
}
