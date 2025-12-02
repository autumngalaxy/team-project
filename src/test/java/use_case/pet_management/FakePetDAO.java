package use_case.pet_management;

import entity.Pet;

import java.util.Collections;
import java.util.List;

/**
 * Simple in-memory fake for PetDataAccessInterface used in tests.
 * Lets tests control "exists" and inspect the last saved Pet.
 */
public class FakePetDAO implements PetDataAccessInterface {

    // what existsById(..) will return
    public boolean existsByIdReturn = false;

    // last pet that was saved (null if none)
    public Pet savedPet = null;

    @Override
    public boolean existsById(int id) {
        return existsByIdReturn;
    }

    @Override
    public Pet getById(int id) {
        if (savedPet != null && savedPet.getId() == id) {
            return savedPet;
        }
        return null;
    }

    @Override
    public List<Pet> getAll() {
        return savedPet == null ? Collections.emptyList() : List.of(savedPet);
    }

    @Override
    public void save(Pet pet) {
        this.savedPet = pet;
    }

    @Override
    public void deleteById(int id) {
        if (savedPet != null && savedPet.getId() == id) {
            savedPet = null;
        }
    }
}
