package use_case.fill_application;

import entity.Application;
import entity.application.AdoptionApplication;
import entity.application.ApplicationBuilder;
import service.Backend;

public class FillApplicationInteractor implements FillApplicationInputBoundary{
    private final Backend applicationDataObject;
    private final FillApplicationOutputBoundary userPresenter;

    public FillApplicationInteractor (Backend dataAccessInterface,
                                      FillApplicationOutputBoundary outputBoundary){
        this.applicationDataObject = dataAccessInterface;
        this.userPresenter = outputBoundary;
    }

    @Override
    public void execute(FillApplicationInputData fillApplicationInputData) {

        if (fillApplicationInputData.getCorespondingPet() == null){
            userPresenter.prepareFailView("Corresponding pet cannot be null!");
            System.out.println("Pet null");
        } else if (fillApplicationInputData.getSurveyInfo() == null) {
            userPresenter.prepareFailView("Must fill out survey.");
            System.out.println("Survey empty");
        } else if (fillApplicationInputData.getCorespondingUser() == -1) {
            userPresenter.prepareFailView("Must have user");
            System.out.println("User null");
        }
        else {
            /*final AdoptionApplication application = new ApplicationBuilder()
                    .addPet(fillApplicationInputData.getCorespondingPet())
                    .addName(fillApplicationInputData.getAdopterName())
                    .addID(fillApplicationInputData.getIdType())
                    .addAddress(fillApplicationInputData.getAdopterAddress())
                    .addPhone(fillApplicationInputData.getAdopterPhone())
                    .addEmail(fillApplicationInputData.getAdopterEmail())
                    .addSurveyInfo(fillApplicationInputData.getSurveyInfo())
                    .addDate().build();*/
                final Application application = new Application(fillApplicationInputData.getCorespondingPet().getId(),
                        fillApplicationInputData.getCorespondingUser(), applicationDataObject.newApplicationId(), fillApplicationInputData.getSurveyInfo());

                applicationDataObject.addApplication(application);
                System.out.println("created application successfully for user " + fillApplicationInputData.getCorespondingUser());
                final FillApplicationOutputData applicationOutputData = new FillApplicationOutputData(applicationDataObject.getPetById(application.getPetId()));
                userPresenter.prepareSuccessView(applicationOutputData);
            }
        }



    @Override
    public void switchToDashboardView() { userPresenter.switchToDashboardView(); }
}
