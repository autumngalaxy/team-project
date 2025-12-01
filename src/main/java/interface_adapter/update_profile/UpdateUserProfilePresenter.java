package interface_adapter.update_profile;

import interface_adapter.ViewManagerModel;
import use_case.update_profile.UpdateUserProfileOutputBoundary;

public class UpdateUserProfilePresenter implements UpdateUserProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    public UpdateUserProfilePresenter(ViewManagerModel viewManagerModel2) {
        this.viewManagerModel = viewManagerModel2;
    }

	@Override
    public void showEditProfile() {
        viewManagerModel.setState("editProfile");
        viewManagerModel.setWindowTitle("Edit Profile");
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void showProfileUpdateSuccess() {
        viewManagerModel.setState("userDashboard");
        viewManagerModel.setWindowTitle("Dashboard");
        viewManagerModel.firePropertyChange();
    }
}
