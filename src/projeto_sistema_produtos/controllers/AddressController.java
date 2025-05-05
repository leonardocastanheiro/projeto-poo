package projeto_sistema_produtos.controllers;

import projeto_sistema_produtos.models.address.Address;

import java.util.Scanner;

public class AddressController {
    public static Address ScanAddress(Scanner scanner) {
        System.out.print("Street: ");
        String street = scanner.nextLine();

        System.out.print("Number: ");
        String number = scanner.nextLine();

        System.out.print("Complement: ");
        String complement = scanner.nextLine();

        System.out.print("Neighborhood: ");
        String neighborhood = scanner.nextLine();

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        System.out.print("State: ");
        String state = scanner.nextLine();

        return new Address(street,number,complement,neighborhood,cep,city,state);
    }
}
