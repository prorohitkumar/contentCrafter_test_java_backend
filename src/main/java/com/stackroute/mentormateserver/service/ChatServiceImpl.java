package com.stackroute.mentormateserver.service;

import com.stackroute.mentormateserver.model.quickquiz.MetaData;
import com.stackroute.mentormateserver.model.quickquiz.QuickQuiz;
import com.stackroute.mentormateserver.model.quickquiz.ResModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChatServiceImpl implements IChatService {
    @Value("${api-key}")
    private String apiKey;

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @Override
    public String getQuizResponse(QuickQuiz quizData) throws Exception {
        try {
            // URL
            String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + apiKey;

            // Set Headers
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Prompt Structuring
            String prompt = "You are a technical mentor preparing an MCQ question paper for pre-assessment targeting learners.";

            String concept = quizData.getConcept();
            List<MetaData> meta = quizData.getMeta();
            for (MetaData data: meta){
                prompt +="\\n- Create "+data.getNoOfQuestions()+" MCQ-based questions along with answers on "+concept+" concept with difficulty level as "+data.getDifficultyLevel()+".";
            }

            String generationConfig = "{" +
                    "'temperature':0.9," +
                    "'topK':1," +
                    "'topP':1," +
                    "'maxOutputTokens':2048," +
                    "'stopSequences':[]" +
                    "}";

            String safetySettings = "[" +
                    "{'category':'HARM_CATEGORY_HARASSMENT','threshold':'BLOCK_MEDIUM_AND_ABOVE'}," +
                    "{'category':'HARM_CATEGORY_HATE_SPEECH','threshold':'BLOCK_MEDIUM_AND_ABOVE'}," +
                    "{'category':'HARM_CATEGORY_SEXUALLY_EXPLICIT','threshold':'BLOCK_MEDIUM_AND_ABOVE'}," +
                    "{'category':'HARM_CATEGORY_DANGEROUS_CONTENT','threshold':'BLOCK_MEDIUM_AND_ABOVE'}" +
                    "]";

            String exampleFormat = "MCQ has to be in below format:"+
                    "Format: \\n\\n1. Question 1"+
                    "\\n\\nA. Option 1"+
                    "\\n\\nB. Option 2"+
                    "\\n\\nC. Option 3"+
                    "\\n\\nD. Option 4"+
                    "\\n\\nAnswer: A. Option 1";

            String requestBody = "{'contents':[{'parts':[{'text':'"+prompt+""+exampleFormat+"'}]}], 'generationConfig':"+generationConfig+",'safetySettings':"+safetySettings+"}";
            System.out.println("Request Body:"+requestBody);

            // Make Gemini API call
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<ResModel> response = restTemplate.postForEntity(apiUrl, entity, ResModel.class);
            String answer = response.getBody().getCandidates().get(0).getContent().getParts().get(0).getText();
            System.out.println(answer);
            return answer;

//            return "You have your answer";
        } catch (Exception e) {
            System.out.println(" Service Exception:"+e.getMessage());
            throw new Exception("Error in getting response from Gemini api");
        }
    }
}
