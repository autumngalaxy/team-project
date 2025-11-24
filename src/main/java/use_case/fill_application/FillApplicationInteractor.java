package use_case.fill_application;

import entity.application.AdoptionApplication;
import entity.application.ApplicationBuilder;

public class FillApplicationInteractor implements FillApplicationInputBoundary{
    private final FillApplicationDataAccessInterface applicationDataObject;
    private final FillApplicationOutputBoundary userPresenter;

    private final ApplicationBuilder applicationBuilder;

    public FillApplicationInteractor (FillApplicationDataAccessInterface dataAccessInterface,
                                      FillApplicationOutputBoundary outputBoundary, ApplicationBuilder applicationBuilder){
        this.applicationDataObject = dataAccessInterface;
        this.userPresenter = outputBoundary;
        this.applicationBuilder = applicationBuilder;
    }

    @Override
    public void execute(FillApplicationInputData fillApplicationInputData) {

        if (fillApplicationInputData.getCorespondingPet() == null){
            userPresenter.prepareFailView("Corresponding pet cannot be null!");
        } else if ("".equals(fillApplicationInputData.getAdopterName())) {
            userPresenter.prepareFailView("Adopter name cannot be empty.");
        } else if (fillApplicationInputData.getIdType() == null) {
            userPresenter.prepareFailView("Must have valid ID.");
        } else if (fillApplicationInputData.getAdopterAddress() == null) {
            userPresenter.prepareFailView("Must enter a valid address.");
        } else if ("".equals(fillApplicationInputData.getAdopterPhone())) {
            userPresenter.prepareFailView("Adopter phone number cannot be empty.");
        } else if ("".equals(fillApplicationInputData.getAdopterEmail())) {
            userPresenter.prepareFailView("Adopter email cannot be empty.");
        } else if (fillApplicationInputData.getSurveyInfo() == null) {
            userPresenter.prepareFailView("Must fill out survey.");
        } else {
            final AdoptionApplication application = applicationBuilder.addPet(fillApplicationInputData.getCorespondingPet())
                    .addName(fillApplicationInputData.getAdopterName())
                    .addID(fillApplicationInputData.getIdType())
                    .addAddress(fillApplicationInputData.getAdopterAddress())
                    .addPhone(fillApplicationInputData.getAdopterPhone())
                    .addEmail(fillApplicationInputData.getAdopterEmail())
                    .addSurveyInfo(fillApplicationInputData.getSurveyInfo())
                    .addDate().build();
            applicationDataObject.save(application);

            final FillApplicationOutputData applicationOutputData = new FillApplicationOutputData(application.getCorespondingPet());
            userPresenter.prepareSuccessView(applicationOutputData);
        }

    }

    @Override
    public void switchToDashboardView() { userPresenter.switchToDashboardView(); }
}
