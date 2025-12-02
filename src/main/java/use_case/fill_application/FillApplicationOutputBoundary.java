package use_case.fill_application;

/**
 * The output boundary for the Fill Application use case.
 */
public interface FillApplicationOutputBoundary {
    /**
     * Prepares the success view for the Fill Application use case.
     * @param outputData the output data.
     */
    void prepareSuccessView(FillApplicationOutputData outputData);

    /**
     * Prepares the failure view for the Fill Application Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Dashboard View.
     * TODO
     */
    void switchToDashboardView();
}
