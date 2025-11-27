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

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(loginView);
        
        if ("login".equals(evt.getPropertyName())) {
            frame.setTitle(loginView.getTitleText());

            // ★ 接收到 userType: user / staff / admin
            String userType = (String) evt.getNewValue();

            // ★ 设置 LoginView 的 userType
            loginView.setUserType(userType);
            loginView.updateTitle(); // 你在 LoginView 写一个方法来修改标题

            // ★ 切换到 LoginView
            viewManagerModel.setState(loginView.getViewName());
            viewManagerModel.firePropertyChange();
        }
        else if ("createAccount".equals(evt.getPropertyName())) {
            // create account
            frame.setTitle("Create User Account");
            // ★ switch 切换到 create account
            viewManagerModel.setState("CreateUserAccount");
            viewManagerModel.firePropertyChange();
        }
        else {
	        // ★★★★★ 恢复 loginChoose 的窗口标题
            frame.setTitle("loginChoose");

            // Switch back to the main menu 切换回主菜单
            viewManagerModel.setState("loginChoose");
            viewManagerModel.firePropertyChange();

        }
    	
    }
}
