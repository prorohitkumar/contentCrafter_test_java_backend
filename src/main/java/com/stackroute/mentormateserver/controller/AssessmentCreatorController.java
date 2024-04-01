package com.stackroute.mentormateserver.controller;

import com.stackroute.mentormateserver.model.quickquiz.SerRes;
import com.stackroute.mentormateserver.model.assessment.AssessmentCreatorRequestBody;
import com.stackroute.mentormateserver.service.assessmentCreator.AssessmentCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Priyanshu Singh
 */
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/ai")
public class AssessmentCreatorController {

    @Autowired
    private AssessmentCreatorService assessmentCreatorService;

    @PostMapping("/createAssessment")
    public ResponseEntity<?> chat(@RequestBody AssessmentCreatorRequestBody assessmentCreatorRequestBody) {
        SerRes response = new SerRes();
        String answer;
        try{
            answer = assessmentCreatorService.getAssessmentResponse(assessmentCreatorRequestBody);
            response.setAnswer(answer);
        } catch (Exception exc) {
            System.out.println("Error in controller:"+exc.getMessage());
            response.setAnswer("Not able to process the query, Please try again later!!");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
