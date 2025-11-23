package view;

import javax.swing.*;
import java.awt.*;

public class CreateUserAccountPage {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Create User Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Fields
        JLabel nameLabel = new JLabel("Full Name:");
        JTextField nameField = new JTextField(20);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(20);

        JLabel idTypeLabel = new JLabel("ID Type:");
        String[] idTypes = {"Photo Card", "Driver's License", "Passport"};
        JComboBox<String> idTypeCombo = new JComboBox<>(idTypes);

        // Dynamic label for ID Number
        JLabel idNumberLabel = new JLabel("Document Number:");
        JTextField idNumberField = new JTextField(20);

        // Listen for dropdown changes
        idTypeCombo.addActionListener(e -> {
            String selected = (String) idTypeCombo.getSelectedItem();
            idNumberLabel.setText(selected + " Number:");
        });

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);

        JButton createButton = new JButton("Create Account");

        // Add components to panel
        gbc.gridx = 0; gbc.gridy = 0; panel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(usernameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(addressLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(addressField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(idTypeLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(idTypeCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 5; panel.add(idNumberLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 5; panel.add(idNumberField, gbc);

        gbc.gridx = 0; gbc.gridy = 6; panel.add(phoneLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 6; panel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 7; panel.add(emailLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 7; panel.add(emailField, gbc);

        gbc.gridx = 1; gbc.gridy = 8; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }
}
