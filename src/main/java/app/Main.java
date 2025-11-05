package app;

import entity.application.*;
import entity.Pet;

public class Main {
    public static void main(String[] args) {
        System.out.println(new ApplicationBuilder()
                    .addName("TESTNAME")
                    .addAddress(new String[] {"AddressA", "AddressB", "AddressC"})
                    .addID(AdoptionApplication.IDType.OTHER)
                    .addPet(new Pet())
                    .addDate()
                    .addPhone("6478675309")
                    .addEmail("thisismyemail@gmail.com")
                    .addSurveyInfo(new SurveyInfo())
                    .build());
    }
}
