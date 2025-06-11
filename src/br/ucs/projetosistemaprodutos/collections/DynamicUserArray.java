package br.ucs.projetosistemaprodutos.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.User;

public class DynamicUserArray {
    private final List<User> users;


    public DynamicUserArray() {
        users =  new ArrayList<>();
    }


    public void add(User user) throws Exception {

        for (User userAux : users) {
        	if(userAux != null) {
                if(userAux == user) {
                    throw new Exception("Usuário já existe.");
                }
                else if(Objects.equals(userAux.getLogin(), user.getLogin())) {
                    throw new Exception("Esse login já existe.");
                }
                else if(Objects.equals(userAux.getEmail(), user.getEmail())) {
                    throw new Exception("Esse endereço de e-mail já está sendo utilizado.");
                }
                else if(Objects.equals(userAux.getId(),user.getId())) {
                    throw new Exception("ID já existente");
                }
        	}
        }

        users.add(user);
    }


    public void delete(User user) throws Exception {

        if (!users.remove(user)) {
            throw new Exception("Usuário não encontrado.");
        }

    }


    public void showArray(Role role) throws Exception {
        if (users.isEmpty()) {
            throw new Exception("Ainda não há usuários cadastrados no sistema.");
        }

        for (User userAux : users) {
            if (userAux != null && userAux.getRole() == role) {
                System.out.println(userAux.toString());
            }
        }
    }
    


    public User getByIndex(int index) throws Exception {
        if (index >= 0 && index < users.size()) {
            return users.get(index);
        }
        throw new Exception("Index inválido");
    }


    public User getById(int id) throws Exception {
        for(User user : users) {
                if (user.getId() == id) {
                    return user;
                }
        }
        throw new Exception("ID de usuário inválido.");
    }


    public User getByLogin(String login) throws Exception {
        for(User user : users) {
                if (user.getLogin().equals(login)) {
                    return user;
                }
        }
        throw new Exception("Login de usuário não encontrado.");
    }


    public User getByEmail(String email) throws Exception {
        for(User user : users) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
        }
        throw new Exception("Email não encontrado.");
    }


    public boolean isLoginExists(String login, User userLogin) {
        for(User user : users) {
                if(user.getLogin().equals(login) && !user.equals(userLogin)) {
                    return true;
                }
        }

        return false;
    }

    public boolean isEmailExists(String email, User userEmail) {
        for(User user : users) {
                if(user.getEmail().equals(email) && !user.equals(userEmail) ) {
                    return true;
                }
        }

        return false;
    }

    public List<User> getByText(String text, Role role) throws Exception {
        List<User> users = new ArrayList<>();

        for(User user : this.users) {
            if(user.getRole() == role &&
                    (user.getName().toLowerCase().contains(text.toLowerCase()) ||
                    user.getLogin().toLowerCase().contains(text.toLowerCase()) ||
                    user.getEmail().toLowerCase().contains(text.toLowerCase()))) {

                users.add(user);
            }
        }

        if(users.isEmpty()) {
            throw new Exception("Nenhum usuário existente com essa correspondência");
        }

        return users;
    }

    public List<User> getAllUsers() {
        return this.users;
    }
}
