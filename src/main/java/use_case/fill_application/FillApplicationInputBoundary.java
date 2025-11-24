package use_case.fill_application;

/**
 * Input boundary for actions related to filling the application.
 */
public interface FillApplicationInputBoundary {
    /**
     * Executes the Fill Application use case.
     * @param fillApplicationInputData the input data.
     */
    void execute(FillApplicationInputData fillApplicationInputData);

    /**
     * Switches to the dashboard view use case
     * TODO
     */
    void switchToDashboardView();

}
