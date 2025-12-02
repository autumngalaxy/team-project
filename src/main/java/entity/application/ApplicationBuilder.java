package entity.application;

import entity.Pet;

/**
 * An entity that builds Adoption Application entities. Requires adding desired info using methods, then calling build().
 */
public class ApplicationBuilder {

    AdoptionApplication application;

    public ApplicationBuilder()
    {
        application = new AdoptionApplication();
    }

    public AdoptionApplication build() { return application; }

    /* ADDING INFO */
    public ApplicationBuilder addSurveyInfo(SurveyInfo survey)
    {
        application.setSurveyInfo(survey);
        return this;
    }

    public ApplicationBuilder addPet(Pet pet){
        application.setCorrespondingPet(pet);
        return this;
    }

    public ApplicationBuilder addName(String name){
        application.setAdopterName(name);
        return this;
    }

    public ApplicationBuilder addID(AdoptionApplication.IDType id){
        application.setIdType(id);
        return this;
    }

    public ApplicationBuilder addAddress(String[] address){
        application.setAdopterAddress(address);
        return this;
    }

    public ApplicationBuilder addPhone(String phoneNumber){
        application.setAdopterPhone(phoneNumber);
        return this;
    }

    public ApplicationBuilder addEmail(String email){
        application.setAdopterEmail(email);
        return this;
    }

    public ApplicationBuilder addDate(){
        application.setApplicationDate();
        return this;
    }

}
