package com.stackroute.mentormateserver.model.quickquiz;

public class MetaData {

    private Integer numberOfQuestions;

    private String difficulty;

    public MetaData() {
    }

    public MetaData(Integer numberOfQuestions, String difficulty) {
        this.numberOfQuestions = numberOfQuestions;
        this.difficulty = difficulty;
    }

    public Integer getNoOfQuestions() {
        return numberOfQuestions;
    }

    public void setnumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getDifficultyLevel() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
