package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.repositories.DynamicProductArray;
import br.ucs.projetosistemaprodutos.repositories.DynamicSupplierArray;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.copies.SupplierCopy;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierController {
	private DynamicSupplierArray supplierArray;
	private DynamicProductArray productArray;
	
	public SupplierController(Store store){
		this.supplierArray = store.getSupplierArray();
		this.productArray = store.getProductArray();
	}
	
	public void create(Supplier supplier) throws Exception {
	    supplierArray.add(supplier);
	}
	 
	public void delete(Supplier supplier) throws Exception {
		
		 if(supplier.getProducts().size() > 0) {
			 throw new Exception("Existem itens vinculados a esse fornecedor, tornando impossível excluí-lo.");
		 }
		
		 supplierArray.delete(supplier);
	}
	
	public void edit(Supplier supplier, SupplierCopy copy) throws Exception {
		
        supplier.setName(copy.getName());
        supplier.setPhone(copy.getPhone());
        supplier.setEmail(copy.getEmail());
        supplier.setDescription(copy.getDescription());
        supplier.setProducts(copy.getProducts());

        Address address = supplier.getAddress();
        Address copyAddress = copy.getAddress();

        address.setStreet(copyAddress.getStreet());
        address.setNumber(copyAddress.getNumber());
        address.setComplement(copyAddress.getComplement());
        address.setNeighborhood(copyAddress.getNeighborhood());
        address.setCep(copyAddress.getCep());
        address.setCity(copyAddress.getCity());
        address.setState(copyAddress.getState());
    }
	
	public Supplier getById(int id) throws Exception{
		return supplierArray.getById(id);
	}
 
	public Supplier getByEmail(String email) throws Exception {
		return supplierArray.getByEmail(email);
	}
 
	public Supplier getByName(String name) throws Exception{
		return supplierArray.getByName(name);
	}
	 
	public void showArray(Role role) throws Exception{
		supplierArray.showArray(role);
	}
	
	public List<Supplier> getByText(String text) throws Exception {
		int id = -1;
		List<Supplier> suppliers = new ArrayList<>();

		try {
			id = Integer.parseInt(text);
			text = null;
		} catch (Exception ignored) {}

		if(text == null) {
			suppliers.add(this.getById(id));

			return suppliers;
		}

		return supplierArray.getByText(text);
	}
}