package interface_adapter.homepage;

import interface_adapter.ViewManagerModel;
import view.UserLoginView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LoginChoosePresenter implements PropertyChangeListener {

    private final ViewManagerModel viewManagerModel;
    private final UserLoginView loginView;

    public LoginChoosePresenter(ViewManagerModel viewManagerModel,
                                UserLoginView loginView) {
        this.viewManagerModel = viewManagerModel;
        this.loginView = loginView;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        final String property = evt.getPropertyName();

        // get window
        final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(loginView);

        if ("login".equals(property)) {

            // userType: user/staff/admin
            final String userType = (String) evt.getNewValue();

            // update userType in LoginView
            loginView.setUserType(userType);
            if (frame != null) {
                frame.setTitle(loginView.getTitleText());
            }

            viewManagerModel.setState(loginView.getViewName());
            viewManagerModel.firePropertyChange();
        }

        else if ("createAccount".equals(property)) {
            if (frame != null) {
                frame.setTitle("Create User Account");
            }
            viewManagerModel.setState("CreateUserAccount");
            viewManagerModel.firePropertyChange();
        }

        else {
            if (frame != null) {
                frame.setTitle("loginChoose");
            }
            viewManagerModel.setState("loginChoose");
            viewManagerModel.firePropertyChange();
        }
    }
}
