package view;

import javax.swing.*;

/**
 * A panel containing text labels and their corresponding radiobuttons.
 * labels and radioButtons must be the same length.
 */
public class LabelRadioPanel extends JPanel {
    LabelRadioPanel (JLabel[] labels, JRadioButton[] radioButtons){
        assert(labels.length == radioButtons.length);

        ButtonGroup panelGroup = new ButtonGroup();
        for (int i = 0; i < labels.length; i++){
            this.add(labels[i]);
            this.add(radioButtons[i]);
            panelGroup.add(radioButtons[i]);
        }
    }
}
