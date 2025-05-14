package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.collections.DynamicSupplierArray;
import br.ucs.projetosistemaprodutos.models.itens.Store;
import br.ucs.projetosistemaprodutos.models.person.Supplier;
import br.ucs.projetosistemaprodutos.models.person.User;

public class SupplierController {
	private DynamicSupplierArray supplierArray;
	
	SupplierController(Store store){
		this.supplierArray = store.getSupplierArray();
	}
	
	 protected void create(Supplier supplier) throws Exception {
	        supplierArray.add(supplier);
	    }
	 
	 protected void delete(Supplier Supplier) throws Exception {
		 supplierArray.delete(Supplier);
	 }
	 
	 public Supplier getById(int id) throws Exception{
		 return supplierArray.getById(id);
	 }
	 
	 public Supplier getByEmail(String email) throws Exception {
	        return supplierArray.getByEmail(email);
	    }
}