package entity;

public class AdopterInfo {
    public enum IDType {PHOTO_CARD, DRIVERS_LICENSE, MAIL, OTHER}

    private final String name;
    private final String address;
    private final IDType idType;
    private final String phoneNumber;
    private final String email;

    AdopterInfo(String name, String address, IDType idType, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.idType = idType;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public IDType getIdType() {
        return idType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
