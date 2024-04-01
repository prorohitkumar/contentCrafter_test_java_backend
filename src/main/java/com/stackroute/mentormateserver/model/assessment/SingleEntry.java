package com.stackroute.mentormateserver.model.assessment;

import java.util.List;

/**
 * @author Priyanshu Singh
 */

public class SingleEntry {

    private String programmingLanguage;
    private String specializations;
    private List<String> concepts;
    private List<String> toolsTechnologies;
    private String level;
    private int noOfQuestions;

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getSpecializations() {
        return specializations;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }

    public List<String> getConcepts() {
        return concepts;
    }

    public void setConcepts(List<String> concepts) {
        this.concepts = concepts;
    }


    public List<String> getToolsTechnologies() {
        return toolsTechnologies;
    }

    public void setToolsTechnologies(List<String> toolsTechnologies) {
        this.toolsTechnologies = toolsTechnologies;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }
}
