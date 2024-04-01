package com.stackroute.mentormateserver.model.assessment;

import java.util.List;

/**
 * @author Priyanshu Singh
 */

public class AssessmentCreatorRequestBody {
    private List<SingleEntry> entries;

    public List<SingleEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<SingleEntry> entries) {
        this.entries = entries;
    }
}
