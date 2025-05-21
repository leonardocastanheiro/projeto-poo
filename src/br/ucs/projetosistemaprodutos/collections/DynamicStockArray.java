package br.ucs.projetosistemaprodutos.collections;

import java.util.Arrays;

import br.ucs.projetosistemaprodutos.models.itens.Stock;

public class DynamicStockArray {
    private Stock [] stocks;
    private Integer count;

    public DynamicStockArray(Integer initialCapacity) {
        stocks = new Stock[initialCapacity];
        count = 0;
    }

    public void add(Stock stock) {
        if(count == stocks.length) {
            int newCapacity = stocks.length * 2;
            stocks = Arrays.copyOf(stocks, newCapacity);
        }
        stocks[count++] = stock;
    }

    public void delete(Stock stock) throws Exception {
        int indexToRemove = -1;
        for (int i = 0; i < count; i++) {
            if (stocks[i] == stock) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            throw new Exception("Stock not found.");
        }

        for (int i = indexToRemove; i < count - 1; i++) {
            stocks[i] = stocks[i + 1];
        }

        stocks[count - 1] = null;
        count--;
    }

    public Stock getByIndex(int index) throws Exception {
        if (index >= 0 && index < count) {
            return stocks[index];
        }
        throw new Exception("Invalid index");
    }

    public Stock getById(int id) throws Exception {
        for(Stock stock : stocks) {
            if(stock != null) {
                if (stock.getId() == id) {
                    return stock;
                }
            }
        }
        throw new Exception("Invalid stock ID.");
    }
}
