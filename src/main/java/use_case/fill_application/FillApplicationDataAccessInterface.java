package use_case.fill_application;

import entity.application.AdoptionApplication;

public interface FillApplicationDataAccessInterface {
    /**
     * Saves the application.
     * @param application the application to save.
     */
    void save(AdoptionApplication application);
}
