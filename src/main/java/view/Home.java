package view;

import service.Backend;
import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    private Backend backend;

    public HomePage(Backend backend) {
        this.backend = backend;

        setTitle("Pet Adoption Service");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Pet Adoption!");
        title.setBounds(120, 40,200, 30);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        JButton browsePetsButton = new JButton("Browse Pets");
        browsePetsButton.setPreferredSize(new Dimension(150, 40));
        centerPanel.add(browsePetsButton);

        add(centerPanel, BorderLayout.CENTER);

        browsePetsButton.addActionListener(e -> {
            BrowsePetsPage browsePage = new BrowsePetsPage(backend);
            browsePage.setVisible(true);
        });
    }
}
