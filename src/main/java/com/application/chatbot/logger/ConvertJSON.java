package com.application.chatbot.logger;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;

// Class file handles JSON conversions, both reading and writing to and from file
public class ConvertJSON {
    private String path = "log.json";
    private String responsePath = "responses.json";

    public String getPath() {
        return path;
    }

    public String getResponsePath() {
        return responsePath;
    }

    public JSONArray readFromJSON(String path) {
        JSONArray arr = new JSONArray();
        JSONParser parse = new JSONParser();

        if(new File(path).exists()) {
            try {
                if (new File(path).length() > 0) {
                    FileReader file = new FileReader(path);
                    Object obj = parse.parse(file);
                    arr = (JSONArray) obj;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arr;
    }

    public void writeToJSON(String startTime, List<String> body, String endTime) {
        JSONObject json = new JSONObject();
        JSONArray arr = readFromJSON(path);

        json.put("startTime", startTime);
        json.put("body", body);
        json.put("endTime", endTime);
        arr.add(json);

        try(FileWriter write = new FileWriter(path)) {
            write.write(arr.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
