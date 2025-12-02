package use_case.update_profile;

import dataAccess.FileUserDataAccessObject;
import entity.User;
import service.Backend;

public class UpdateUserProfileInteractor implements UpdateUserProfileInputBoundary {

    private final FileUserDataAccessObject userdao;
    private final Backend backend;
    private final UpdateUserProfileOutputBoundary presenter;

    public UpdateUserProfileInteractor(FileUserDataAccessObject userDataAccessObject, Backend backend,
                                       UpdateUserProfileOutputBoundary presenter) {
        this.userdao = userDataAccessObject;
        this.backend = backend;
        this.presenter = presenter;
    }

    @Override
    public void openEditPage() {
        presenter.showEditProfile();
    }

    @Override
    public void phoneNumberInvalid(String message) {
        presenter.showProfileUpdateFailure(message);
    }

    @Override
    public void saveProfile(UpdateUserProfileInputData data) {
        final User u = userdao.getCurrentUser();
        if (u != null) {
            final User updated = new User(
                    u.getId(),
                    data.getName(),
                    data.getAddress(),
                    u.getIdType(),
                    data.getPhoneNumber(),
                    data.getEmail(),
                    u.getUsername(),
                    u.getPassword(),
                    u.getUserType()
            );
            userdao.save(updated);
            backend.setCurrentUser(updated);
        }
        // saveProfile
        presenter.showProfileUpdateSuccess();
    }

    @Override
    public void save(String name, String email, String address, int phoneNumber) {

        final User user = userdao.getCurrentUser();

        final User updated = new User(
                user.getId(),
                name,
                address,
                user.getIdType(),
                phoneNumber,
                email,
                user.getUsername(),
                user.getPassword(),
                user.getUserType()
        );

        userdao.updateUser(updated);
        backend.setCurrentUser(updated);

        presenter.showProfileUpdateSuccess();
    }

}
