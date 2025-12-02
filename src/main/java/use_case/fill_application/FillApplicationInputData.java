package use_case.fill_application;

import entity.Pet;
import entity.application.SurveyInfo;

/**
 * The input data for the Fill Application use case.
 */
public class FillApplicationInputData {

    private final Pet corespondingPet;
    private final int corespondingUser;

    // Adopter Survey info
    private final SurveyInfo surveyInfo;

    public FillApplicationInputData(Pet correspondingPet, int correspondingUser, SurveyInfo surveyInfo) {
        this.corespondingPet = correspondingPet;
        this.corespondingUser = correspondingUser;
        this.surveyInfo = surveyInfo;
    }

    /* GETTERS (FOR OTHER USE) */

    public Pet getCorespondingPet() {
        return corespondingPet;
    }

    public int getCorespondingUser() {return corespondingUser; }


    public SurveyInfo getSurveyInfo() {
        return surveyInfo;
    }
}
