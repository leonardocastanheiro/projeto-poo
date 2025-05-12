package Controllers;

import java.util.Scanner;
import projeto_sistema_produtos.models.person.Person;
import projeto_sistema_produtos.models.person.User;
import projeto_sistema_produtos.models.person.Client;
import projeto_sistema_produtos.models.person.Role;

public class UserController extends GeneralController {

    public User createPerson() {
        Scanner sc = new Scanner(System.in);

        Person person = super.createPerson();

        Role role = null;
        String creditCard = null; // Inicializa com null

        while (role == null) {
            System.out.println("Digite o tipo de usuário (ADMIN ou CLIENT): ");
            String roleInput = sc.nextLine().toUpperCase();

            if (roleInput.equals("ADMIN")) {
                System.out.print("Digite o código de administrador: ");
                int adminCode = sc.nextInt();
                sc.nextLine(); 

                if (adminCode == Role.ADMIN.getCode()) {
                    role = Role.ADMIN;
                } else {
                    System.out.println("Código de administrador incorreto. Tente novamente ou escolha outro tipo de usuário.");
                }

            } else if (roleInput.equals("CLIENT")) {
                role = Role.CLIENT;
                System.out.print("Digite o número do cartão de crédito: ");
                creditCard = sc.nextLine();

            } else {
                System.out.println("Tipo de usuário inválido. Tente novamente.");
            }
        }

        System.out.println("Digite o login do Usuário: ");
        String login = sc.nextLine();

        System.out.println("Digite a senha:");
        String password = sc.nextLine();

        User user = new User(login, password, role, person.getName(), person.getPhone(), person.getEmail(), person.getAddress());

        if (role == Role.ADMIN) {
            return user;
        } else {
            return new Client(user, creditCard);
        }
    }
}
