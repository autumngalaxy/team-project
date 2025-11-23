package view;

import javax.swing.*;
import java.awt.*;

public class UserLoginPage {

    public static void main(String[] args) {

        JFrame frame = new JFrame("User Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Labels + Fields
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(18);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(18);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(18);

        JButton loginButton = new JButton("Login");

        // Add items to panel
        gbc.gridx = 0; gbc.gridy = 0; panel.add(emailLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(usernameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 3; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }
}
