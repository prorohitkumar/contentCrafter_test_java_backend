package com.stackroute.mentormateserver.model.quickquiz;

import java.util.List;

public class Candidates {
    private ResContent content;
    private String finishReason;
    private Integer index;
    private List<SafetyRatings> safetyRatings;

    public Candidates() {
    }

    public Candidates(ResContent content, String finishReason, Integer index, List<SafetyRatings> safetyRatings) {
        this.content = content;
        this.finishReason = finishReason;
        this.index = index;
        this.safetyRatings = safetyRatings;
    }

    public ResContent getContent() {
        return content;
    }

    public void setContent(ResContent content) {
        this.content = content;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<SafetyRatings> getSafetyRatings() {
        return safetyRatings;
    }

    public void setSafetyRatings(List<SafetyRatings> safetyRatings) {
        this.safetyRatings = safetyRatings;
    }
}

class SafetyRatings {

    private String category;

    private String probability;
}
