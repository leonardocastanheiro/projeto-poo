package br.ucs.projetosistemaprodutos.controllers;

import br.ucs.projetosistemaprodutos.collections.DynamicSupplierArray;
import br.ucs.projetosistemaprodutos.models.itens.Store;
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
	 
	 public Supplier getById(int id) throws Exception{
		 return supplierArray.getById(id);
	 }
	 
	 public Supplier getByEmail(String email) throws Exception {
	     return supplierArray.getByEmail(email);
	 }
	 
	 public Supplier getByName(String name) throws Exception{
		 return supplierArray.getByName(name);
	 }
}