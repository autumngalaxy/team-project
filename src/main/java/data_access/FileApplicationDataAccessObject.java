package data_access;

import entity.application.AdoptionApplication;
import use_case.fill_application.FillApplicationDataAccessInterface;

import java.io.*;

public class FileApplicationDataAccessObject implements FillApplicationDataAccessInterface {
    private static final String HEADER = "HEADER HERE"; // TODO

    private final File csvFile;

    public FileApplicationDataAccessObject(String csvPath){
        csvFile = new File(csvPath);

        if (csvFile.length() == 0){ /* Nothing in the file yet */
            save();
        }
        else { /* Something is in the file--load it */

            // TODO do this
        }
    }

    @Override
    public void save(AdoptionApplication application) {

    }
    public void save(){

    }

    @Override
    public boolean existsByID(String id) {
        return false;
    }

}
