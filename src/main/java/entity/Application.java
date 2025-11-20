package entity;

import java.time.LocalDate;

public class Application {
    public enum Status { PENDING, APPROVED, REJECTED }

    private int id;
    private AdopterInfo adopter;
    private Status status;
    private int petId;
    private LocalDate date;
    private SurveyInfo surveyInfo;

    public Application(int applicationId, AdopterInfo adopter, int petId, SurveyInfo surveyInfo) {
        this.id = applicationId;
        this.adopter = adopter;
        this.status = Status.PENDING;
        this.petId = petId;
        this.date = LocalDate.now();
        this.surveyInfo = surveyInfo;
    }

    public int getId() {
        return id;
    }

    public int getPetId() {
        return petId;
    }

    public AdopterInfo getAdopter() {
        return adopter;
    }

    public SurveyInfo getSurveyInfo() {
        return surveyInfo;
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
}