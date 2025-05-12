package Controllers;

import java.util.Scanner;

import projeto_sistema_produtos.models.address.Address;
import projeto_sistema_produtos.models.person.Person;
import projeto_sistema_produtos.models.person.Role;

public abstract class GeneralController {
	
	public Person createPerson() {
		
		 Scanner sc = new Scanner(System.in);

	       Role role = null;
	       
	        System.out.println("Nome: ");
	        String name = sc.nextLine();

	        System.out.println("Telefone: ");
	        String phone = sc.nextLine();

	        System.out.println("e-mail: ");
	        String email = sc.nextLine();

	        /*----------------------------------------------------*/
	        System.out.println("Endereço: ");

	        System.out.println("Rua: ");
	        String street = sc.nextLine();

	        System.out.println("Numero: ");
	        String number = sc.nextLine();

	        System.out.println("Complemento: ");
	        String complement = sc.nextLine();

	        System.out.println("Bairro: ");
	        String neighborhood = sc.nextLine();

	        System.out.println("CEP: ");
	        String cep = sc.nextLine();

	        System.out.println("Cidade: ");
	        String city = sc.nextLine();

	        System.out.println("Estado: ");
	        String state = sc.nextLine();

	        Address address = new Address(street, number, complement, neighborhood, cep, city, state);
	        /*----------------------------------------------------*/
	        Person person = new Person(role, name, phone, email, address);
	        
	        
	        return person;
	}
}
