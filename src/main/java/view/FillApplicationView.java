package view;

import interface_adapter.fill_application.FillApplicationController;
import interface_adapter.fill_application.FillApplicationViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is filling out an Adoption Application.
 */
public class FillApplicationView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "fill application";

    private final FillApplicationViewModel fillApplicationViewModel;
    private FillApplicationController fillApplicationController = null;


    /* NAME */
    private final JTextField nameInputField = new JTextField(15);
    /* RADIAL BUTTONS FOR ID TYPE*/
    // TODO
    /* ADOPTER ADDRESS [Street address, city, province, postal code, country] */
    private final JTextField streetAddressInputField = new JTextField(15);
    private final JTextField cityInputField = new JTextField(15);
    private final JTextField provinceInputField = new JTextField(15);
    private final JTextField postalCodeInputField = new JTextField(15);
    private final JTextField countryInputField = new JTextField(15);

    /* PHONE NUMBER & EMAIL */
    private final JTextField phoneInputField = new JTextField(15);
    private final JTextField emailInputField = new JTextField(15);

    /* SURVEY INFO */
    // TODO

    /* BUTTONS */
    private final JButton submit;
    private final JButton cancel;

    public FillApplicationView(FillApplicationViewModel fillApplicationViewModel) {
        this.fillApplicationViewModel = fillApplicationViewModel;
        fillApplicationViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(FillApplicationViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        /* APPLICATION INFO */
        final JPanel applicationInfo = new JPanel();
        // Name
        final LabelTextPanel nameInfo = new LabelTextPanel(
                new JLabel(FillApplicationViewModel.NAME_LABEL), nameInputField);
        // ID Type
        final ButtonGroup idInfo = new ButtonGroup();

        final JRadioButton photoCardInfo = new JRadioButton(FillApplicationViewModel.PHOTOCARD_LABEL);
        final JRadioButton driverInfo = new JRadioButton(FillApplicationViewModel.DRIVERS_LABEL);
        final JRadioButton mailInfo = new JRadioButton(FillApplicationViewModel.MAIL_LABEL);
        final JRadioButton

        // idInfo.add();

        //todo

        // Address
        final LabelTextPanel streetAddressInfo = new LabelTextPanel(
                new JLabel(FillApplicationViewModel.STREET_LABEL), streetAddressInputField);
        final LabelTextPanel cityInfo = new LabelTextPanel(
                new JLabel(FillApplicationViewModel.CITY_LABEL), cityInputField);
        final LabelTextPanel provinceInfo = new LabelTextPanel(
                new JLabel(FillApplicationViewModel.PROVINCE_LABEL), provinceInputField);
        final LabelTextPanel postalCodeInfo = new LabelTextPanel(
                new JLabel(FillApplicationViewModel.POSTAL_LABEL), postalCodeInputField);
        final LabelTextPanel countryInfo = new LabelTextPanel(
                new JLabel(FillApplicationViewModel.COUNTRY_LABEL), countryInputField);
        // Phone & Email
        final LabelTextPanel phoneInfo = new LabelTextPanel(
                new JLabel(FillApplicationViewModel.PHONE_LABEL), phoneInputField);
        final LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel(FillApplicationViewModel.EMAIL_LABEL), emailInputField);

        /* SURVEY INFO */
        final JPanel surveyInfo = new JPanel();
        // TODO

        /* BUTTONS */
        final JPanel buttons = new JPanel();
        submit = new JButton(FillApplicationViewModel.SUBMIT_BUTTON_LABEL);
        buttons.add(submit);
        cancel = new JButton(FillApplicationViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        /* LISTENERS */
        // todo
        // submit.addActionListener(); // TODO; see lab

        cancel.addActionListener(this);

        /* LAYOUT */
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(applicationInfo);
        this.add(surveyInfo);
        // todo
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }

    public void setFillApplicationController(FillApplicationController controller) {
        this.fillApplicationController = controller;
    }
}