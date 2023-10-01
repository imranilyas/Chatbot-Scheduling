package com.application.chatbot.chat;

public class ChatbotAttributes {
    /*
        "id":  int, -> continue where index left off, if array size = 6, index is 5
        "chatbotResponse": "ChatBot's output to the console",
        "movingForward": true, -> let's developer know if user's response will lead to another output from chatbot
        "expectedResponses": [{"input":  String, "leadsTo":  index in JSON Array}...]
    */

    public int id;
    public String chatbotResponse;
    public boolean movingForward;
    public Object[] expectedResponses;
}
