import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Validateuser {
    public List<User> users;

    public Validateuser() {
        users = new ArrayList<>();
        loadUsers();
    }

    private void loadUsers() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(this.getClass().getResource("users.json").toURI())));
            JSONArray userArray = new JSONArray(content);
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject userJson = userArray.getJSONObject(i);
                String username = userJson.getString("username");
                String password = userJson.getString("password");
                String role = userJson.getString("role");
                users.add(new User(username, password, role));
            }
        } catch (Exception e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }

    public User ValidateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}