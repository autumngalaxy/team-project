package view;

import javax.swing.*;
import java.awt.*;

import service.Backend;

public class AdminLoginPage extends JPanel {
    private Backend backend;

    private JFrame frame;
    private JPanel panel;
    private GridBagConstraints gbc;

    private JLabel emailLabel;
    private JTextField emailField;

    private JLabel usernameLabel;
    private JTextField usernameField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton loginButton;

    private void setFrame() {
        frame = new JFrame("Admin Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
    }

    private void setPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
    }

    private void setLoginFields() {
        emailLabel = new JLabel("Admin Email:");
        emailField = new JTextField(18);

        usernameLabel = new JLabel("Admin Username:");
        usernameField = new JTextField(18);

        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(18);
    }

    private void setLoginButton() {
        loginButton = new JButton("Login as Admin");
    }

    private void addToPanel() {
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);
    }

    private void addToFrame() {
        frame.add(panel);
        frame.setVisible(true);
    }

    public AdminLoginPage(Backend backend) {
        this.backend = backend;
        setFrame();
        setPanel();
        setLoginFields();
        setLoginButton();
        addToPanel();
        addToFrame();

    }
}
