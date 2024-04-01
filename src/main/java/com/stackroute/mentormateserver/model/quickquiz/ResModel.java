package com.stackroute.mentormateserver.model.quickquiz;

import java.util.List;
import java.util.Objects;

public class ResModel {

    private List<Candidates> candidates;

    private PromptFeedback promptFeedback;

    public ResModel() {
    }

    public ResModel(List<Candidates> candidates, PromptFeedback promptFeedback) {
        this.candidates = candidates;
        this.promptFeedback = promptFeedback;
    }

    public List<Candidates> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidates> candidates) {
        this.candidates = candidates;
    }

    public PromptFeedback getPromptFeedback() {
        return promptFeedback;
    }

    public void setPromptFeedback(PromptFeedback promptFeedback) {
        this.promptFeedback = promptFeedback;
    }
}
