package projeto_sistema_produtos.models.person;

public enum Role {
    ADMIN,
    SUPPLIER,
    CLIENT;

    @Override
    public String toString() {
        return this.name();
    }
}
