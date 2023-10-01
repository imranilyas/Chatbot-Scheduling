package com.application.chatbot.chat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestUserInput {
    @Test
    void shouldReceiveUserInput() {
        UserInput ui = new UserInput();
        String input = "yes";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Assertions.assertEquals("yes", ui.getUserInput());
    }

    @Test
    void inputShouldBeTwenty() {
        UserInput ui = new UserInput();
        String input = "i am trying to spam this bot with irrelevant messages but it is not working";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Assertions.assertEquals("i am trying to spam ", ui.getUserInput());
    }
}
