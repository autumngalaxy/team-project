package use_case.fill_application;

import entity.application.AdoptionApplication;

public interface FillApplicationDataAccessInterface {
    /**
     * Checks if the given Application ID exists.
     * @param id the ID to look for
     * @return true if an application with the given ID exists; false otherwise
     */
    boolean existsByID(String id);

    /**
     * Saves the application.
     * @param application the application to save.
     */
    void save(AdoptionApplication application);
}
