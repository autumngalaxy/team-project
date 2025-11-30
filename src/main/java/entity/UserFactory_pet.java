package entity;

/**
 * A factory class responsible for creating User_k objects.
 * This factory provides simplified construction of User_k instances
 * when only a name and password are available, assigning default
 * values to the remaining fields.
 */
public class UserFactory_pet {

    /**
     * Creates a new User_k object with minimal required information.
     * Other fields such as address, ID type, phone number, email,
     * and username are initialized with default or empty values.
     *
     * @param name     the user's display name
     * @param password the user's account password
     * @return a newly created User_k instance with defaulted fields
     */
    public User_pet create(String name, String password) {
        return new User_pet(0, name, "",
                User_pet.idType.PHOTO_CARD, 0, "", "", password);
    }
}
