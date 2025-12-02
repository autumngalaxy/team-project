package view;

import entity.application.SurveyInfo;
import interface_adapter.fill_application.FillApplicationController;
import interface_adapter.fill_application.FillApplicationState;
import interface_adapter.fill_application.FillApplicationViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import entity.Pet;
/**
 * The View for when the user is filling out an Adoption Application.
 */
public class FillApplicationView extends JFrame implements ActionListener, PropertyChangeListener {

    private final String viewName = "fill application";

    private final FillApplicationViewModel fillApplicationViewModel;
    private FillApplicationController fillApplicationController = null;
    private Pet correspondingPet;
    private int correspondingUser;

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

    public FillApplicationView(FillApplicationViewModel fillApplicationViewModel, Pet correspondingPet, int coorespondingUser) {
        super("Fill Application View");

        this.fillApplicationViewModel = fillApplicationViewModel;
        fillApplicationViewModel.addPropertyChangeListener(this);

        this.correspondingPet = correspondingPet;
        this.correspondingUser = coorespondingUser;

        final JPanel mainPanel = new JPanel();

        final JLabel title = new JLabel(FillApplicationViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        /* APPLICATION INFO */
        final JPanel applicationInfo = new JPanel();

        /* SURVEY INFO */
        final JPanel surveyInfo = new JPanel();

        // Radials
        JLabel[] energyLabels = {new JLabel("Quiet"), new JLabel("Loud"), new JLabel("Both")};
        JRadioButton[] energies = {quietEnergy, loudEnergy, bothEnergy};
        final LabelRadioPanel energyInfo = new LabelRadioPanel(
                energyLabels,
                energies
        );

        surveyInfo.add(energyInfo);

        JLabel[] stayingLabels = {new JLabel("Loose"), new JLabel("Confined"), new JLabel("Crated"), new JLabel("Outside"), new JLabel("Other")};
        JRadioButton[] stayings = {looseStaying, confinedStaying, cratedStaying, outsideStaying, otherStaying};
        final LabelRadioPanel stayingInfo = new LabelRadioPanel(
                stayingLabels,
                stayings
        );
        surveyInfo.add(stayingInfo);

        JLabel[] aloneLabels = {new JLabel("10+ Hours"), new JLabel("8-10 Hours"), new JLabel("4-8 Hours"), new JLabel(">4 Hours")};
        JRadioButton[] aloneRadios = {aboveTenAlone, eightTenAlone, fourSixAlone, lessFour};
        final LabelRadioPanel aloneInfo = new LabelRadioPanel(
                aloneLabels,
                aloneRadios
        );
        surveyInfo.add(aloneInfo);

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
                                    currentState.getCorespondingPet(),
                                    currentState.getCorrespondingUser(),
                                    currentState.getSurveyInfo()
                            );
                            makeInvisible();
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        /* USE LISTENER METHODS */
        addEnumListener();
        addCheckboxListener();

        /* LAYOUT */
        mainPanel.add(title);
        mainPanel.add(applicationInfo);
        mainPanel.add(surveyInfo);
        mainPanel.add(buttons);
        setContentPane(mainPanel);
        getContentPane().setLayout( new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void makeVisible(Pet pet, int user){
        this.correspondingPet = pet;
        this.correspondingUser = user;
        FillApplicationState curState = fillApplicationViewModel.getState();
        curState.setCorrespondingPet(pet);
        curState.setCorrespondingUser(user);
        fillApplicationViewModel.setState(curState);
        setVisible(true);
    }

    public void makeInvisible(){
        setVisible(false);

        quietEnergy.setSelected(true);

        looseStaying.setSelected(true);

        aboveTenAlone.setSelected(true);

        adultLiveWith.setSelected(false);
        childLiveWith.setSelected(false);
        seniorLiveWith.setSelected(false);
        childLiveWith.setSelected(false);
        teenLiveWith.setSelected(false);
        catLiveWith.setSelected(false);
        dogLiveWith.setSelected(false);
        birdLiveWith.setSelected(false);
        mammalLiveWith.setSelected(false);

        cityTimeWith.setSelected(false);
        parkTimeWith.setSelected(false);
        hikeTimeWith.setSelected(false);
        jogTimeWith.setSelected(false);
        waterTimeWith.setSelected(false);
        campingTimeWith.setSelected(false);
        quietDayTimeWith.setSelected(false);
        otherTimeWith.setSelected(false);

        toyEnrichment.setSelected(false);
        brushingEnrichment.setSelected(false);
        otherAnimalEnrichment.setSelected(false);
        playtimeEnrichment.setSelected(false);
        otherEnrichment.setSelected(false);

    }

    /* LISTENER METHODS */
    private void addEnumListener(){
        quietEnergy.addChangeListener(e -> {
            if (quietEnergy.isSelected()) {
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setEnergy(SurveyInfo.EnergyOfHome.QUIET);
                fillApplicationViewModel.setState(currentState);
            }
        });
        loudEnergy.addChangeListener(e -> {
            if (loudEnergy.isSelected()){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setEnergy(SurveyInfo.EnergyOfHome.LOUD);
                fillApplicationViewModel.setState(currentState);
            }
        });
        bothEnergy.addChangeListener(e -> {
            if (bothEnergy.isSelected()){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setEnergy(SurveyInfo.EnergyOfHome.BOTH);
                fillApplicationViewModel.setState(currentState);
            }
        });

        /* Animal Staying*/
        looseStaying.addChangeListener(e -> {
            if (looseStaying.isSelected()) {
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setStaying(SurveyInfo.AnimalStaying.LOOSE);
                fillApplicationViewModel.setState(currentState);
            }
        });

        confinedStaying.addChangeListener(e -> {
            if (confinedStaying.isSelected()){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setStaying(SurveyInfo.AnimalStaying.CONFINED);
                fillApplicationViewModel.setState(currentState);
            }
        });
        cratedStaying.addChangeListener(e -> {
            if(cratedStaying.isSelected()){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setStaying(SurveyInfo.AnimalStaying.CRATED);
                fillApplicationViewModel.setState(currentState);
            }
        });
        outsideStaying.addChangeListener(e -> {
            if(outsideStaying.isSelected()){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setStaying(SurveyInfo.AnimalStaying.OUTSIDE);
                fillApplicationViewModel.setState(currentState);
            }
        });
        otherStaying.addChangeListener(e -> {
            if (otherStaying.isSelected()){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setStaying(SurveyInfo.AnimalStaying.OTHER);
                fillApplicationViewModel.setState(currentState);
            }
        });


        /* Animal Alone Time */
        aboveTenAlone.addChangeListener(e -> {
            if(aboveTenAlone.isSelected()){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setAlone(SurveyInfo.AloneTime.MORE_THAN_TEN);
                fillApplicationViewModel.setState(currentState);
            }
        });
        eightTenAlone.addChangeListener(e -> {
            if(eightTenAlone.isSelected()){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setAlone(SurveyInfo.AloneTime.EIGHT_TO_TEN);
                fillApplicationViewModel.setState(currentState);
            }
        });
        fourSixAlone.addChangeListener(e -> {
            if(fourSixAlone.isSelected()){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setAlone(SurveyInfo.AloneTime.FOUR_TO_SIX);
                fillApplicationViewModel.setState(currentState);
            }
        });
        lessFour.addChangeListener(e -> {
            if(lessFour.isSelected()){
                final FillApplicationState currentState = fillApplicationViewModel.getState();
                currentState.setAlone(SurveyInfo.AloneTime.LESS_THAN_FOUR);
                fillApplicationViewModel.setState(currentState);
            }
        });


        final FillApplicationState currentState = fillApplicationViewModel.getState();

        fillApplicationViewModel.setState(currentState);
    }

    void liveWithAdd(JCheckBox updatedBox, int num){
        final FillApplicationState currentState = fillApplicationViewModel.getState();
        boolean[] updatedState = currentState.getSurveyInfo().getAdopterLivesWith();
        updatedState[num] = updatedBox.isSelected();
        currentState.setLiveWith(updatedState);
        fillApplicationViewModel.setState(currentState);
    }

    void timeWithAdd(JCheckBox updatedBox, int num){
        final FillApplicationState currentState = fillApplicationViewModel.getState();
        boolean[] updatedState = currentState.getSurveyInfo().getAdopterSpendsTimeWith();
        updatedState[num] = updatedBox.isSelected();
        currentState.setTimeWith(updatedState);
        fillApplicationViewModel.setState(currentState);
    }

    void enrichmentAdd(JCheckBox updatedBox, int num){
        final FillApplicationState currentState = fillApplicationViewModel.getState();
        boolean[] updatedState = currentState.getSurveyInfo().getAdopterEnrichment();
        updatedState[num] = updatedBox.isSelected();
        currentState.setEnrichment(updatedState);
        fillApplicationViewModel.setState(currentState);
    }

    private void addCheckboxListener(){
        /* LIVE WITH CHECKBOXES */
        adultLiveWith.addChangeListener(e -> liveWithAdd(adultLiveWith, 0));
        childLiveWith.addChangeListener(e -> liveWithAdd(childLiveWith, 1));
        seniorLiveWith.addChangeListener(e -> liveWithAdd(seniorLiveWith, 2));
        teenLiveWith.addChangeListener(e -> liveWithAdd(teenLiveWith, 3));
        catLiveWith.addChangeListener(e -> liveWithAdd(teenLiveWith, 4));
        dogLiveWith.addChangeListener(e -> liveWithAdd(dogLiveWith, 5));
        birdLiveWith.addChangeListener(e -> liveWithAdd(birdLiveWith, 6));
        mammalLiveWith.addChangeListener(e -> liveWithAdd(mammalLiveWith, 7));

        cityTimeWith.addChangeListener(e -> timeWithAdd(cityTimeWith, 0));
        parkTimeWith.addChangeListener(e -> timeWithAdd(parkTimeWith, 1));
        hikeTimeWith.addChangeListener(e -> timeWithAdd(hikeTimeWith, 2));
        jogTimeWith.addChangeListener(e -> timeWithAdd(jogTimeWith, 3));
        waterTimeWith.addChangeListener(e -> timeWithAdd(waterTimeWith, 4));
        campingTimeWith.addChangeListener(e -> timeWithAdd(campingTimeWith, 5));
        quietDayTimeWith.addChangeListener(e -> timeWithAdd(quietDayTimeWith, 6));
        otherTimeWith.addChangeListener(e -> timeWithAdd(otherTimeWith, 7));

        /* ENRICHMENT CHECBOXES */
        toyEnrichment.addChangeListener(e -> enrichmentAdd(toyEnrichment, 0));
        brushingEnrichment.addChangeListener(e -> enrichmentAdd(brushingEnrichment, 1));
        otherAnimalEnrichment.addChangeListener(e -> enrichmentAdd(otherAnimalEnrichment, 2));
        playtimeEnrichment.addChangeListener(e -> enrichmentAdd(playtimeEnrichment, 3));
        otherEnrichment.addChangeListener(e -> enrichmentAdd(playtimeEnrichment, 4));
    }

    /* OTHER METHODS */
    @Override
    public void actionPerformed(ActionEvent e) {
        makeInvisible();
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