package projeto_sistema_produtos.controllers;
import projeto_sistema_produtos.models.address.Address;
import projeto_sistema_produtos.models.person.Role;
import projeto_sistema_produtos.models.person.User;
import projeto_sistema_produtos.repositories.user.UserRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

import static projeto_sistema_produtos.models.person.Role.ADMIN;

public class UserController {

    private UserRepository userRepository = new UserRepository();

    public UserController() throws Exception {
    }

    public void createUser() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose the role of the user that you want to create (1 - ADMIN, 2 - CLIENT): ");

        int option;

        try {
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException exception) {
            throw new Exception("You need to type a number.");
        }

        Role role;

        if (option == 1) {
           role = ADMIN;
        }
        else {
            if(option == 2) {
                role = Role.CLIENT;
            }

            else {
                throw new Exception("You need to type a valid number.");
            }
        }

        System.out.print("Login: ");
        String login;
        boolean isLoginExists;
        do {
            login = scanner.nextLine();
            isLoginExists = userRepository.isLoginExists(login);

            if(isLoginExists) {
                System.out.println("This login already exists, choose another one: ");
            }
        } while(isLoginExists);


        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        Address address = AddressController.ScanAddress(scanner);

        User user = new User(login,password,role,name,phone,email,address);

        userRepository.create(user);

    }

    public User getUserByLogin(String login) throws Exception {
        return userRepository.getByLogin(login);
    }

    public void save() throws Exception {
        userRepository.save();
    }

    public static void main(String[] args) throws Exception {
        UserController controller = new UserController();
    }
}
