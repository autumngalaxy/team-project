package use_case.update_profile;

public class UpdateUserProfileInputData {
    public final String name;
    public final String email;
    public final String address;
    public final int phoneNumber;

    public UpdateUserProfileInputData(String name, String email, String address, int phoneNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
