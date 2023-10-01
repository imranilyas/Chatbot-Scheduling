package com.application.chatbot;

import com.application.chatbot.chat.*;
import com.application.chatbot.logger.ConvertJSON;
import org.json.simple.JSONArray;

import java.util.Date;

public class ChatbotApplication {
    public static void main(String[] args) {
        // Grab the array of objects in responses.json
        ConvertJSON wf = new ConvertJSON();
        String path = wf.getResponsePath();
        JSONArray responseArray = wf.readFromJSON(path);

        // Exit in case responses have not been added to the json
        if(responseArray.isEmpty()) {
            System.out.println("Developer needs to add an object for the ChatBot to properly respond.");
            return;
        }

        String startTime = new Date().toString();

        ChatResponseTree chat = new ChatResponseTree();
        chat.setResponseArray(responseArray);
        // Feed in array with 0 for function to start at 0th element in array
        chat.chatResponses(0);

        String endTime = new Date().toString();
        // Add chatbot interaction to the log
        wf.writeToJSON(startTime, chat.log, endTime);
    }

}
