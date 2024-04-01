package com.stackroute.mentormateserver.service;

import com.stackroute.mentormateserver.model.quickquiz.CaseStudy;

public interface ICaseStudyService {
    String getCaseStudyResponse(CaseStudy caseStudy) throws Exception;
}
