package br.ucs.projetosistemaprodutos.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ucs.projetosistemaprodutos.models.person.Role;
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
    
    public void showArray(Role role) throws Exception {
        boolean existsSupplier = false;

        for (Supplier supplierAux : suppliers) {
            if (supplierAux != null && supplierAux.getRole() == role) {
                System.out.println(supplierAux.toString());
                existsSupplier = true;
            }
        }

        if (!existsSupplier) {
            throw new Exception("Ainda não há fornecedores cadastrados no sistema.");
        }
    }
        
    public Supplier getByIndex(int index) throws Exception {
        if (index >= 0 && index < count) {
            return suppliers[index];
        }
        throw new Exception("Index inválido");
    }

    public Supplier getById(int id) throws Exception {
        for(Supplier supplier : suppliers) {
            if(supplier != null) {
                if (supplier.getId() == id) {
                    return supplier;
                }
            }
        }
        throw new Exception("ID de fornecedor inválido.");
    }
    
    public Supplier getByEmail(String email) throws Exception {
        for(Supplier supplier : suppliers) {
            if(supplier != null) {
                if (supplier.getEmail().equals(email)) {
                    return supplier;
                }
            }
        }
        throw new Exception("Email inválido.");
    }
    
    public Supplier getByName(String name) throws Exception {
        for(Supplier supplier : suppliers) {
            if(supplier != null) {
                if (supplier.getName().equals(name)) {
                    return supplier;
                }
            }
        }
        throw new Exception("Fornecedor não encontrado.");
    }

    public List<Supplier> getByText(String text) throws Exception {
        List<Supplier> suppliers = new ArrayList<>();

        text = text.toLowerCase();

        for(Supplier supplier : this.suppliers) {
            if(supplier != null && (supplier.getName().toLowerCase().contains(text) ||
            supplier.getEmail().toLowerCase().contains(text) ||
            supplier.getDescription().toLowerCase().contains(text))) {
                suppliers.add(supplier);
            }
        }

        if(suppliers.isEmpty()) {
            throw new Exception("Nenhum fornecedor existente com essa correspondência");
        }

        return suppliers;
    }

    public int size() {
        return count;
    }
}
