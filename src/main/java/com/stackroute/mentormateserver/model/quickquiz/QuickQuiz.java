package com.stackroute.mentormateserver.model.quickquiz;

import java.util.List;

public class QuickQuiz {

    private String concept;

    private List<MetaData> meta;

    public QuickQuiz() {
    }

    public QuickQuiz(String concept, List<MetaData> meta) {
        this.concept = concept;
        this.meta = meta;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public List<MetaData> getMeta() {
        return meta;
    }

    public void setMeta(List<MetaData> meta) {
        this.meta = meta;
    }
}
