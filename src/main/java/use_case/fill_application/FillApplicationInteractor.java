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
        } else if ("".equals(fillApplicationInputData.getAdopterName())) {
            userPresenter.prepareFailView("Adopter name cannot be empty.");
            System.out.println("Name empty");
        } else if (fillApplicationInputData.getIdType() == null) {
            userPresenter.prepareFailView("Must have valid ID.");
            System.out.println("ID Empty");
        } else if (fillApplicationInputData.getAdopterAddress() == null) {
            userPresenter.prepareFailView("Must enter a valid address.");
            System.out.println("Address empty");
        } else if ("".equals(fillApplicationInputData.getAdopterPhone())) {
            userPresenter.prepareFailView("Adopter phone number cannot be empty.");
            System.out.println("Phone empty");
        } else if ("".equals(fillApplicationInputData.getAdopterEmail())) {
            userPresenter.prepareFailView("Adopter email cannot be empty.");
            System.out.println("Email empty");
        } else if (fillApplicationInputData.getSurveyInfo() == null) {
            userPresenter.prepareFailView("Must fill out survey.");
            System.out.println("Survey empty");
        } else {
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
                    1, applicationDataObject.newApplicationId(), fillApplicationInputData.getSurveyInfo());

             applicationDataObject.addApplication(application);
            // TODO MUST UNCOMMENT AND FIGURE OUT WHAT THE HECK GOES ON THERE
            System.out.println("WHAT GOES ON HERE IN FILLAPPLICATIONINTERACTOR?!");

            final FillApplicationOutputData applicationOutputData = new FillApplicationOutputData(applicationDataObject.getPetById(application.getPetId()));
            userPresenter.prepareSuccessView(applicationOutputData);
        }

    }

    @Override
    public void switchToDashboardView() { userPresenter.switchToDashboardView(); }
}
