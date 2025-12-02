package entity.application;

/**
 * An entity that builds Survey Info entities. Requires adding desired info using methods, then calling build().
 */
public class SurveyBuilder {
    SurveyInfo survey;

    public SurveyBuilder(){
        survey = new SurveyInfo();
    }

    public SurveyInfo build(){ return survey; }

    /* ADDING INFORMATION */
    public SurveyBuilder addAdopterEnergy(SurveyInfo.EnergyOfHome energy){
        survey.setAdopterEnergy(energy);
        return this;
    }

    public SurveyBuilder addAnimalStaying(SurveyInfo.AnimalStaying staying){
        survey.setAnimalStaying(staying);
        return this;
    }

    public SurveyBuilder addAnimalAlone(SurveyInfo.AloneTime aloneTime){
        survey.setAnimalAlone(aloneTime);
        return this;
    }

    public SurveyBuilder addLivesWith(boolean[] livesWith){
        survey.setAdopterLivesWith(livesWith);
        return this;
    }

    public SurveyBuilder addTimeWith(boolean[] timeWith){
        survey.setAdopterSpendsTimeWith(timeWith);
        return this;
    }

    public SurveyBuilder addEnrichment(boolean[] enrichment){
        survey.setAdopterEnrichment(enrichment);
        return this;
    }

    public static SurveyInfo defaultSettings(){
        return new SurveyBuilder().addAdopterEnergy(SurveyInfo.EnergyOfHome.QUIET)
                .addAnimalStaying(SurveyInfo.AnimalStaying.LOOSE)
                .addAnimalAlone(SurveyInfo.AloneTime.MORE_THAN_TEN)
                .addLivesWith(new boolean[8])
                .addTimeWith(new boolean[8])
                .addEnrichment(new boolean[5])
                .build();

    }

}
