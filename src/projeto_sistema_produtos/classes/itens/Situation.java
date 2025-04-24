package projeto_sistema_produtos.classes.itens;

public enum Situation {
    NEW,
    DELIVERED,
    CANCELED;

    @Override
    public String toString() {
        return this.name();
    }

}
