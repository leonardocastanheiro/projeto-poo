package br.ucs.projetosistemaprodutos.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;

public class DynamicSupplierArray {
	private final List<Supplier> suppliers;

    public DynamicSupplierArray() {
        suppliers = new ArrayList<>();
    }

    public void add(Supplier supplier) throws Exception {

        for (Supplier supplierAux : suppliers) {
            if(supplierAux != supplier && Objects.equals(supplierAux.getId(), supplier.getId())) {
                throw new Exception("Fornecedor já existe");
            }
        }

        suppliers.add(supplier);
    }

    public void delete(Supplier supplier) throws Exception {
        if(!suppliers.remove(supplier)) {
            throw new Exception("Fornecedor não encontrado");
        }
    }
    
    public void showArray(Role role) throws Exception {

        if (suppliers.isEmpty()) {
            throw new Exception("Ainda não há fornecedores cadastrados no sistema.");
        }

        for (Supplier supplierAux : suppliers) {
            if (supplierAux.getRole() == role) {
                System.out.println(supplierAux.toString());
            }
        }
    }
        
    public Supplier getByIndex(int index) throws Exception {
        if (index >= 0 && index < suppliers.size()) {
            return suppliers.get(index);
        }
        throw new Exception("Index inválido");
    }

    public Supplier getById(int id) throws Exception {
        for(Supplier supplier : suppliers) {
                if (supplier.getId() == id) {
                    return supplier;
                }
        }
        throw new Exception("ID de fornecedor inválido.");
    }
    
    public Supplier getByEmail(String email) throws Exception {
        for(Supplier supplier : suppliers) {
                if (supplier.getEmail().equals(email)) {
                    return supplier;
                }
        }
        throw new Exception("Email inválido.");
    }
    
    public Supplier getByName(String name) throws Exception {
        for(Supplier supplier : suppliers) {
                if (supplier.getName().equals(name)) {
                    return supplier;
                }
        }
        throw new Exception("Fornecedor não encontrado.");
    }

    public List<Supplier> getByText(String text) throws Exception {
        List<Supplier> suppliers = new ArrayList<>();

        text = text.toLowerCase();

        for(Supplier supplier : this.suppliers) {
            if((supplier.getName().toLowerCase().contains(text) ||
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
        return suppliers.size();
    }
}
