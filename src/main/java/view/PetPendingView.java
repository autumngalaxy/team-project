package view;

import interface_adapter.pet_pending.PetPendingViewModel;

import javax.swing.*;
import java.awt.*;

public class PetPendingView extends JPanel {

    private final String viewName = "Pet Pending";
    private final PetPendingViewModel petPendingViewModel;

    private final JLabel username;

    private final JButton petpending;
    private final JButton homepage;


    public PetPendingView(PetPendingViewModel petPendingViewModel, JLabel username){
        this.petPendingViewModel = petPendingViewModel;
        this.username = username;
        // this.petPendingViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Pet Pending");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel petpending_button = new JPanel();
        petpending = new JButton("Pet");
        petpending_button.add(petpending);

        final JPanel homepage_button = new JPanel();
        homepage = new JButton("Homepage");
        homepage_button.add(homepage);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));


        this.add(title);
        this.add(username);
        this.add(petpending_button);
        this.add(homepage_button);
    }

    public String getViewName() {
        return viewName;
    }

}
