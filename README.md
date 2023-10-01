# Chatbot Scheduling

## Brief Walkthrough
ChatBot is a program designed to listen and respond to user input based on its own prebuilt responses. This specific iteration of the project reads from the responses.json file and runs through it starting at the first element in the JSON Array. Based on the user's input, it will respond in-kind. If the user input does not match any of the expected responses, then ChatBot will recycle the original question and query for the user's input. Other than invalid responses, there are two types of "appropriate" potential responses. If the expected response does not lead to another question, ChatBot will output that final string and the program will end. If the expected promise does however move forward, then that specific response points to the index in the JSON Array with which to continue the cycle.

After the interaction ends, everything that was input and output to the console is written to the log.json file.

## Installation Instructions
This project was built with IntelliJ IDE 2021. Attempting to run the chatbot on Eclipse may prove more difficult. Install <a href="https://www.jetbrains.com/idea/download/?fromIDE=&section=windows" target="_blank">IntelliJ</a> here. Upon installation, you may git clone the repository and open the project in the IDE.

## Run Instructions
The ouput of the program is placed in a log.json in the project root. You will not see this until you run the program since the file is under .gitignore. The program will create said file if it doesn't already exist and write the log of the chatbot interaction there.

## Dependencies
- **json.simple**: used for parsing / creating JSON Objects and Arrays
- **Jackson API**: more specifically the *ObjectMapper*, used to map the JSON Object to a class for easier accessibility of the object's properties
- **Junit & Mockito**: testing purposes

## responses.json Structure
```JAVA
[{
  "id":  0,
  "chatbotResponse": "Hi! This is LISA. I have a great shift opportunity for you! Are you interested in hearing about it?\nPlease respond \"Yes\" or \"No\"",
  "movingForward": true,
  "expectedResponses": [{"input":  "Yes", "leadsTo":  2},{"input":  "No", "leadsTo": 1}]
}, ...]
```

## Project Difficulties
1. Accessing the Expected Responses from a JSON Object
The expectedResponses, when combined with ObjectMapper get mapped to Object[]. I wanted to convert said array of objects into a List<Map.Entry<String, Integer>>. This and other data structures would be met with a type casting error: *class java.util.LinkedHashMap cannot be cast to class...*.

SOLUTION: Use HashMap<String, Integer> and extract values from there.

2. Unit Testing involving Mocking Classes
Initially, I had the wrong version of Mockito which disabled my ability to use the verify() method, which was solved.
The more pressing issue is that I would experience unit tests working individually, but when run together with other tests, would fail. Also, assertThrows will not show if anything is thrown unless throws is in the method header. I opted for try, catch blocks. The proposed solution for that is to use an older version of the @Test(expected = JsonProcessingException.class) which in turn gave me new errors.

## Future Implementations
- More tests
- Adding / Removing from the responses.json without direct contact, (removal could prove to be tricky)
- UI - Right now it runs entirely on the console, but it would be more appealing if there was a UI to go along with it
