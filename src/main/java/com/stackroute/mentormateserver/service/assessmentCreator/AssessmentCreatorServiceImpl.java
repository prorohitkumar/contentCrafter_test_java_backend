package com.stackroute.mentormateserver.service.assessmentCreator;

import com.stackroute.mentormateserver.model.quickquiz.ResModel;
import com.stackroute.mentormateserver.model.assessment.AssessmentCreatorRequestBody;
import com.stackroute.mentormateserver.model.assessment.SingleEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Priyanshu Singh
 */
@Service
public class AssessmentCreatorServiceImpl implements AssessmentCreatorService{

    @Value("${api-key}")
    private String apiKey;

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();


    @Override
    public String getAssessmentResponse(AssessmentCreatorRequestBody assessmentModel) throws Exception {

        StringBuilder result = new StringBuilder();

        for(SingleEntry entry : assessmentModel.getEntries()) {
            if (entry.getNoOfQuestions() <= 40) {
                String prompt = getPrompt(entry);
                String res = getResult(prompt);
                System.out.println(res);
                result.append(res);
                result.append(("\n\n"));
            } else {
                int totalQuestions = entry.getNoOfQuestions();
                int questionsPerCall = 25;  // Maximum questions per API call (adjust as needed)
                while (totalQuestions > 0) {
                    int questionsThisTime = Math.min(questionsPerCall, totalQuestions); // Get minimum of desired and remaining questions
                    SingleEntry tempEntry = entry; // Create a copy to avoid modifying original entry
                    tempEntry.setNoOfQuestions(questionsThisTime); // Set number of questions for this call
                    String prompt = getPrompt(tempEntry);
                    String res = getResult(prompt);
                    System.out.println(res);
                    result.append(res);
                    result.append("\n\n"); // Add a newline after each batch of questions
                    totalQuestions -= questionsThisTime; // Update remaining questions
                }

                }
            }


        System.out.println(result.toString());
        return result.toString();
    }
    public String getPrompt(SingleEntry entry){
        String prompt = "";

        prompt += "\n- Create " + entry.getNoOfQuestions() + " " + entry.getLevel() + " level MCQ-based questions along with answers";

        // Handle potentially missing programming language
        if (entry.getProgrammingLanguage() != null && !entry.getProgrammingLanguage().isEmpty()) {
            prompt += " in " + entry.getProgrammingLanguage();

            // Handle potentially missing specialization
            if (entry.getSpecializations() != null && !entry.getSpecializations().isEmpty()) {
                prompt += " (" + entry.getSpecializations() + " specialization)";
            }
        }

        prompt += " for the following concepts:\n";

        // Add concepts, ensuring commas for better separation
        for (int i = 0; i < entry.getConcepts().size(); i++) {
            prompt += entry.getConcepts().get(i);
            if (i < entry.getConcepts().size() - 1) {
                prompt += ", ";
            }
        }

        // Handle potentially missing tools and technologies
        if (entry.getToolsTechnologies() != null && !entry.getToolsTechnologies().isEmpty()) {
            prompt += "\n- Tools and technologies: ";
            // Add tools and technology
            for (int i = 0; i < entry.getToolsTechnologies().size(); i++) {
                prompt += entry.getToolsTechnologies().get(i);
                if (i < entry.getToolsTechnologies().size() - 1) {
                    prompt += ", ";
                }
            }
        }

        prompt += "\n\nMake sure to return " + entry.getNoOfQuestions() + " questions\n\n";

        return prompt;
    }





    public  String getResult(String prompt) throws Exception {
        try {
            // URL
            String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + apiKey;
            //
            headers.setContentType(MediaType.APPLICATION_JSON);

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
                "Format: \\n\\n **Question 1"+
                "\\n\\nA. Option 1"+
                "\\n\\nB. Option 2"+
                "\\n\\nC. Option 3"+
                "\\n\\nD. Option 4"+
                "\\n\\n**Answer: A. Option 1 \n\n no need to separate questions topic wise and mentioned the topic";
            String requestBody = "{'contents':[{'parts':[{'text':'"+prompt+""+exampleFormat+"'}]}], 'generationConfig':"+generationConfig+",'safetySettings':"+safetySettings+"}";

            System.out.println("Request Body:"+requestBody);
            // Make Gemini API call
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<ResModel> response = restTemplate.postForEntity(apiUrl, entity, ResModel.class);
//            System.out.println(response.getBody().toString());
            String answer = response.getBody().getCandidates().get(0).getContent().getParts().get(0).getText();
            return answer;
        } catch (Exception e) {
            System.out.println(" Service Exception:"+e.getMessage());
            throw new Exception("Error in getting response from Gemini api");
        }
    }



    public static String generatePrompt(AssessmentCreatorRequestBody requestBody) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("Please create a multiple-choice question (MCQ) assessment with following specifications given below. ");
        promptBuilder.append("Ensure the questions are clear, concise, and appropriate for the specified level.\n\n");
        promptBuilder.append("Create MCQ assessment based on following requirements\n");

        int i=1;
        for (SingleEntry entry : requestBody.getEntries()) {
            promptBuilder.append(String.format(i+". give mcq question for  %s "+"for "+entry.getSpecializations()+" \n", entry.getProgrammingLanguage())); // Assuming one language per entry for simplicity
            promptBuilder.append("provide exact "+entry.getNoOfQuestions()+" "+entry.getLevel()+ " level Questions: ").append("");

//           promptBuilder.append("   - level: ").append(entry.getLevel()).append("\n");
            promptBuilder.append("   - Key Concepts to Cover:");
            for (String concept : entry.getConcepts()) {
                promptBuilder.append("- ").append(concept).append("");
            }
            promptBuilder.append("   & Tools and Technologies :");
            for (String tool : entry.getToolsTechnologies()) {
                promptBuilder.append("- ").append(tool).append("");
            }
            promptBuilder.append("   - Instructions: ").append(String.format("Formulate questions that test basic understanding and application of %s in ,"+entry.getSpecializations()+" ", entry.getProgrammingLanguage())+"\n");
           i++;

        }
        promptBuilder.append("with a focus on the listed concepts. Incorporate scenarios or examples where the listed tools might be used in a development workflow.");
        promptBuilder.append("Provide all questions in one reply\n\n");
        promptBuilder.append("Make sure to return desired no of questions");

        return promptBuilder.toString();
    }
}
