package br.ucs.projetosistemaprodutos.collections;

import java.util.Arrays;
import java.util.Objects;

import br.ucs.projetosistemaprodutos.models.person.User;

public class DynamicUserArray {
    private User [] users;
    private Integer count;

    public DynamicUserArray(Integer initialCapacity) {
        users = new User[initialCapacity];
        count = 0;
    }

    public void add(User user) throws Exception {
        if(count == users.length) {
            int newCapacity = users.length * 2;
            users = Arrays.copyOf(users, newCapacity);
        }

        for (User userAux : users) {
            if(userAux == user) {
                throw new Exception("User already exists.");
            }
            else if(Objects.equals(userAux.getLogin(), user.getLogin())) {
                throw new Exception("Login already exists.");
            }
            else if(Objects.equals(userAux.getEmail(), user.getEmail())) {
                throw new Exception("E-mail already exists.");
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
            throw new Exception("User not found.");
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
        throw new Exception("Invalid index");
    }

    public User getById(int id) throws Exception {
        for(User user : users) {
            if(user != null) {
                if (user.getId() == id) {
                    return user;
                }
            }
        }
        throw new Exception("Invalid user ID.");
    }

    public User getByLogin(String login) throws Exception {
        for(User user : users) {
            if(user != null) {
                if (user.getLogin().equals(login)) {
                    return user;
                }
            }
        }
        throw new Exception("Invalid user login.");
    }

    public User getByEmail(String email) throws Exception {
        for(User user : users) {
            if(user != null) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
        }
        throw new Exception("Invalid user email.");
    }
}
