package entity;

public class User {

    private final String username;
    private final String password;
    private final String userType; // "user", "admin", "staff"

    public User(String username, String password, String userType) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (userType == null || userType.isEmpty()) {
            throw new IllegalArgumentException("User type cannot be empty.");
        }
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getUserType() { return userType; }
}
