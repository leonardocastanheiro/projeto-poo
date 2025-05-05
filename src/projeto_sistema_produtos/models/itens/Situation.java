package projeto_sistema_produtos.models.itens;

public enum Situation {
    NEW,
    DELIVERED,
    CANCELED;

    @Override
    public String toString() {
        return this.name();
    }

}
