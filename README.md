# chatbot-sample

This exercise is to help us determine your ability to write quality, self-documenting code, pay attention to edge cases, and follow requirements. Your recruiter will let you know the deadline to submit your project from when we provide you access to this repository. It also checks your ability to use Git...

INSTRUCTIONS
1. Clone this (mostly) empty repository
2. Create a branch with the pattern AUS_FirstName_LastName
3. Write your project, be sure to include unit tests! Please include any database sql files (including DDLs for any tables/views/functions/etc that you necessary to run your code)
4. When finished create a pull request against the main branch, do not merge. Send your recruiter the link to your PR.

REQUIREMENTS

Create a simple chat bot. 

The goal of this exercise is create a simple chatbot that interacts with the user, asks questions and process responses. Based on the response, the chatbot should perform an action. The chatbot should be able to handle erroneous responses by clarifying and restating the original question. 
For the purpose of this exercise, the chat can occur completely in the console window, bonus points if the chat occurs in a form window. 
The task is to implement the following chat workflow in the attached pdf file.

Each conversation node is expecting a particular data type – Boolean, String, etc. Based upon the response, the chatbot should respond according to the document. Please note that all nodes expecting a response should be able to hand bad responses and respond appropriately. Also, please find a way to implement a common "INVALID RESPONSE" handler that will let the end user know that their response wasn't understood and redirect back to the associated request.

For each action, log the response (this is to simulate taking some action beyond continuing the chat)

We will be evaluating your project on adherence to requirements, data structures, and clean code. The most elegant solutions will focus on the data structure that supports a configurable conversation, that is, the actual text of a conversation node should be configurable via a text file, database record etc. Also, your solution should be able handle/support changing the flow of a conversation. Not a requirement for this implementation – but be able to explain how to add/remove/modify conversation nodes without requiring code changes. 

Be sure to include unit tests...(sigh)

Please use Java to implement the solution as the role will be heavily using Java on the job….

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
- UI, right now it runs entirely on the console but it would be more appealing if there was a UI to go along with it
