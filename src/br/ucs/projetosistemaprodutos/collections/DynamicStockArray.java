package br.ucs.projetosistemaprodutos.collections;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.ucs.projetosistemaprodutos.models.itens.Stock;

public class DynamicStockArray implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    private final List<Stock> stocks;

    public DynamicStockArray() {
        stocks = new ArrayList<>();
    }

    public void add(Stock stock) throws Exception {
        for(Stock stockAux : stocks) {
            if(stock != stockAux && Objects.equals(stock.getId(), stockAux.getId())) {
                throw new Exception("Esse estoque já existe");
            }
        }

        stocks.add(stock);
    }

    public void delete(Stock stock) throws Exception {
        if(!stocks.remove(stock)) {
            throw new Exception("Estoque não encontrado");
        }
    }

    public Stock getByIndex(int index) throws Exception {
        if (index >= 0 && index < stocks.size()) {
            return stocks.get(index);
        }
        throw new Exception("Index inválido");
    }

    public Stock getById(int id) throws Exception {
        for(Stock stock : stocks) {
            if (stock.getId() == id) {
                return stock;
            }
        }
        throw new Exception("ID de Estoque não encontrado.");
    }

    public int size() {
        return stocks.size();
    }
}
