package com.stackroute.mentormateserver.model.quickquiz;

import java.util.List;

public class CaseStudy {
    private String sector;
    private String vertical;
    private String otherVertical;
    private String specialization;
    private String otherspecialization;

    private String caseStudyName;
    private List<String> learningObjectives;
    private List<String> technologies;
    private String difficulty;
    private int duration;
    private int numOfDevs;
    private String scenarioDescription;

    public CaseStudy() {
    }

    public CaseStudy(String sector, String vertical,String otherVertical ,String specialization,String otherspecialization ,String caseStudyName, List<String> learningObjectives, List<String> technologies, String difficulty, int duration, int numOfDevs, String scenarioDescription) {
        this.sector = sector;
        this.vertical = vertical;
        this.otherVertical = otherVertical;
        this.specialization = specialization;
        this.otherspecialization = otherspecialization;
        this.caseStudyName = caseStudyName;
        this.learningObjectives = learningObjectives;
        this.technologies = technologies;
        this.difficulty = difficulty;
        this.duration = duration;
        this.numOfDevs = numOfDevs;
        this.scenarioDescription = scenarioDescription;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public String getOtherVertical() {
        return otherVertical;
    }

    public void setOtherVertical(String otherVertical) {
        this.otherVertical = otherVertical;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getOtherspecialization() {
        return otherspecialization;
    }

    public void setOtherspecialization(String otherspecialization) {
        this.otherspecialization = otherspecialization;
    }

    public String getCaseStudyName() {
        return caseStudyName;
    }

    public void setCaseStudyName(String caseStudyName) {
        this.caseStudyName = caseStudyName;
    }

    public List<String> getLearningObjectives() {
        return learningObjectives;
    }

    public void setLearningObjectives(List<String> learningObjectives) {
        this.learningObjectives = learningObjectives;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumOfDevs() {
        return numOfDevs;
    }

    public void setNumOfDevs(int numOfDevs) {
        this.numOfDevs = numOfDevs;
    }

    public String getScenarioDescription() {
        return scenarioDescription;
    }

    public void setScenarioDescription(String scenarioDescription) {
        this.scenarioDescription = scenarioDescription;
    }

    @Override
    public String toString() {
        return "CaseStudy{" +
                "sector='" + sector + '\'' +
                ", vertical='" + vertical + '\'' +
                ", otherVertical='" + otherVertical + '\'' +
                ", specialization='" + specialization + '\'' +
                ", otherspecialization='" + otherspecialization + '\'' +
                ", caseStudyName='" + caseStudyName + '\'' +
                ", learningObjectives=" + learningObjectives +
                ", technologies=" + technologies +
                ", difficulty='" + difficulty + '\'' +
                ", duration=" + duration +
                ", numOfDevs=" + numOfDevs +
                ", scenarioDescription=" + scenarioDescription +
                '}';
    }
}
