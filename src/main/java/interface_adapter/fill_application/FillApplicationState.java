package interface_adapter.fill_application;

import entity.Pet;
import entity.application.AdoptionApplication;
import entity.application.SurveyBuilder;
import entity.application.SurveyInfo;

import java.time.LocalDate;

/**
 * The state for the Fill Application View Model
 */
public class FillApplicationState {
    private Pet correspondingPet;
    /*private String adopterName = "";
    private AdoptionApplication.IDType idType = AdoptionApplication.IDType.PHOTO_CARD;
    private String[] adopterAddress = {"", "", "", "", ""};
    private String adopterPhone = "";
    private String adopterEmail = "";*/
    private int correspondingUser;

    private LocalDate applicationDate;
    private String applicationID;

    private SurveyInfo surveyInfo = new SurveyBuilder().build();


    /* GETTERS */
    public Pet getCorespondingPet() {
        return correspondingPet;
    }

    /*public String getAdopterName() {
        return adopterName;
    }

    public AdoptionApplication.IDType getIdType() {
        return idType;
    }

    public String[] getAdopterAddress() {
        return adopterAddress;
    }

    public String getAdopterPhone() {
        return adopterPhone;
    }

    public String getAdopterEmail() {
        return adopterEmail;
    }*/

    public SurveyInfo getSurveyInfo() {
        return surveyInfo;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public String getApplicationID() { return applicationID; }

    /* SETTERS */
    public void setCorrespondingPet(Pet correspondingPet) {
        this.correspondingPet = correspondingPet;
    }

    /*public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public void setIdType(AdoptionApplication.IDType idType) {
        this.idType = idType;
    }

    public void setAdopterAddress(String[] adopterAddress) {
        this.adopterAddress = adopterAddress;
    }

    public void setAdopterPhone(String adopterPhone) {
        this.adopterPhone = adopterPhone;
    }

    public void setAdopterEmail(String adopterEmail) {
        this.adopterEmail = adopterEmail;
    }*/

    public void setSurveyInfo(SurveyInfo surveyInfo) {
        this.surveyInfo = surveyInfo;
    }

    public void setApplicationDate() {
        this.applicationDate = LocalDate.now();
    }

    /* Survey info stuff */
    public void setEnergy(SurveyInfo.EnergyOfHome energy){
        SurveyInfo sb = new SurveyBuilder()
                .addAdopterEnergy(energy)
                .addAnimalAlone(surveyInfo.getAnimalAlone())
                .addEnrichment(surveyInfo.getAdopterEnrichment())
                .addTimeWith(surveyInfo.getAdopterSpendsTimeWith())
                .addLivesWith(surveyInfo.getAdopterLivesWith())
                .addAnimalStaying(surveyInfo.getAnimalStaying())
                .build();

        setSurveyInfo(sb);
    }

    public void setStaying(SurveyInfo.AnimalStaying staying){
        SurveyInfo sb = new SurveyBuilder()
                .addAdopterEnergy(surveyInfo.getAdopterEnergy())
                .addAnimalAlone(surveyInfo.getAnimalAlone())
                .addEnrichment(surveyInfo.getAdopterEnrichment())
                .addTimeWith(surveyInfo.getAdopterSpendsTimeWith())
                .addLivesWith(surveyInfo.getAdopterLivesWith())
                .addAnimalStaying(staying)
                .build();

        setSurveyInfo(sb);
    }

    public void setAlone(SurveyInfo.AloneTime aloneTime){
        SurveyInfo sb = new SurveyBuilder()
                .addAdopterEnergy(surveyInfo.getAdopterEnergy())
                .addAnimalAlone(aloneTime)
                .addEnrichment(surveyInfo.getAdopterEnrichment())
                .addTimeWith(surveyInfo.getAdopterSpendsTimeWith())
                .addLivesWith(surveyInfo.getAdopterLivesWith())
                .addAnimalStaying(surveyInfo.getAnimalStaying())
                .build();

        setSurveyInfo(sb);
    }

    public void setLiveWith(boolean[] liveWith){
        SurveyInfo sb = new SurveyBuilder()
                .addAdopterEnergy(surveyInfo.getAdopterEnergy())
                .addAnimalAlone(surveyInfo.getAnimalAlone())
                .addEnrichment(surveyInfo.getAdopterEnrichment())
                .addTimeWith(surveyInfo.getAdopterSpendsTimeWith())
                .addLivesWith(liveWith)
                .addAnimalStaying(surveyInfo.getAnimalStaying())
                .build();

        setSurveyInfo(sb);
    }

    public void setTimeWith(boolean[] timeWith){
        SurveyInfo sb = new SurveyBuilder()
                .addAdopterEnergy(surveyInfo.getAdopterEnergy())
                .addAnimalAlone(surveyInfo.getAnimalAlone())
                .addEnrichment(surveyInfo.getAdopterEnrichment())
                .addTimeWith(timeWith)
                .addLivesWith(surveyInfo.getAdopterLivesWith())
                .addAnimalStaying(surveyInfo.getAnimalStaying())
                .build();

        setSurveyInfo(sb);
    }

    public void setEnrichment(boolean[] enrichment){
        SurveyInfo sb = new SurveyBuilder()
                .addAdopterEnergy(surveyInfo.getAdopterEnergy())
                .addAnimalAlone(surveyInfo.getAnimalAlone())
                .addEnrichment(enrichment)
                .addTimeWith(surveyInfo.getAdopterSpendsTimeWith())
                .addLivesWith(surveyInfo.getAdopterLivesWith())
                .addAnimalStaying(surveyInfo.getAnimalStaying())
                .build();

        setSurveyInfo(sb);
    }

    public void setCorrespondingUser(int user){
        correspondingUser = user;
    }

    public int getCorrespondingUser() {
        return correspondingUser;
    }
}
