package com.application.chatbot.chat;

import java.util.Scanner;

public class UserInput {

    // Max Length to prevent user input from being too verbose or abusive
    private int maxLength = 20;

    // Scanner for retrieving user input
    public String getUserInput() {
        Scanner scan = new Scanner(System.in);
        String res = scan.nextLine().trim();
        if(res.length() > 20) {
            res = res.substring(0, maxLength);
        }
        return res;
    }
}
