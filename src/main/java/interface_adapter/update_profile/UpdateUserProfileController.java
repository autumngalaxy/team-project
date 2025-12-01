package interface_adapter.update_profile;

import use_case.update_profile.UpdateUserProfileInputBoundary;
import use_case.update_profile.UpdateUserProfileInputData;

public class UpdateUserProfileController {

    private final UpdateUserProfileInputBoundary interactor;

    public UpdateUserProfileController(UpdateUserProfileInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void openEditPage() {
        interactor.openEditPage();
    }

    public void save(String name, String email, String address) {
        interactor.saveProfile(new UpdateUserProfileInputData(name, email, address));
    }
}
