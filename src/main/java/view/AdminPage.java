package view;

import entity.Application;
import entity.Pet;

import entity.User;
import service.Backend;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class AdminPage extends UIComponent {

    private final JPanel applicationListPanel;

    public AdminPage(JFrame frame, Backend backend) {
        super(frame, backend);

        frame.setSize(1200, 800);   // wider default
        frame.setLocationRelativeTo(null); // center window
        frame.setResizable(true);

        setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome Admin");
        welcome.setFont(new Font("Arial", Font.BOLD, 22));
        welcome.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(welcome, BorderLayout.NORTH);

        applicationListPanel = new JPanel();
        applicationListPanel.setLayout(new BoxLayout(applicationListPanel, BoxLayout.Y_AXIS));
        applicationListPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(applicationListPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        scrollPane.getViewport().setPreferredSize(new Dimension(1000, 600));

        loadApplicationCards();
    }

    private void loadApplicationCards() {
        applicationListPanel.removeAll();

        List<Application> apps = backend.getPendingApplications();

        // sort applications
        apps.sort((a, b) -> {
            Pet petA = backend.getPetById(a.getPetId());
            Pet petB = backend.getPetById(b.getPetId());

            String nameA = petA != null ? petA.getName() : "";
            String nameB = petB != null ? petB.getName() : "";

            // 1. Sort alphabetically by pet name
            int nameCompare = nameA.compareToIgnoreCase(nameB);
            if (nameCompare != 0) return nameCompare;

            // If same pet name → sort by date of application
            return a.getDate().compareTo(b.getDate());
        });

        System.out.println("Loaded apps: " + apps.size()); // Debug

        for (Application app : apps) {
            JPanel card = createApplicationCard(app);
            applicationListPanel.add(card);
            applicationListPanel.add(Box.createVerticalStrut(10));
        }

        applicationListPanel.revalidate();
        applicationListPanel.repaint();
    }

    private JPanel createApplicationCard(Application app) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setBackground(new Color(245, 245, 245));
        card.setPreferredSize(new Dimension(700, 185));
        card.setMaximumSize(new Dimension(700, 185));

        Pet pet = backend.getPetById(app.getPetId());
        String petName = pet.getName();

        User user = backend.getUserById(app.getUserId());
        String userName = user.getName();

        // Left panel: picture
        JLabel picLabel = new JLabel();
        picLabel.setPreferredSize(new Dimension(200, 185));
        picLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        picLabel.setHorizontalAlignment(SwingConstants.CENTER);
        picLabel.setVerticalAlignment(SwingConstants.CENTER);

        // If Application has a picture, set it
        if (pet != null && pet.getImageURL() != null && !pet.getImageURL().isEmpty()) {
            try {
                final URL url = new URL(pet.getImageURL());
                Image img = ImageIO.read(url);
                Image scaled = img.getScaledInstance(
                        picLabel.getPreferredSize().width,
                        picLabel.getPreferredSize().height,
                        Image.SCALE_SMOOTH
                );
                picLabel.setIcon(new ImageIcon(scaled));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            picLabel.setText("No Image");
            picLabel.setHorizontalAlignment(SwingConstants.CENTER);
            picLabel.setVerticalAlignment(SwingConstants.CENTER);
        }

        // Center panel: vertical details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(new Color(245, 245, 245));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("Application ID: " + app.getId());
        JLabel dateLabel = new JLabel("Date: " + app.getDate());
        JLabel userLabel = new JLabel("User ID: " + app.getUserId());
        JLabel userNameLabel = new JLabel("User Name: " + user.getName());
        JLabel petLabel = new JLabel("Pet ID: " + app.getPetId());
        JLabel petNameLabel = new JLabel("Pet Name: " + pet.getName());
        JLabel statusLabel = new JLabel("Status: " + app.getStatus());

        idLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        petLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        petNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        detailsPanel.add(idLabel);
        detailsPanel.add(dateLabel);
        detailsPanel.add(userLabel);
        detailsPanel.add(userNameLabel);
        detailsPanel.add(petLabel);
        detailsPanel.add(petNameLabel);
        detailsPanel.add(statusLabel);

        // Combine picture and details
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(new Color(245, 245, 245));
        leftPanel.add(picLabel, BorderLayout.WEST);
        leftPanel.add(detailsPanel, BorderLayout.CENTER);

        card.add(leftPanel, BorderLayout.CENTER);

        // Right panel (Buttons)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton approveBtn = new JButton("Approve");
        JButton rejectBtn  = new JButton("Reject");
        JButton viewUserBtn = new JButton("View User Details");

        approveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        rejectBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewUserBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(approveBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(rejectBtn);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(viewUserBtn);

        // Button actions
        approveBtn.addActionListener(e -> {
            backend.approveApplication(app.getId());
            JOptionPane.showMessageDialog(frame, "Application approved.");
            loadApplicationCards(); // refresh UI
        });

        rejectBtn.addActionListener(e -> {
            backend.rejectApplication(app.getId());
            JOptionPane.showMessageDialog(frame, "Application rejected.");
            loadApplicationCards(); // refresh UI
        });

        // view user details popup
        viewUserBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    frame,
                    "User Details:\n\n" +
                            "ID: " + user.getId() + "\n" +
                            "Name: " + user.getName() + "\n" +
                            "Address: " + user.getAddress() + "\n" +
                            "ID Type: " + user.getIdType() + "\n" +
                            "Phone: " + user.getPhoneNumber() + "\n" +
                            "Email: " + user.getEmail() + "\n" +
                            "Username: " + user.getUsername(),
                    "User Information",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        card.add(picLabel, BorderLayout.WEST);
        card.add(detailsPanel, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.EAST);

        return card;
    }
}
