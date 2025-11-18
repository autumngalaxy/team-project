package entity;

public class User {

    private String username;
    private String userpassword;
    /**
     * Creates a new user with the given non-empty name and non-empty password.
     * @param name the username
     * @param password the password
     * @throws IllegalArgumentException if the password or name are empty
     */
    public User(String name, String password) {
        if ("".equals(name)) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if ("".equals(password)) {
            throw new IllegalArgumentException("Password cannot be empty");
            //password length should between 6-20
        }
        this.username = name;
        this.userpassword = password;
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return userpassword;
    }

}
