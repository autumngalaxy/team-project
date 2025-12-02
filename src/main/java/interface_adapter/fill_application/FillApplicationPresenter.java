package interface_adapter.fill_application;

import use_case.fill_application.FillApplicationOutputBoundary;
import use_case.fill_application.FillApplicationOutputData;

/**
 * The presenter for the Fill Application use case.
 */
public class FillApplicationPresenter implements FillApplicationOutputBoundary{

    @Override
    public void prepareSuccessView(FillApplicationOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToDashboardView() {

    }
}
