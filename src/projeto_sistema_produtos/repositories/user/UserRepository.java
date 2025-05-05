package projeto_sistema_produtos.repositories.user;

import projeto_sistema_produtos.models.person.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String FILE_PATH = "users.db";
    private DynamicUserArray users;

    public UserRepository() throws Exception {

        List<User> existing = loadAllUsers();

        this.users = new DynamicUserArray(Math.max(existing.size(), 10));

        existing.forEach(u -> {
            try {
                users.add(u);
            } catch (Exception ignored) { }
        });
    }

    public void create(User user) throws Exception {
        users.add(user);
        saveAllUsers();
    }

    public void delete(User user) throws Exception {
        users.delete(user);
        saveAllUsers();
    }

    public void save() throws Exception {
        saveAllUsers();
    }

    public User getByLogin(String login) throws Exception {
        return users.getByLogin(login);
    }

    public boolean isLoginExists(String login) {
        return users.isLoginExists(login);
    }

    @SuppressWarnings("unchecked")
    private List<User> loadAllUsers() throws Exception {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Error while trying to read users.", e);
        }
    }

    private void saveAllUsers() throws Exception {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(users.toList());
        } catch (IOException e) {
            throw new Exception("Error while trying to save users.", e);
        }
    }

}
