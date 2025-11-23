package view;

import entity.Pet;
import service.Backend;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class BrowsePetsPage extends JPanel {

    private Backend backend;
    private JPanel petsPanel;
    private ArrayList<Pet> petsList;

    public BrowsePetsPage(JFrame jFrame, Backend backend) {
        this.backend = backend;

        // JPanel version: no title, no setSize, no close operation
        setLayout(new BorderLayout());

        // -----------------------
        // Filter panel (top)
        // -----------------------
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] types = {"All", "Dog", "Cat", "Rabbit"};
        JComboBox<String> typeFilter = new JComboBox<>(types);
        String[] genders = {"Any", "Male", "Female"};
        JComboBox<String> genderFilter = new JComboBox<>(genders);
        JButton applyFilter = new JButton("Apply Filter");

        filterPanel.add(new JLabel("Species:"));
        filterPanel.add(typeFilter);
        filterPanel.add(new JLabel("Gender:"));
        filterPanel.add(genderFilter);
        filterPanel.add(applyFilter);
        add(filterPanel, BorderLayout.NORTH);

        // -----------------------
        // Scrollable pets panel
        // -----------------------
        petsPanel = new JPanel();
        petsPanel.setLayout(new GridLayout(0, 2, 20, 20));  // 2 columns default

        JScrollPane scrollPane = new JScrollPane(petsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Load pets
        petsList = new ArrayList<>(backend.pets.values());
        loadPets(petsList);

        // Filter button logic
        applyFilter.addActionListener(e -> {
            String selectedType = (String) typeFilter.getSelectedItem();
            String selectedGender = (String) genderFilter.getSelectedItem();
            ArrayList<Pet> filtered = new ArrayList<>();

            for (Pet p : petsList) {
                boolean matchesSpecies = selectedType.equals("All")
                        || p.getSpecies().equalsIgnoreCase(selectedType);
                boolean matchesGender = selectedGender.equals("Any")
                        || p.getGender() == Pet.Gender.valueOf(selectedGender);

                if (matchesSpecies && matchesGender) {
                    filtered.add(p);
                }
            }

            loadPets(filtered);
        });

        // Resize listener for dynamic columns
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeGridColumns();
            }
        });
    }

    // Dynamically adjust grid columns based on width
    private void resizeGridColumns() {
        int panelWidth = petsPanel.getWidth();
        int cardWidth = 320; // approx card width
        int columns = Math.max(2, panelWidth / cardWidth);

        GridLayout layout = (GridLayout) petsPanel.getLayout();
        layout.setColumns(columns);

        petsPanel.revalidate();
    }

    // Load pet cards
    private void loadPets(ArrayList<Pet> pets) {
        petsPanel.removeAll();
        for (Pet pet : pets) {
            petsPanel.add(createPetCard(pet));
        }
        petsPanel.revalidate();
        petsPanel.repaint();
    }

    // Create one pet card
    private JPanel createPetCard(Pet pet) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(300, 500));
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setBackground(Color.WHITE);

        // Image placeholder
        JLabel imageLabel = new JLabel("[Image]", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(200, 250));
        card.add(imageLabel, BorderLayout.NORTH);

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoPanel.add(new JLabel("Name: " + pet.getName()));
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(new JLabel("Species: " + pet.getSpecies()));
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(new JLabel("Age: " + pet.getAge() + " years"));
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(new JLabel("Gender: " + pet.getGender()));
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(new JLabel("Breed: " + pet.getBreed()));
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(new JLabel("Color: " + pet.getColor()));
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(new JLabel("Size: " + pet.getSize()));
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(new JLabel("Status: " + (pet.isAdopted() ? "Already Adopted" : "Available")));
        infoPanel.add(Box.createVerticalStrut(5));

        // Description box
        JTextArea descriptionArea = new JTextArea("Description: " + pet.getDescription());
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(descriptionArea);
        infoPanel.add(Box.createVerticalStrut(5));

        card.add(infoPanel, BorderLayout.CENTER);

        // Wishlist button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton wishlistButton = new JButton("Add to Wishlist");
        buttonPanel.add(wishlistButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }
}
