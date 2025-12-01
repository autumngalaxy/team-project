package view;

import entity.User;
import interface_adapter.update_profile.UpdateUserProfileController;
import service.Backend;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditProfileView extends JPanel implements PropertyChangeListener {

    private final UpdateUserProfileController controller;
    private final String viewName = "editProfile";

    private JTextField nameField;
    private JTextField emailField;
    private JTextArea addressField;

    public EditProfileView(Backend backend, UpdateUserProfileController controller) {
        this.controller = controller;

        setLayout(new BorderLayout());
        setBackground(new Color(0xF5F7FB));

        // ====== USER DATA ======
        User user = backend.getCurrentUser();
        if (user == null) {
            user = new User(0, "", "", User.idType.PHOTO_CARD, 0, "",
                    "", "", "user");
        }

        // ====== RIGHT CONTENT AREA ======
        JPanel contentWrapper = new JPanel(new GridBagLayout());
        contentWrapper.setBackground(new Color(0xF5F7FB));

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(850, 500));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        // ====== TITLE ======
        JLabel title = new JLabel("Edit Profile");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(60, 60, 75));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);
        card.add(Box.createVerticalStrut(30));

        // ====== FORM FIELDS ======
        nameField = createField(card, "Name:", user.getName());
        emailField = createField(card, "Email:", user.getEmail());
        addressField = createTextArea(card, "Address:", user.getAddress());

        // ====== SAVE BUTTON ======
        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(new Color(110, 150, 255));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        saveBtn.setPreferredSize(new Dimension(100, 36));
        saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        saveBtn.addActionListener(e ->
                controller.save(
                        nameField.getText(),
                        emailField.getText(),
                        addressField.getText()
                )
        );

        card.add(Box.createVerticalStrut(30));
        card.add(saveBtn);

        contentWrapper.add(card);

        add(contentWrapper, BorderLayout.CENTER);
    }

    /** Create an aligned label + textbox line */
    private JTextField createField(JPanel card, String label, String value) {

        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(700, 50));

        JLabel lbl = new JLabel("  " + label);
        lbl.setPreferredSize(new Dimension(120, 40));
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField field = new JTextField(value);
        field.setPreferredSize(new Dimension(400, 30));

        row.add(lbl, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);

        card.add(row);
        card.add(Box.createVerticalStrut(15));
        return field;
    }

    /** Create multi-line Address field */
    private JTextArea createTextArea(JPanel card, String label, String value) {

        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(700, 120));

        JLabel lbl = new JLabel("  " + label);
        lbl.setPreferredSize(new Dimension(120, 40));
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextArea area = new JTextArea(value);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 100));

        row.add(lbl, BorderLayout.WEST);
        row.add(scroll, BorderLayout.CENTER);

        card.add(row);
        card.add(Box.createVerticalStrut(15));

        return area;
    }

//    public void setController(UpdateUserProfileController controller) {
//        this.controller = controller;
//    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}
