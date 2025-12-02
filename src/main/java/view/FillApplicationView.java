package view;

import entity.application.AdoptionApplication;
import entity.application.SurveyInfo;
import interface_adapter.fill_application.FillApplicationController;
import interface_adapter.fill_application.FillApplicationState;
import interface_adapter.fill_application.FillApplicationViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private final JRadioButton photoCard = new JRadioButton();
    private final JRadioButton driverLicense = new JRadioButton();
    private final JRadioButton mailLetter = new JRadioButton();
    private final JRadioButton otherCardType = new JRadioButton();

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
    // 12 enums
    // energy of home -- 3
    private final JRadioButton quietEnergy = new JRadioButton();
    private final JRadioButton loudEnergy = new JRadioButton();
    private final JRadioButton bothEnergy = new JRadioButton();

    // animal staying -- 5
    private final JRadioButton looseStaying = new JRadioButton();
    private final JRadioButton confinedStaying = new JRadioButton();
    private final JRadioButton cratedStaying = new JRadioButton();
    private final JRadioButton outsideStaying = new JRadioButton();
    private final JRadioButton otherStaying = new JRadioButton();

    // alone time -- 4
    private final JRadioButton aboveTenAlone = new JRadioButton();
    private final JRadioButton eightTenAlone = new JRadioButton();
    private final JRadioButton fourSixAlone = new JRadioButton();
    private final JRadioButton lessFour = new JRadioButton();

    // 21 checkboxes
    // live with -- 8
    private final JCheckBox adultLiveWith = new JCheckBox("Adults");
    private final JCheckBox childLiveWith = new JCheckBox("Children");
    private final JCheckBox seniorLiveWith = new JCheckBox("Seniors");
    private final JCheckBox teenLiveWith = new JCheckBox("Teens");
    private final JCheckBox catLiveWith = new JCheckBox("Cats");
    private final JCheckBox dogLiveWith = new JCheckBox("Dogs");
    private final JCheckBox birdLiveWith = new JCheckBox("Birds");
    private final JCheckBox mammalLiveWith = new JCheckBox("Mammals");

    // time with -- 8
    private final JCheckBox cityTimeWith = new JCheckBox("City Walking");
    private final JCheckBox parkTimeWith = new JCheckBox("Parks and Public Walks");
    private final JCheckBox hikeTimeWith = new JCheckBox("Hikes");
    private final JCheckBox jogTimeWith = new JCheckBox("Jogging or Biking");
    private final JCheckBox waterTimeWith = new JCheckBox("Water Time");
    private final JCheckBox campingTimeWith = new JCheckBox("Camping");
    private final JCheckBox quietDayTimeWith = new JCheckBox("Quiet Days");
    private final JCheckBox otherTimeWith = new JCheckBox("Other");

    // enrichment -- 5
    private final JCheckBox toyEnrichment = new JCheckBox("Toys");
    private final JCheckBox brushingEnrichment = new JCheckBox("Brushing or Petting");
    private final JCheckBox otherAnimalEnrichment = new JCheckBox("Other Animals");
    private final JCheckBox playtimeEnrichment = new JCheckBox("Playtime");
    private final JCheckBox otherEnrichment = new JCheckBox("Other");

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
        final JPanel idGroup = new JPanel();

        final JRadioButton photoCardInfo = new JRadioButton(FillApplicationViewModel.PHOTOCARD_LABEL);
        final JRadioButton driverInfo = new JRadioButton(FillApplicationViewModel.DRIVERS_LABEL);
        final JRadioButton mailInfo = new JRadioButton(FillApplicationViewModel.MAIL_LABEL);
        final JRadioButton otherInfo = new JRadioButton(FillApplicationViewModel.OTHER_LABEL);

        idInfo.add(photoCardInfo);
        idInfo.add(driverInfo);
        idInfo.add(mailInfo);
        idInfo.add(otherInfo);

        idGroup.add(photoCardInfo);
        idGroup.add(driverInfo);
        idGroup.add(mailInfo);
        idGroup.add(otherInfo);

        // Address
        final JPanel addressInfo = new JPanel();

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

        addressInfo.add(streetAddressInfo);
        addressInfo.add(cityInfo);
        addressInfo.add(provinceInfo);
        addressInfo.add(postalCodeInfo);
        addressInfo.add(countryInfo);

        // Phone & Email
        final JPanel phoneEmailInfo = new JPanel();

        final LabelTextPanel phoneInfo = new LabelTextPanel(
                new JLabel(FillApplicationViewModel.PHONE_LABEL), phoneInputField);
        final LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel(FillApplicationViewModel.EMAIL_LABEL), emailInputField);

        phoneEmailInfo.add(phoneInfo);
        phoneEmailInfo.add(emailInfo);

        applicationInfo.add(nameInfo);
        applicationInfo.add(idGroup);
        applicationInfo.add(addressInfo);
        applicationInfo.add(phoneEmailInfo);

        /* SURVEY INFO */
        final JPanel surveyInfo = new JPanel();

        // Radials
        JLabel[] energyLabels = {new JLabel("Quiet"), new JLabel("Loud"), new JLabel("Both")};
        JRadioButton[] energies = {quietEnergy, loudEnergy, bothEnergy};
        final LabelRadioPanel energyInfo = new LabelRadioPanel(
                energyLabels,
                energies
        );
        /*final JPanel energyPanel = new JPanel();
        final ButtonGroup energyGroup = new ButtonGroup();
        final JRadioButton quietButton = new JRadioButton();
        final JRadioButton loudButton = new JRadioButton();
        final JRadioButton bothButton = new JRadioButton(); */

        /*energyGroup.add(quietButton);
        energyGroup.add(loudButton);
        energyGroup.add(bothButton);
        energyPanel.add(quietButton);
        energyPanel.add(loudButton);
        energyPanel.add(bothButton);*/

        surveyInfo.add(energyInfo);

        JLabel[] stayingLabels = {new JLabel("Loose"), new JLabel("Confined"), new JLabel("Crated"), new JLabel("Outside"), new JLabel("Other")};
        JRadioButton[] stayings = {looseStaying, confinedStaying, cratedStaying, outsideStaying, otherStaying};
        final LabelRadioPanel stayingInfo = new LabelRadioPanel(
                stayingLabels,
                stayings
        );
        surveyInfo.add(stayingInfo);
        /*final JPanel stayingPanel = new JPanel();
        final ButtonGroup stayingGroup = new ButtonGroup();
        final JRadioButton looseButton = new JRadioButton();
        final JRadioButton confinedButton = new JRadioButton();
        final JRadioButton cratedButton = new JRadioButton();
        final JRadioButton outsideButton = new JRadioButton();
        final JRadioButton otherStayingButton = new JRadioButton();*/

        JLabel[] aloneLabels = {new JLabel("10+ Hours"), new JLabel("8-10 Hours"), new JLabel("4-8 Hours"), new JLabel(">4 Hours")};
        JRadioButton[] aloneRadios = {aboveTenAlone, eightTenAlone, fourSixAlone, lessFour};
        final LabelRadioPanel aloneInfo = new LabelRadioPanel(
                aloneLabels,
                aloneRadios
        );
        surveyInfo.add(aloneInfo);
        /* final JPanel alonePanel = new JPanel();
        final ButtonGroup aloneGroup = new ButtonGroup();
        final JRadioButton moreTenAlone = new JRadioButton();
        final JRadioButton eightTenAlone = new JRadioButton();
        final JRadioButton fourSixAlone = new JRadioButton();
        final JRadioButton lessFourAlone = new JRadioButton();*/

        // Checkboxes
        final JPanel liveWithPanel = new JPanel();
        liveWithPanel.add(adultLiveWith);
        liveWithPanel.add(childLiveWith);
        liveWithPanel.add(seniorLiveWith);
        liveWithPanel.add(teenLiveWith);
        liveWithPanel.add(catLiveWith);
        liveWithPanel.add(dogLiveWith);
        liveWithPanel.add(birdLiveWith);
        liveWithPanel.add(mammalLiveWith);

        final JPanel timeWithPanel = new JPanel();
        timeWithPanel.add(cityTimeWith);
        timeWithPanel.add(parkTimeWith);
        timeWithPanel.add(hikeTimeWith);
        timeWithPanel.add(jogTimeWith);
        timeWithPanel.add(waterTimeWith);
        timeWithPanel.add(campingTimeWith);
        timeWithPanel.add(quietDayTimeWith);
        timeWithPanel.add(otherTimeWith);

        final JPanel enrichmentPanel = new JPanel();
        enrichmentPanel.add(toyEnrichment);
        enrichmentPanel.add(brushingEnrichment);
        enrichmentPanel.add(otherAnimalEnrichment);
        enrichmentPanel.add(playtimeEnrichment);
        enrichmentPanel.add(otherEnrichment);

        surveyInfo.add(liveWithPanel);
        surveyInfo.add(timeWithPanel);
        surveyInfo.add(enrichmentPanel);

        /* BUTTONS */
        final JPanel buttons = new JPanel();
        submit = new JButton(FillApplicationViewModel.SUBMIT_BUTTON_LABEL);
        buttons.add(submit);
        cancel = new JButton(FillApplicationViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        /* LISTENERS */
        submit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(submit)){
                            final FillApplicationState currentState = fillApplicationViewModel.getState();

                            fillApplicationController.execute(
                                    currentState.getPet,
                                    currentState.getName,
                                    currentState.getID,
                                    currentState.getAddress,
                                    currentState.getPhone,
                                    currentState.getEmail,
                                    currentState.getSurveyInfo
                            );
                        }
                    }
                }
        ); // TODO; see lab

        cancel.addActionListener(this);

        /* USE LISTENER METHODS */
        addNameListener();
        addPhoneListener();
        addEmailListener();
        addAddressListener();
        addEnumListener();
        addCheckboxListener();

        /* LAYOUT */
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(applicationInfo);
        this.add(surveyInfo);
        this.add(buttons);
    }

    /* LISTENER METHODS */
    private void addNameListener(){
        nameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setName(nameInputField.getText());
                fillApplicationViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) { documentListenerHelper(); }

            @Override
            public void removeUpdate(DocumentEvent e) { documentListenerHelper(); }

            @Override
            public void changedUpdate(DocumentEvent e) { documentListenerHelper(); }
        });
    }

    private void addPhoneListener(){
        phoneInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper(){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setPhone(phoneInputField.getText());
                fillApplicationViewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addEmailListener(){
        emailInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper(){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setEmail(emailInputField.getText());
                fillApplicationViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addAddressListener(){
        streetAddressInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper(){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setStreetAddress(streetAddressInputField.getText());
                fillApplicationViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        cityInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper(){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setCity(cityInputField.getText());
                fillApplicationViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        provinceInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper(){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setProvince(provinceInputField.getText());
                fillApplicationViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        postalCodeInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper(){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setPostalCode(postalCodeInputField.getText());
                fillApplicationViewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
        countryInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper(){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setCountry(countryInputField.getText());
                fillApplicationViewModel.setState(currentState);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addEnumListener(){
        quietEnergy.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (quietEnergy.isSelected()) {
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setEnergy(SurveyInfo.EnergyOfHome.QUIET);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });
        loudEnergy.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (loudEnergy.isSelected()){
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setEnergy(SurveyInfo.EnergyOfHome.LOUD);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });
        bothEnergy.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (bothEnergy.isSelected()){
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setEnergy(SurveyInfo.EnergyOfHome.BOTH);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });

        /* Animal Staying*/
        looseStaying.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (looseStaying.isSelected()) {
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setStaying(SurveyInfo.AnimalStaying.LOOSE);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });

        confinedStaying.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (confinedStaying.isSelected()){
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setStaying(SurveyInfo.AnimalStaying.CONFINED);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });
        cratedStaying.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(cratedStaying.isSelected()){
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setStaying(SurveyInfo.AnimalStaying.CRATED);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });
        outsideStaying.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(outsideStaying.isSelected()){
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setStaying(SurveyInfo.AnimalStaying.OUTSIDE);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });
        otherStaying.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (otherStaying.isSelected()){
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setStaying(SurveyInfo.AnimalStaying.OTHER);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });


        /* Animal Alone Time */
        aboveTenAlone.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(aboveTenAlone.isSelected()){
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setAlone(SurveyInfo.AloneTime.MORE_THAN_TEN);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });
        eightTenAlone.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(eightTenAlone.isSelected()){
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setAlone(SurveyInfo.AloneTime.EIGHT_TO_TEN);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });
        fourSixAlone.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(fourSixAlone.isSelected()){
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setAlone(SurveyInfo.AloneTime.FOUR_TO_SIX);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });
        lessFour.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(lessFour.isSelected()){
                    final FillApplicationState currentState = fillApplicationViewModel.getState();
                    currentState.setAlone(SurveyInfo.AloneTime.LESS_THAN_FOUR);
                    fillApplicationViewModel.setState(currentState);
                }
            }
        });


        final FillApplicationState currentState = fillApplicationViewModel.getState();

        fillApplicationViewModel.setState(currentState);
    }

    void liveWithAdd(JCheckBox updatedBox, int num){
        final FillApplicationState currentState = fillApplicationViewModel.getState();
        boolean[] updatedState = currentState.getLiveWith();
        updatedState[num] = updatedBox.isSelected();
        currentState.setLiveWith(updatedState);
        fillApplicationViewModel.setState(currentState);
    }

    void timeWithAdd(JCheckBox updatedBox, int num){
        final FillApplicationState currentState = fillApplicationViewModel.getState();
        boolean[] updatedState = currentState.getTimeWith();
        updatedState[num] = updatedBox.isSelected();
        currentState.setTimeWith(updatedState);
        fillApplicationViewModel.setState(currentState);
    }

    void enrichmentAdd(JCheckBox updatedBox, int num){
        final FillApplicationState currentState = fillApplicationViewModel.getState();
        boolean[] updatedState = currentState.getEnrichment();
        updatedState[num] = updatedBox.isSelected();
        currentState.setEnrichment(updatedState);
        fillApplicationViewModel.setState(currentState);
    }

    private void addCheckboxListener(){
        /* LIVE WITH CHECKBOXES */
        adultLiveWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                liveWithAdd(adultLiveWith, 0);
            }
        });

        childLiveWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                liveWithAdd(childLiveWith, 1);
            }
        });
        seniorLiveWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                liveWithAdd(seniorLiveWith, 2);
            }
        });
        teenLiveWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                liveWithAdd(teenLiveWith, 3);
            }
        });
        catLiveWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                liveWithAdd(teenLiveWith, 4);
            }
        });
        dogLiveWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                liveWithAdd(dogLiveWith, 5);
            }
        });
        birdLiveWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                liveWithAdd(birdLiveWith, 6);
            }
        });
        mammalLiveWith.addChangeListener(new ChangeListener() {
             @Override
             public void stateChanged(ChangeEvent e) {
                 liveWithAdd(mammalLiveWith, 7);
             }
         });

        cityTimeWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timeWithAdd(cityTimeWith, 0);
            }
        });
        parkTimeWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timeWithAdd(parkTimeWith, 1);
            }
        });
        hikeTimeWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timeWithAdd(hikeTimeWith, 2);
            }
        });
        jogTimeWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timeWithAdd(jogTimeWith, 3);
            }
        });
        waterTimeWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timeWithAdd(waterTimeWith, 4);
            }
        });
        campingTimeWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timeWithAdd(campingTimeWith, 5);
            }
        });
        quietDayTimeWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timeWithAdd(quietDayTimeWith, 6);
            }
        });
        otherTimeWith.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timeWithAdd(otherTimeWith, 7);
            }
        });

        /* ENRICHMENT CHECBOXES */
        toyEnrichment.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                enrichmentAdd(toyEnrichment, 0);
            }
        });
        brushingEnrichment.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                enrichmentAdd(brushingEnrichment, 1);
            }
        });
        otherAnimalEnrichment.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                enrichmentAdd(otherAnimalEnrichment, 2);
            }
        });
        playtimeEnrichment.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                enrichmentAdd(playtimeEnrichment, 3);
            }
        });
        otherEnrichment.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                enrichmentAdd(playtimeEnrichment, 4);
            }
        });
    }

    /* OTHER METHODS */
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final FillApplicationState state = (FillApplicationState) evt.getNewValue();
        /*if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }*/
    }

    public String getViewName() {
        return viewName;
    }

    public void setFillApplicationController(FillApplicationController controller) {
        this.fillApplicationController = controller;
    }
}