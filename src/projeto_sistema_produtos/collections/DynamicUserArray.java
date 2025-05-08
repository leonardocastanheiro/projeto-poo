package projeto_sistema_produtos.collections;

import java.util.Arrays;

import projeto_sistema_produtos.models.person.User;

public class DynamicUserArray {
    private User [] users;
    private Integer count;

    public DynamicUserArray(Integer initialCapacity) {
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
}
