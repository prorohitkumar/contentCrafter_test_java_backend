package com.stackroute.mentormateserver.controller;

import com.stackroute.mentormateserver.model.quickquiz.QuickQuiz;
import com.stackroute.mentormateserver.model.quickquiz.SerRes;
import com.stackroute.mentormateserver.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/ai")
public class ChatController {

    @Autowired
    IChatService chatService;

    @GetMapping("/greet")
    public String greet() {
        return "Good Morning, Have a nice day";
    }

    @PostMapping("/create/quickQuiz")
    public ResponseEntity<?> chat(@RequestBody QuickQuiz quizData) {
        SerRes response = new SerRes();
        String answer;
        System.out.println(quizData.getConcept());
        System.out.println(quizData.getMeta().get(0));
        try{
            answer = chatService.getQuizResponse(quizData);
            response.setAnswer(answer);
        } catch (Exception exc) {
            System.out.println("Error in controller:"+exc.getMessage());
            response.setAnswer("Not able to process the query, Please try again later!!");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
