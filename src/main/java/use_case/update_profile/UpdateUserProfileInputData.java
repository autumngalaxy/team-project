package use_case.update_profile;

public class UpdateUserProfileInputData {
    public final String name;
    public final String email;
    public final String address;

    public UpdateUserProfileInputData(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
