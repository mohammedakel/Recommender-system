package edu.brown.cs.student.main.API;

import com.google.gson.Gson;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * JSONParser class
 * Syntax for storing data in student objects adapted from: project-1-avonderg-bpiekarz-kku2
 *
 */

public class JsonParser {

    /**
     *
     * class constructor
     *
     */
    public JsonParser(){}

    /**
     * Extracts the message parameter from a JSON object and stores it into a Message object.
     * Then, it prints the stored message in the Message object.
     *
     * @param
     *       jsonObject stores the message.
     */
    public static String printMessage(String jsonObject) {
        Gson parser = new Gson();
        String[] messages = parser.fromJson(jsonObject, String[].class);
        return Arrays.toString(messages);
    }

    /**
     * Method to parse and store all the student info extracted from info API
     * @param apiResponse
     *          - HttpResponse containing JSON student data
     * @return
     *          - a list of InfoStudents
     */
    public static List<InfoStudents> storeInfo(HttpResponse<String> apiResponse) {
        assert apiResponse.statusCode() == 200;
        Gson parser = new Gson();
        InfoStudents[] infoStudentsList = parser.fromJson(apiResponse.body(), InfoStudents[].class);
        return Arrays.asList(infoStudentsList);
    }



    /**
     * Method to parse and store all the student info extracted from Match API
     * @param apiResponse
     *          - HttpResponse containing JSON student data
     * @return
     *          - a list of MatchStudents
     */
    public static List<MatchStudents> storeMatch(HttpResponse<String> apiResponse) {
        assert apiResponse.statusCode() == 200;
        Gson parser = new Gson();
        MatchStudents[] matchStudentsList = parser.fromJson(apiResponse.body(), MatchStudents[].class);
        return Arrays.asList(matchStudentsList);
    }

}