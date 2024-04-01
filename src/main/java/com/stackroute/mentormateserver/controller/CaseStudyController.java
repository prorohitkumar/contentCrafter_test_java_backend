package com.stackroute.mentormateserver.controller;

import com.stackroute.mentormateserver.model.quickquiz.CaseStudy;
import com.stackroute.mentormateserver.model.quickquiz.SerRes;
import com.stackroute.mentormateserver.service.ICaseStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/ai")
public class CaseStudyController {

    @Autowired
    ICaseStudyService caseStudyService;

    @PostMapping("/create/case-study")
    public ResponseEntity<?> chat(@RequestBody CaseStudy caseStudy) {
        SerRes response = new SerRes();
        String answer;
        try {
            answer = caseStudyService.getCaseStudyResponse(caseStudy);
            response.setAnswer(answer);
        } catch (Exception exc) {
            System.out.println("Error in controller:" + exc.getMessage());
            response.setAnswer("Not able to process the query, Please try again later!!");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
