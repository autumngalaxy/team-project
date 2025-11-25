package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplicationService {
    private final HashMap<Integer, Pet> pets = new HashMap<>();
    private final HashMap<Integer, Application> applications = new HashMap<>();

    public void addPet(Pet pet) {
        assert(!pets.containsKey(pet.getId()));
        pets.put(pet.getId(), pet);
    }

    public void addApplication(Application application) {
        assert(!applications.containsKey(application.getId()));
        assert(pets.containsKey(application.getPetId()));
        applications.put(application.getId(), application);
    }

    public List<Application> getPendingApplications() {
        List<Application> pending = new ArrayList<>();

        for (Application application : applications.values()) {
            if (application.getStatus() == Application.Status.PENDING) {
                pending.add(application);
            }
        }

        return pending;
    }

    private List<Application> getApplicationsForPet(int petId) {
        List<Application> forPet = new ArrayList<>();

        for (Application application : applications.values()) {
            if (application.getPetId() == petId) {
                forPet.add(application);
            }
        }

        return forPet;
    }


    void approveApplication(int applicationId) {
        assert(applications.containsKey(applicationId));
        Application app = applications.get(applicationId);
        app.setStatus(Application.Status.APPROVED);

        // mark pet as adopted
        int petId = app.getPetId();
        assert(pets.containsKey(petId));
        Pet pet = pets.get(petId);
        assert(!pet.isAdopted());
        pet.setAdopted();

        // reject all other applications for the same pet
        List<Application> otherAppsForSamePet = getApplicationsForPet(petId);
        for (Application otherApp : otherAppsForSamePet) {
            if (otherApp.getId() != applicationId) {
                assert(otherApp.getStatus() == Application.Status.PENDING);
                rejectApplication(otherApp.getId());
            }
        }
    }

    void rejectApplication(int applicationId) {
        assert(applications.containsKey(applicationId));
        Application app = applications.get(applicationId);
        app.setStatus(Application.Status.REJECTED);
    }
}
