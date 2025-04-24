package projeto_sistema_produtos.classes.person;

public enum Role {
    ADMIN,
    SUPPLIER,
    CLIENT;

    @Override
    public String toString() {
        return this.name();
    }
}
