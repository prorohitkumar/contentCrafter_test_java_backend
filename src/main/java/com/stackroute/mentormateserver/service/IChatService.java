package com.stackroute.mentormateserver.service;

import com.stackroute.mentormateserver.model.quickquiz.QuickQuiz;
import org.springframework.stereotype.Service;

@Service
public interface IChatService {
    String getQuizResponse(QuickQuiz quizData) throws Exception;
}
