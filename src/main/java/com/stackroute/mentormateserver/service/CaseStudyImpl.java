package com.stackroute.mentormateserver.service;

import com.stackroute.mentormateserver.model.quickquiz.CaseStudy;
import com.stackroute.mentormateserver.model.quickquiz.ResModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CaseStudyImpl implements ICaseStudyService {
    @Value("${api-key}")
    private String apiKey;

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Override
    public String getCaseStudyResponse(CaseStudy caseStudy) throws Exception {


        System.out.println(caseStudy.getOtherspecialization());
        try {
            // URL
            String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + apiKey;

            // Set Headers
            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            String technologies = null;
//            if(caseStudy.getTechnologies() != null){
//                technologies = caseStudy.getTechnologies().stream().reduce((x, y) -> x + ", " + y).get();}


            if(Objects.equals(caseStudy.getVertical(), "Other")){
                caseStudy.setVertical(caseStudy.getOtherVertical());
            }else{
                caseStudy.setVertical(caseStudy.getVertical());
            }

            if(Objects.equals(caseStudy.getSpecialization(), "Other")){
                caseStudy.setSpecialization(caseStudy.getOtherspecialization());
            }else{
                caseStudy.setSpecialization(caseStudy.getSpecialization());
            }

            // Prompt Structuring
            String prompt = "You are a technical mentor preparing a case study for new software developers to work on. ";
            if(caseStudy.getNumOfDevs() != 0 && caseStudy.getDuration() != 0 && caseStudy.getVertical() != null && caseStudy.getSpecialization() != null && caseStudy.getTechnologies() != null && caseStudy.getLearningObjectives() != null) {
                String technologies = caseStudy.getTechnologies().stream().reduce((x, y) -> x + ", " + y).get();
                String learningObjectives = caseStudy.getLearningObjectives().stream().reduce((x, y) -> x + ", " + y).get();
                prompt += "\n  create a case study for a group of " + caseStudy.getNumOfDevs() + " software developers to work on for " + caseStudy.getDuration() + " hours. The case study should be related to " + caseStudy.getVertical() + " vertical with " + caseStudy.getSpecialization() + " specialization. The developers will use " + technologies + " as tools and technologies to build the project.Some of the learning objectives for the particpants should be - \n" + learningObjectives + ".";
            }else {
                prompt += "\n  create a case study for software developers. The case study should be related to " + caseStudy.getVertical() + " vertical with " + caseStudy.getSpecialization() + " specialization.";
            }


//            if (caseStudy.getLearningObjectives() != null) {
//                String learningObjectives = caseStudy.getLearningObjectives().stream().reduce((x, y) -> x + ", " + y).get();
//                prompt += "Some of the learning objectives for the particpants should be - \n" + learningObjectives + ".";
//            }
//            


            if (caseStudy.getScenarioDescription() != null) {
                prompt += "\n Here is a brief problem statement I have in my mind. You can use this as a starting point - \n " + caseStudy.getScenarioDescription() + ".";
            }

            prompt += "\n Also give the deliverables for the case study at the end.";
            System.out.println(prompt);

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

            String requestBody = "{'contents':[{'parts':[{'text':'" + prompt + "'}]}], 'generationConfig':" + generationConfig + ",'safetySettings':" + safetySettings + "}";
//            System.out.println("Request Body:" + requestBody);

            // Make Gemini API call
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<ResModel> response = restTemplate.postForEntity(apiUrl, entity, ResModel.class);
            String answer = response.getBody().getCandidates().get(0).getContent().getParts().get(0).getText();
            return answer;
        } catch (Exception e) {
            System.out.println(" Service Exception:" + e.getMessage());
            throw new Exception("Error in getting response from Gemini api");
        }
    }
}
