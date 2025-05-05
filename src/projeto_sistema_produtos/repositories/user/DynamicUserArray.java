package projeto_sistema_produtos.repositories.user;

import projeto_sistema_produtos.models.person.User;

import java.util.Arrays;
import java.util.List;

public class DynamicUserArray {
    private User[] users;
    private int count;

    public DynamicUserArray(int initialCapacity) {
        users = new User[initialCapacity];
        count = 0;
    }

    public void add(User user) {
        if(count == users.length) {
            int newCapacity = users.length * 2;
            users = Arrays.copyOf(users, newCapacity);
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

    public User getByLogin(String login) throws Exception {
        for(User userTemporary : users) {
            if(userTemporary != null) {
                if (userTemporary.getLogin().equals(login)) {
                    return userTemporary;
                }
            }
        }
        throw new Exception("Invalid login");
    }

    public boolean isLoginExists(String login) {
        for(User userTemporary : users) {
            if (userTemporary != null) {
                if (userTemporary.getLogin().equals(login)) {
                    return true;
                }
        }
    }

        return false;
    }

    public List<User> toList() {
        return Arrays.asList(Arrays.copyOf(users, count));
    }

}
