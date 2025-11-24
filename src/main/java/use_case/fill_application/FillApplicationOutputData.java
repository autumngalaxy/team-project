package use_case.fill_application;
import entity.Pet;

/**
 * Output data for the Fill Application use case.
 */
public class FillApplicationOutputData {

    private final Pet correspondingPet;

    public FillApplicationOutputData(Pet correspondingPet){
        this.correspondingPet = correspondingPet;
    }

    public Pet getPet() { return correspondingPet; }
}
