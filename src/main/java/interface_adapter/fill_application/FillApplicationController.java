package interface_adapter.fill_application;


import entity.application.AdoptionApplication;
import use_case.fill_application.FillApplicationInputBoundary;
import use_case.fill_application.FillApplicationInputData;
import entity.Pet;
import entity.application.SurveyInfo;

/**
 * Controller for the Fill Application use case.
 */
public class FillApplicationController {

    private final FillApplicationInputBoundary userFillApplicationUseCaseInteractor;

    public FillApplicationController (FillApplicationInputBoundary userFillApplicationUseCaseInteractor){
        this.userFillApplicationUseCaseInteractor = userFillApplicationUseCaseInteractor;
    }

    /**
     * Executes the Fill Application Use Case.
     * @param correspondingPet the correspoding pet.
     * @param adopterName the name of the adopter.
     * @param idType the type of adopter ID.
     * @param adopterAddress the adopter's address.
     * @param adopterPhone the phone number of the adopter.
     * @param adopterEmail the adopter's email.
     * @param surveyInfo the survey info the adopter filled out.
     */
    public void execute(Pet correspondingPet, String adopterName, AdoptionApplication.IDType idType,
                        String[] adopterAddress, String adopterPhone, String adopterEmail, SurveyInfo surveyInfo){
        final FillApplicationInputData applicationInputData = new FillApplicationInputData(
            correspondingPet, adopterName, idType, adopterAddress, adopterPhone, adopterEmail, surveyInfo
        );

        userFillApplicationUseCaseInteractor.execute(applicationInputData);
    }

    /**
     * Executes the "switch to DashboardView" Use Case.
     */
    public void switchToDashboardView(){
        userFillApplicationUseCaseInteractor.switchToDashboardView();
    }
}
