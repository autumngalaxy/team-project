package use_case.fill_application;

import entity.application.*;
import jdk.jshell.spi.ExecutionControl;

public class FillApplicationInteractor implements FillApplicationInputBoundary{
    private final FillApplicationDataAccessInterface dataAccessObject;
    private final FillApplicationOutputBoundary userPresenter;
    private final ApplicationBuilder applicationBuilder;
    private final SurveyBuilder surveyBuilder;

    public FillApplicationInteractor(FillApplicationDataAccessInterface dataAccessObject, FillApplicationOutputBoundary applicationOutputBoundary, ApplicationBuilder applicationBuilder, SurveyBuilder surveyBuilder){
        this.dataAccessObject = dataAccessObject;
        this.userPresenter = applicationOutputBoundary;
        this.applicationBuilder = applicationBuilder;
        this.surveyBuilder = surveyBuilder;
    }

    @Override
    public void execute(FillApplicationInputData applicationInputData) {
        /* TODO If block of errors we might get */

        /* Otherwise... what do we want to do? */
        //else {}
        final SurveyInfo survey = surveyBuilder.addAdopterEnergy(SurveyInfo.EnergyOfHome.LOUD).build();
        final AdoptionApplication application = applicationBuilder.addName("[NAME HERE FROM INTERACTOR]").addSurveyInfo(survey).build();
        dataAccessObject.save(application);

        final FillApplicationOutputData applicationOutputData = new FillApplicationOutputData();
        // TODO userPresenter.prepareNextView(applicationOutputData);

    }

    @Override
    public void switchToOtherView() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Which view are you trying to switch to?");  // TODO
    }
}
