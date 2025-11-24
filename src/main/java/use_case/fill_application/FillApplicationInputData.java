package use_case.fill_application;

import entity.Pet;
import entity.application.AdoptionApplication;
import entity.application.SurveyInfo;

import java.time.LocalDate;

/**
 * The input data for the Fill Application use case.
 */
public class FillApplicationInputData {

    private final Pet corespondingPet;
    private final String adopterName;
    private final AdoptionApplication.IDType idType;
    private final String[] adopterAddress;
    private final String adopterPhone;
    private final String adopterEmail;

    // Adopter Survey info
    private final SurveyInfo surveyInfo;

    public FillApplicationInputData (Pet corespondingPet, String adopterName, AdoptionApplication.IDType idType,
                                     String[] adopterAddress, String adopterPhone, String adopterEmail, SurveyInfo surveyInfo) {
        this.corespondingPet = corespondingPet;
        this.adopterName = adopterName;
        this.idType = idType;
        this.adopterAddress = adopterAddress;
        this.adopterPhone = adopterPhone;
        this.adopterEmail = adopterEmail;
        this.surveyInfo = surveyInfo;
    }

    /* GETTERS (FOR OTHER USE) */

    public Pet getCorespondingPet() {
        return corespondingPet;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public AdoptionApplication.IDType getIdType() {
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
}
