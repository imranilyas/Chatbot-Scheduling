package com.application.chatbot.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatResponseTree {

    public List<String> log = new ArrayList<>();

    private JSONArray responseArray;
    private int invalidCount = 0;
    private int terminationNumber = 5;

    public void setResponseArray(JSONArray responseArray) {
        this.responseArray = responseArray;
    }

    public JSONObject returnSingleObject(int index) {
        JSONObject obj = (JSONObject) responseArray.get(index);
        return obj;
    }

    public void chatResponses(int index) {
        // Grabs Current Object chat is on and converts it to a more accessible class
        JSONObject obj = returnSingleObject(index);
        ChatbotAttributes chat = convertJSONToClass(obj);

        // Chatbot output and user input if necessary
        System.out.println(chat.chatbotResponse);
        log.add("Chatbot: " + chat.chatbotResponse);

        // No followup for user response results in termination of program
        if(!chat.movingForward) {
            return;
        }

        UserInput userInput = new UserInput();
        String userResponse = userInput.getUserInput();
        log.add("User: " + userResponse);

        // Check if response is valid
        int nextIndex = userInputValid(chat.expectedResponses, userResponse);

        if(nextIndex == -1) {
            invalidResponseHandler(index);
        } else {
            //  Recursively call the method with the new index to run through the next chatbot scenario
            chatResponses(nextIndex);
        }

    }

    // Converts json object into a class for easier access of elements
    public ChatbotAttributes convertJSONToClass(JSONObject obj) {
        ObjectMapper mapper = new ObjectMapper();
        ChatbotAttributes ca = new ChatbotAttributes();
        try {
            ca = mapper.readValue(obj.toJSONString(), ChatbotAttributes.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ca;
    }

    // Check user response against expected responses
    public int userInputValid(Object[] obj, String actualResponse) {
        for(int i = 0; i < obj.length; i++) {
            // input -> expected user response, leadsTo -> index of where to navigate next in the JSON array
            // Chose HashMap due to the Object[] being recognized as a LinkedHashMap resulting in typecasting issues
            HashMap<String, Integer> map = (HashMap<String, Integer>) obj[i];
            String expectedResponse = String.valueOf(map.get("input"));
            if(actualResponse.equalsIgnoreCase(expectedResponse)) {
                int nextIndex = map.get("leadsTo");
                return nextIndex;
            }
        }
        // For triggering the invalidResponseHandler
        return -1;
    }

    // Invalid Handler Routes back with Original index
    public void invalidResponseHandler(int index) {
        String invalid = "I'm sorry, I didn't understand your response";
        System.out.println(invalid);
        // Add to the log
        log.add("Chatbot: " + invalid);

        // End program to prevent user abuse / multiple invalid requests
        invalidCount++;
        if(invalidCount >= terminationNumber) {
            String terminate = "The maximum number of attempts has been reached. Thank you for using ChatBot. Try again later.";
            System.out.println(terminate);
            log.add("Chatbot: " + terminate);
            return;
        }
        chatResponses(index);
    }
}
