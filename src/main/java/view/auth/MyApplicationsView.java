package view.auth;

import entity.Application;
import entity.Pet;
import service.Backend;
import view.common.RightContentTemplate;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyApplicationsView extends RightContentTemplate {

    private final Backend backend;

    private final JPanel listPanel = new JPanel();

    public MyApplicationsView(Backend backend) {
        super("My Applications");

        this.backend = backend;

        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        listPanel.setMaximumSize(new Dimension(900, Integer.MAX_VALUE)); 
        listPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.add(Box.createVerticalStrut(10));
        bodyPanel.add(listPanel);
        bodyPanel.add(Box.createVerticalStrut(10));

        loadMyApplications();
    }

    private void loadMyApplications() {

        listPanel.removeAll();

        if (backend.getCurrentUser() == null) {
            JLabel empty = new JLabel("No user logged in.");
            empty.setFont(new Font("Arial", Font.PLAIN, 16));
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(empty);
            refreshList();
            return;
        }

        int uid = backend.getCurrentUser().getId();

        List<Application> apps = backend.getApplicationsForUser(uid);

        if (apps.isEmpty()) {
            JLabel empty = new JLabel("You have no applications.");
            empty.setFont(new Font("Arial", Font.PLAIN, 16));
            empty.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(empty);

        } else {
            for (Application app : apps) {
                listPanel.add(createApplicationCard(app));
                listPanel.add(Box.createVerticalStrut(15));
            }
        }

        refreshList();
    }

    private JPanel createApplicationCard(Application app) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        card.setMaximumSize(new Dimension(900, 150)); 
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        Pet pet = backend.getPetById(app.getPetId());

        // info
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(Color.WHITE);

        info.add(new JLabel("Pet Name: " + (pet != null ? pet.getName() : "Unknown")));
        info.add(new JLabel("Species: " + (pet != null ? pet.getSpecies() : "-")));
        info.add(new JLabel("Date: " + app.getDate()));
        
        JLabel statusLabel = new JLabel("Status: " + app.getStatus());
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));

        switch (app.getStatus()) {
            case PENDING:
                statusLabel.setForeground(new Color(255, 140, 0)); // orange
                break;
            case APPROVED:
                statusLabel.setForeground(new Color(0, 160, 0));  // green
                break;
            case REJECTED:
                statusLabel.setForeground(new Color(200, 0, 0));  // red
                break;
            default:
                statusLabel.setForeground(Color.DARK_GRAY);
        }

        info.add(Box.createVerticalStrut(8));
        info.add(statusLabel);

        card.add(info, BorderLayout.CENTER);

        // button
        JPanel btnArea = new JPanel();
        btnArea.setLayout(new BoxLayout(btnArea, BoxLayout.Y_AXIS));
        btnArea.setBackground(Color.WHITE);

        if (app.getStatus() == Application.Status.PENDING) {
            JButton cancelBtn = new JButton("Cancel Application");
            cancelBtn.setBackground(new Color(220, 80, 80));
            cancelBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            cancelBtn.setForeground(Color.WHITE);

            cancelBtn.addActionListener(e -> {
                int choice = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel this application?",
                        "Confirm Cancel",
                        JOptionPane.YES_NO_OPTION
                );
                if (choice == JOptionPane.YES_OPTION) {
                    backend.deleteApplicationById(app.getId());
                    JOptionPane.showMessageDialog(this,
                            "Application cancelled.");
                    refreshList();
                }
            });

            btnArea.add(cancelBtn);
        }

        card.add(btnArea, BorderLayout.EAST);

        return card;
    }

    private void refreshList() {
        listPanel.revalidate();
        listPanel.repaint();
    }
}
