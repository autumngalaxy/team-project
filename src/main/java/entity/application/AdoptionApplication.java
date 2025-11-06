package entity.application;

import entity.Pet;

import java.time.LocalDate;

/**
* An entity representing an Application for Adoption.
 * Applications contain essential information (Pet, name of Adopter, ID, address, etc.), and survey information (who they live with, how they'll interact with the pet, etc.)
* */
public class AdoptionApplication {
    // Required info
    public enum IDType {PHOTO_CARD, DRIVERS_LICENSE, MAIL, OTHER}

    private Pet corespondingPet;
    private String adopterName;
    private IDType idType;
    private String[] adopterAddress;
    private String adopterPhone;
    private String adopterEmail;
    private LocalDate applicationDate;

    // Adopter Survey info
    private SurveyInfo surveyInfo;

    /* GETTERS (FOR OTHER USE) */

    public Pet getCorespondingPet() {
        return corespondingPet;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public IDType getIdType() {
        return idType;
    }

    public String[] getAdopterAddress() {
        return adopterAddress;
    }

    public String getAdopterPhone() {
        return adopterPhone;
    }

    public String getAdopterEmail() {
        return adopterEmail;
    }

    public SurveyInfo getSurveyInfo() {
        return surveyInfo;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    /* SETTERS (FOR BUILDER USE) */
    public void setCorespondingPet(Pet corespondingPet) {
        this.corespondingPet = corespondingPet;
    }

    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public void setIdType(IDType idType) {
        this.idType = idType;
    }

    public void setAdopterAddress(String[] adopterAddress) {
        this.adopterAddress = adopterAddress;
    }

    public void setAdopterPhone(String adopterPhone) {
        this.adopterPhone = adopterPhone;
    }

    public void setAdopterEmail(String adopterEmail) {
        this.adopterEmail = adopterEmail;
    }

    public void setSurveyInfo(SurveyInfo surveyInfo) {
        this.surveyInfo = surveyInfo;
    }

    public void setApplicationDate() {
        this.applicationDate = LocalDate.now();
    }

    /* ToString */

    private String addressToString() {
        StringBuilder result = new StringBuilder();
        result.append("{");

        for (String address : adopterAddress) {
            result.append(address);
            result.append(", ");
        }
        result.append("}");
        return result.toString();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Application: ")
                .append("[Pet: ")
                .append(corespondingPet)
                .append("] [Name: ")
                .append(adopterName)
                .append("] [idType: ")
                .append(idType)
                .append("] [Address: ")
                .append(addressToString())
                .append("] [Phone: ")
                .append(adopterPhone)
                .append("] [Email: ")
                .append(adopterEmail)
                .append("] [SurveyInfo: ")
                .append(surveyInfo)
                .append("] [Date: ")
                .append(applicationDate)
                .append("]")
                .toString();
    }
}