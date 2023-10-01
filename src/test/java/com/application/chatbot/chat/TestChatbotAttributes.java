package com.application.chatbot.chat;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestChatbotAttributes {
    @Mock
    JSONObject json = new JSONObject();

    @Test
    void testConvertJSONtoClass() {
        ChatResponseTree ct = Mockito.mock(ChatResponseTree.class);
        ChatbotAttributes ca = new ChatbotAttributes();
        Mockito.when(ct.convertJSONToClass(json)).thenReturn(ca);
    }
}
