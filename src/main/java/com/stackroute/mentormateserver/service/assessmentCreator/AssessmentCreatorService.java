package com.stackroute.mentormateserver.service.assessmentCreator;

import com.stackroute.mentormateserver.model.assessment.AssessmentCreatorRequestBody;
import org.springframework.stereotype.Service;

/**
 * @author Priyanshu Singh
 */
@Service
public interface AssessmentCreatorService {
    String getAssessmentResponse(AssessmentCreatorRequestBody assessmentModel) throws Exception;
}
