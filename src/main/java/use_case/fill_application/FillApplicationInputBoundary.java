package use_case.fill_application;

import jdk.jshell.spi.ExecutionControl;

public interface FillApplicationInputBoundary {

    /**
     * Executes the Fill Application use case.
     * @param applicationInputData the input data
     */
    void execute(FillApplicationInputData applicationInputData);

    /**
     * Executes the switch to [OTHER VIEW] use case.
     */
    void switchToOtherView() throws ExecutionControl.NotImplementedException;
}
