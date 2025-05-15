package br.ucs.projetosistemaprodutos.collections;

import java.util.Arrays;
import java.util.Objects;

import br.ucs.projetosistemaprodutos.models.address.Address;
import br.ucs.projetosistemaprodutos.models.person.Role;
import br.ucs.projetosistemaprodutos.models.person.User;

public class DynamicUserArray {
    private User [] users;
    private Integer count;

    public DynamicUserArray(Integer initialCapacity) {
        users = new User[initialCapacity];
        count = 0;
        users[count++] = new User("admin","admin",Role.ADMIN,"admin","admin","admin", new Address("admin","admin","admin","admin","admin","admin","admin"));
    }

    public void add(User user) throws Exception {
        if(count == users.length) {
            int newCapacity = users.length * 2;
            users = Arrays.copyOf(users, newCapacity);
        }

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
        	}
        }

        users[count++] = user;
    }

    public void delete(User user) throws Exception {
        int indexToRemove = -1;
        for (int i = 0; i < count; i++) {
            if (users[i] == user) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            throw new Exception("Usuário não encontrado.");
        }

        for (int i = indexToRemove; i < count - 1; i++) {
            users[i] = users[i + 1];
        }

        users[count - 1] = null;
        count--;
    }

    public User getByIndex(int index) throws Exception {
        if (index >= 0 && index < count) {
            return users[index];
        }
        throw new Exception("Index inválido");
    }

    public User getById(int id) throws Exception {
        for(User user : users) {
            if(user != null) {
                if (user.getId() == id) {
                    return user;
                }
            }
        }
        throw new Exception("ID de usuário inválido.");
    }

    public User getByLogin(String login) throws Exception {
        for(User user : users) {
            if(user != null) {
                if (user.getLogin().equals(login)) {
                    return user;
                }
            }
        }
        throw new Exception("Login de usuário não encontrado.");
    }

    public User getByEmail(String email) throws Exception {
        for(User user : users) {
            if(user != null) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
        }
        throw new Exception("Email não encontrado.");
    }
}
