package com.stackroute.mentormateserver.model.quickquiz;

import java.util.List;

public class ResContent {

    private List<ResParts> parts;
    private String role;

    public ResContent() {
    }

    public ResContent(List<ResParts> parts, String role) {
        this.parts = parts;
        this.role = role;
    }

    public List<ResParts> getParts() {
        return parts;
    }

    public void setParts(List<ResParts> parts) {
        this.parts = parts;
    }
}
