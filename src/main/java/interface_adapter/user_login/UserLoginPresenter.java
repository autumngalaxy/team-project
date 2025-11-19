package interface_adapter.user_login;

import interface_adapter.ViewManagerModel;
import interface_adapter.pet_pending.PetPendingState;
import interface_adapter.pet_pending.PetPendingViewModel;
import use_case.user_login.UserLoginOutputBoundary;
import use_case.user_login.UserLoginOutputData;
import view.PetPendingView;

public class UserLoginPresenter implements UserLoginOutputBoundary {

    private final UserLoginViewModel userLoginViewModel;
    private final PetPendingViewModel petPendingViewModel;
    private final ViewManagerModel viewManagerModel;

    public UserLoginPresenter(ViewManagerModel viewManagerModel,
                              PetPendingViewModel petPendingViewModel,
                              UserLoginViewModel userLoginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.petPendingViewModel = petPendingViewModel;
        this.userLoginViewModel = userLoginViewModel;
    }

    @Override
    public void prepareSuccessView(UserLoginOutputData response) {
        // On success, update the loggedInViewModel's state
        final PetPendingState petPendingState = petPendingViewModel.getState();
        petPendingState.setUsername(response.getUsername());
        this.petPendingViewModel.firePropertyChange();

        // and clear everything from the LoginViewModel's state
        userLoginViewModel.setState(new UserLoginState());

        // switch to the logged in view
        this.viewManagerModel.setState(petPendingViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String error) {
        final UserLoginState loginState = userLoginViewModel.getState();
        loginState.setLoginError(error);
        userLoginViewModel.firePropertyChange();
    }

    //for admin the successView is applicationManagement
}
