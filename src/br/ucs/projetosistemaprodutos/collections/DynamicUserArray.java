package br.ucs.projetosistemaprodutos.collections;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

import br.ucs.projetosistemaprodutos.exceptions.UserNotFoundException;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.User;

public class DynamicUserArray implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    private final Map<String, User> users;


    public DynamicUserArray() {
        users =  new HashMap<>();
    }


    public void add(User user) throws Exception {

        User userAux = users.get(user.getLogin());

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

        users.put(user.getLogin(),user);
    }


    public void delete(User user) throws Exception {

        if (!users.remove(user.getLogin(),user)) {
            throw new Exception("Usuário não encontrado.");
        }

    }


    public void showArray(Role role) throws Exception {
        if (users.isEmpty()) {
            throw new Exception("Ainda não há usuários cadastrados no sistema.");
        }

        for (User userAux : this.toList()) {
            if (userAux != null && userAux.getRole() == role) {
                System.out.println(userAux.toString());
            }
        }
    }

    public User getById(int id) throws Exception {
        for(User user : this.toList()) {
                if (user.getId() == id) {
                    return user;
                }
        }
        throw new Exception("ID de usuário inválido.");
    }


    public User getByLogin(String login) throws UserNotFoundException{
        User user = users.get(login);
        
        if(user == null) {
        	throw new UserNotFoundException();
        }
        
        return user;
    }


    public User getByEmail(String email) throws Exception {
        for(User user : this.toList()) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
        }
        throw new Exception("Email não encontrado.");
    }
    
    public boolean isUserLogin(String login, User userLogin) {
        User user = users.get(login);

        if(user == null) {
            return false;
        }

        return user != userLogin;
    }

    public boolean isEmailExists(String email, User userEmail) {
        for(User user : this.toList()) {
                if(user.getEmail().equals(email) && !user.equals(userEmail) ) {
                    return true;
                }
        }

        return false;
    }

    public List<User> getByText(String text, Role role) throws Exception {
        List<User> users = new ArrayList<>();

        for(User user : this.toList()) {
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

        Collections.sort(users);
        return users;
    }

    public List<User> getAllUsers() {
        return this.toList();
    }

    public List<User> toList() {
        return new ArrayList<>(users.values());
    }
}
