package br.ucs.projetosistemaprodutos.collections;

import java.util.Arrays;

import br.ucs.projetosistemaprodutos.models.person.Supplier;

public class DynamicSupplierArray {
	private Supplier [] suppliers;
    private Integer count;

    public DynamicSupplierArray(Integer initialCapacity) {
        suppliers = new Supplier[initialCapacity];
        count = 0;
    }

    public void add(Supplier supplier) {
        if(count == suppliers.length) {
            int newCapacity = suppliers.length * 2;
            suppliers = Arrays.copyOf(suppliers, newCapacity);
        }
        suppliers[count++] = supplier;
    }

    public void delete(Supplier supplier) throws Exception {
        int indexToRemove = -1;
        for (int i = 0; i < count; i++) {
            if (suppliers[i] == supplier) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            throw new Exception("Supplier not found.");
        }

        for (int i = indexToRemove; i < count - 1; i++) {
            suppliers[i] = suppliers[i + 1];
        }

        suppliers[count - 1] = null;
        count--;
    }

    public Supplier getByIndex(int index) throws Exception {
        if (index >= 0 && index < count) {
            return suppliers[index];
        }
        throw new Exception("Invalid index");
    }

    public Supplier getById(int id) throws Exception {
        for(Supplier supplier : suppliers) {
            if(supplier != null) {
                if (supplier.getId() == id) {
                    return supplier;
                }
            }
        }
        throw new Exception("Invalid supplier ID.");
    }
}
