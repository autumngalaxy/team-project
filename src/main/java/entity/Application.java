package entity;

import entity.application.SurveyBuilder;
import entity.application.SurveyInfo;
import org.json.JSONObject;

import java.time.LocalDate;

public class Application {
    public enum Status { PENDING, APPROVED, REJECTED }

    private final int id;
    private final int userId;
    private final int petId;
    private final LocalDate date;
    private Status status;
    private SurveyInfo surveyInfo;

    public Application(int applicationId, int userId, int petId, SurveyInfo surveyInfo) {
        this.id = applicationId;
        this.userId = userId;
        this.petId = petId;
        this.date = LocalDate.now();
        this.status = Status.PENDING;
        this.surveyInfo = surveyInfo;
    }

    public int getId() {
        return id;
    }

    public int getPetId() {
        return petId;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void approve() {
        this.status = Status.APPROVED;
    }

    public void reject() {
        this.status = Status.REJECTED;
    }

    public Application(JSONObject applicationJson) {
        this.id = applicationJson.getInt("id");
        this.userId = applicationJson.getInt("userId");
        this.petId = applicationJson.getInt("petId");
        this.date = LocalDate.parse(applicationJson.getString("date"));
        this.status = applicationJson.getEnum(Status.class, "status");
        this.surveyInfo = getJSONSurveyInfo(applicationJson.getJSONObject("survey_info"));
    }

    public SurveyInfo getJSONSurveyInfo(JSONObject jsonObject){
        if(jsonObject == null || jsonObject.isEmpty()){
            return SurveyBuilder.defaultSettings();
        }
        else {
            boolean[] timeWith = new boolean[8];
            boolean[] livesWith = new boolean[8];
            boolean[] enrichment = new boolean[5];

            for(int i = 0; i < timeWith.length; i++){
                timeWith[i] = jsonObject.getJSONArray("time_with").getBoolean(i);
            }
            for(int i = 0; i < livesWith.length; i++){
                livesWith[i] = jsonObject.getJSONArray("lives_with").getBoolean(i);
            }
            for(int i = 0; i < enrichment.length; i++){
                enrichment[i] = jsonObject.getJSONArray("enrichment").getBoolean(i);
            }

            return new SurveyBuilder()
                    .addAnimalAlone(SurveyInfo.AloneTime.valueOf(jsonObject.getString("alone_time")))
                    .addAnimalStaying(SurveyInfo.AnimalStaying.valueOf(jsonObject.getString("animal_staying")))
                    .addAdopterEnergy(SurveyInfo.EnergyOfHome.valueOf(jsonObject.getString("energy_of_home")))
                    .addTimeWith(timeWith)
                    .addLivesWith(livesWith)
                    .addEnrichment(enrichment)
                    .build();
        }
    }

    public JSONObject toJson() {
        JSONObject applicationJson = new JSONObject();

        applicationJson.put("id", id);
        applicationJson.put("userId", userId);
        applicationJson.put("petId", petId);
        applicationJson.put("date", date);
        applicationJson.put("status", status.toString());
        applicationJson.put("survey_info", surveyInfo.toJSON());
        return applicationJson;
    }
}