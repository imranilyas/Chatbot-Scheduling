package com.application.chatbot.chat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestChatResponseTree {
//    @AfterEach

    @Test
    void invalidHandlerAddsToLog() {
        ChatResponseTree ct = new ChatResponseTree() {
          @Override
          public void chatResponses(int index) {}
        };
        ct.invalidResponseHandler(0);
        Assertions.assertEquals("Chatbot: I'm sorry, I didn't understand your response", ct.log.get(0));
    }

    @Test
    void convertEmptyJSON() {
        ChatResponseTree ct = new ChatResponseTree();
        JSONObject json = new JSONObject();
        ChatbotAttributes ca = ct.convertJSONToClass(json);
        Assertions.assertAll("Empty JSON Conversion", () -> Assertions.assertEquals(0, ca.id), () -> Assertions.assertEquals(null, ca.chatbotResponse), () -> Assertions.assertEquals(false, ca.movingForward));
    }

    @Test
    void userInputValidMethodReturnsNegativeOne() {
        Object[] obj = anyList().toArray(new HashMap[0]);
        ChatResponseTree ct = new ChatResponseTree();
        int res = ct.userInputValid(obj, "Test String");
        Assertions.assertEquals(-1, res);
    }

    @Test
    void chatResponsesDoesNotMoveForward() {
        ChatResponseTree ct = new ChatResponseTree();
        JSONArray arr = new JSONArray();
        arr.add(new JSONObject());
        ct.setResponseArray(arr);
        Assertions.assertEquals(0, ct.log.size());
    }
}
