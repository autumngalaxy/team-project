package interface_adapter.user_login;

import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomepageState;
import interface_adapter.homepage.HomepageViewModel;
import use_case.user_login.UserLoginOutputBoundary;
import use_case.user_login.UserLoginOutputData;

public class UserLoginPresenter implements UserLoginOutputBoundary {

    private final UserLoginViewModel userLoginViewModel;
    private final HomepageViewModel homepageViewModel;
    private final ViewManagerModel viewManagerModel;

    public UserLoginPresenter(ViewManagerModel viewManagerModel,
                              HomepageViewModel homepageViewModel,
                              UserLoginViewModel userLoginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
        this.userLoginViewModel = userLoginViewModel;
    }

    @Override
    public void prepareSuccessView(UserLoginOutputData response) {
        String userType = response.getUserType();
        
        // On success, update the homepageViewModel state 	
        final HomepageState homepageState = homepageViewModel.getState();
        homepageState.setUsername(response.getUsername());
        this.homepageViewModel.firePropertyChange();

        // and clear everything from the LoginViewModel's state
        userLoginViewModel.setState(new UserLoginState());

        // ★ Redirect to different menus based on user type ★ 根据用户类型跳转不同菜单
        switch(userType) {
            case "user":
                viewManagerModel.setState("homepage");
                break;

            case "staff":
                viewManagerModel.setState("staffMenu");
                break;

            case "admin":
                viewManagerModel.setState("adminMenu");
                break;
        }
        
        // switch to the logged in view
//        this.viewManagerModel.setState(homepageViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String error) {
        final UserLoginState loginState = userLoginViewModel.getState();
        loginState.setLoginError(error);
        userLoginViewModel.firePropertyChange();
    }

	@Override
	public void prepareGoBackView(String viewName) {
        this.viewManagerModel.setState(viewName);
        this.viewManagerModel.setWindowTitle("Pet Adoption System");
        this.viewManagerModel.firePropertyChange();		
	}

    //for admin the successView is applicationManagement
}
