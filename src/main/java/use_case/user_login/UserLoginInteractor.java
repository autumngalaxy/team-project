package use_case.user_login;

import entity.User;

public class UserLoginInteractor implements UserLoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final UserLoginOutputBoundary userLoginPresenter;

    public UserLoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           UserLoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.userLoginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(UserLoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();

        // final UserLoginOutputData loginOutputData = new UserLoginOutputData(user.getName());
        final UserLoginOutputData loginOutputData = new UserLoginOutputData("username");
        userLoginPresenter.prepareSuccessView(loginOutputData);
        /*
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userDataAccessObject.get(loginInputData.getUsername());

                userDataAccessObject.setCurrentUsername(username);

                final UserLoginOutputData loginOutputData = new UserLoginOutputData(user.getName());
                userLoginPresenter.prepareSuccessView(loginOutputData);
            }
        }
         */
    }
}
