package entity.application;

import entity.Pet;

import java.time.LocalDate;

/**
 * An entity representing an Application for Adoption.
 * Applications contain essential information (Pet, name of Adopter, ID, address, etc.), and survey information through a SurveyInfo.
 */
public class AdoptionApplication {
    // Required info
    public enum IDType {PHOTO_CARD, DRIVERS_LICENSE, MAIL, OTHER}

    private Pet correspondingPet;
    private String adopterName;
    private IDType idType;
    private String[] adopterAddress;
    private String adopterPhone;
    private String adopterEmail;

    private LocalDate applicationDate;
    private String applicationID;

    // Adopter Survey info
    private SurveyInfo surveyInfo;

    /* GETTERS (FOR OTHER USE) */

    public Pet getCorespondingPet() {
        return correspondingPet;
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

    public String getApplicationID() { return applicationID; }

    /* SETTERS (FOR BUILDER USE) */
    public void setCorrespondingPet(Pet correspondingPet) {
        this.correspondingPet = correspondingPet;
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

        for (int i = 0; i < adopterAddress.length; i++) {
            result.append(adopterAddress[i]);
            if (i < adopterAddress.length - 1){ result.append(", "); }
        }
        result.append("}");
        return result.toString();
    }

    @Override
    public String toString() {
        return "Application: " + applicationID +
                " [Pet: " +
                correspondingPet +
                "] [Name: " +
                adopterName +
                "] [idType: " +
                idType +
                "] [Address: " +
                addressToString() +
                "] [Phone: " +
                adopterPhone +
                "] [Email: " +
                adopterEmail +
                "] [SurveyInfo: " +
                surveyInfo +
                "] [Date: " +
                applicationDate +
                "]";
    }
}