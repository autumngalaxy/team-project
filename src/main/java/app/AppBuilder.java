package app;

import data_access.FileUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomepageViewModel;
import interface_adapter.homepage.LoginChoosePresenter;
import interface_adapter.homepage.LoginChooseViewModel;
import interface_adapter.user_login.UserLoginController;
import interface_adapter.user_login.UserLoginPresenter;
import interface_adapter.user_login.UserLoginViewModel;
import interface_adapter.user_logout.UserLogoutController;
import interface_adapter.user_logout.UserLogoutPresenter;
import use_case.user_login.UserLoginInputBoundary;
import use_case.user_login.UserLoginInteractor;
import use_case.user_login.UserLoginOutputBoundary;
import use_case.user_logout.UserLogoutInputBoundary;
import use_case.user_logout.UserLogoutInteractor;
import use_case.user_logout.UserLogoutOutputBoundary;
import view.CreateUserAccountView;
import view.HomepageView;
import view.LoginChooseView;
//import view.LoggedInView;
import view.UserLoginView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final UserFactory userFactory = new UserFactory();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    /** add start 20251124 **/
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    /** add end 20251124 **/

    final FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("users.csv", userFactory);


    private UserLoginViewModel userLoginViewModel;
    private UserLoginView userLoginView;
    /** add start 20251124 **/
    private HomepageViewModel homepageViewModel;
    private HomepageView homepageView;
    private LoginChooseViewModel loginChooseViewModel;
    private LoginChooseView loginChooseView;
    private CreateUserAccountView createUserAccountView;
    /** add end 20251124 **/

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addLoginChoosePresenter() {

        LoginChoosePresenter presenter =
                new LoginChoosePresenter(viewManagerModel, userLoginView);

        loginChooseViewModel.addPropertyChangeListener(presenter);

        return this;
    }
    
    public AppBuilder addLoginChooseView() {
    	loginChooseViewModel = new LoginChooseViewModel();
    	loginChooseView = new LoginChooseView(loginChooseViewModel);
        cardPanel.add(loginChooseView, loginChooseView.getViewName());
        return this;
    }
    public AppBuilder addCreateUserAccountView() {
    	loginChooseViewModel = new LoginChooseViewModel();
    	createUserAccountView = new CreateUserAccountView(loginChooseViewModel);
        cardPanel.add(createUserAccountView, createUserAccountView.getViewName());
        return this;
    }    
    public AppBuilder addHomepageView() {
    	homepageViewModel = new HomepageViewModel();
        homepageView = new HomepageView(homepageViewModel);
        cardPanel.add(homepageView, homepageView.getViewName());
        return this;
    }
    
    // 用户登录（user、staff、admin）
    public AppBuilder addUserLoginView() {
        userLoginViewModel = new UserLoginViewModel();
        userLoginView = new UserLoginView(userLoginViewModel, "user"); // ★ loginType = user
        cardPanel.add(userLoginView, "login");
        return this;
    }
    
    public AppBuilder addUserLoginUseCase() {
        final UserLoginOutputBoundary userLoginOutputBoundary = new UserLoginPresenter(viewManagerModel,
                homepageViewModel, userLoginViewModel);
        final UserLoginInputBoundary userloginInteractor = new UserLoginInteractor(
                userDataAccessObject, userLoginOutputBoundary);

        final UserLoginController loginController = new UserLoginController(userloginInteractor);
        userLoginView.setLoginController(loginController);
        return this;
    }
    
//    public AppBuilder addChangePasswordUseCase() {
//        final ChangePasswordOutputBoundary changePasswordOutputBoundary = new ChangePasswordPresenter(viewManagerModel,
//        		homepageViewModel);
//
//        final ChangePasswordInputBoundary changePasswordInteractor =
//                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);
//
//        ChangePasswordController changePasswordController = new ChangePasswordController(changePasswordInteractor);
//        homepageView.setChangePasswordController(changePasswordController);
//        return this;
//    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addUserLogoutUseCase() {
        final UserLogoutOutputBoundary userLogoutOutputBoundary = new UserLogoutPresenter(viewManagerModel,
        		homepageViewModel, userLoginViewModel);

        final UserLogoutInputBoundary logoutInteractor =
                new UserLogoutInteractor(userDataAccessObject, userLogoutOutputBoundary);

        final UserLogoutController logoutController = new UserLogoutController(logoutInteractor);
        homepageView.setUserLogoutController(logoutController);
        return this;
    }

    public JFrame build() {
//        final JFrame application = new JFrame("User Login");
//        final JFrame application = new JFrame("loginChoose");
        final JFrame application = new JFrame("Pet Adoption System");
        
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        return application;
    }

}
