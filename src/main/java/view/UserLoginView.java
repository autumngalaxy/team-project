package view;

import entity.User;
import interface_adapter.user_login.UserLoginViewModel;

import javax.swing.*;
import java.awt.*;

public class UserLoginView extends JPanel {

    private final String viewName = "log in";
    private final UserLoginViewModel UserloginViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final JButton logIn;
    private final JButton cancel;
    // private LoginController loginController = null;

    public UserLoginView(UserLoginViewModel userloginViewModel) {
        UserloginViewModel = userloginViewModel;

        final JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JPanel buttons = new JPanel();
        logIn = new JButton("log in");
        buttons.add(logIn);
        cancel = new JButton("cancel");
        buttons.add(cancel);
    }




}
