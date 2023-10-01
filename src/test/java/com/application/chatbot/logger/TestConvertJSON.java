package com.application.chatbot.logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TestConvertJSON {
    ConvertJSON wf = new ConvertJSON();

    @Test
    void checkForNoExceptionThrown() {
        List<String> list = new ArrayList<>();
        list.add("ChatBot: Hello");
        wf.writeToJSON("13:12:00", list, "13:12:27");
        Assertions.assertDoesNotThrow(ConvertJSON::new);
    }
}
