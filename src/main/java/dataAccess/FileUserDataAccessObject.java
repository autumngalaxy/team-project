package dataAccess;

import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.user_login.UserLoginUserDataAccessInterface;
import use_case.user_logout.UserLogoutUserDataAccessInterface;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON-based implementation of the User Data Access Object.
 * This replaces CSV storage and provides persistent JSON storage.
 */
public class FileUserDataAccessObject implements UserLoginUserDataAccessInterface,
        UserLogoutUserDataAccessInterface, SignupUserDataAccessInterface {

    private final String jsonPath;
    private final UserFactory userFactory;
    private final Map<String, User> accounts = new HashMap<>();

    private String currentUsername;

    /**
     * Constructs a JSON DAO that loads and saves users from/to a JSON file.
     *
     * @param jsonPath    path to the users.json file
     * @param userFactory factory for creating User objects
     */
    public FileUserDataAccessObject(String jsonPath, UserFactory userFactory) {
        this.jsonPath = jsonPath;
        this.userFactory = userFactory;

        File f = new File(jsonPath);

        if (!f.exists()) {
            save(); // Create an empty JSON file
        } else {
            load();
        }
    }

    /**
     * Loads all users from the JSON file into memory.
     */
    private void load() {
        try {
            String data = new String(Files.readAllBytes(Paths.get(jsonPath)));
            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                String username = o.getString("username");
                String password = o.getString("password");
                String userType = o.getString("userType");

                User user = userFactory.create(username, password, userType);
                accounts.put(username, user);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON: " + jsonPath, e);
        }
    }

    /**
     * Saves all user data into the JSON file (pretty formatted).
     */
    private void save() {
        try (FileWriter writer = new FileWriter(jsonPath)) {
            JSONArray arr = new JSONArray();

            for (User user : accounts.values()) {
                JSONObject obj = new JSONObject();
                obj.put("username", user.getUsername());
                obj.put("password", user.getPassword());
                obj.put("userType", user.getUserType());
                arr.put(obj);
            }

            writer.write(arr.toString(4)); // 4-space pretty print
        } catch (Exception e) {
            throw new RuntimeException("Failed to write JSON: " + jsonPath, e);
        }
    }

    // ---------- Interface Methods ----------

    @Override
    public boolean existsByName(String username) {
        return accounts.containsKey(username);
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        save(); // Persist immediately
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }
}
