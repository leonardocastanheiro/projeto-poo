package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.collections.DynamicSupplierArray;
import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.copies.ClientCopy;
import br.ucs.projetosistemaprodutos.models.copies.SupplierCopy;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Client;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.Supplier;
import br.ucs.projetosistemaprodutos.models.person.User;

public class SupplierController {
	private DynamicSupplierArray supplierArray;
	
	public SupplierController(Store store){
		this.supplierArray = store.getSupplierArray();
	}
	
	public void create(Supplier supplier) throws Exception {
	    supplierArray.add(supplier);
	}
	 
	public void delete(Supplier Supplier) throws Exception {
		 supplierArray.delete(Supplier);
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
	
	
}