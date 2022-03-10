package edu.brown.cs.student.main.API;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Class that handles API aggregation tasks including loading data, parsing Jsons and student lists
 *
 */
public class ApiAggregate {
    private  String method = "";
    private  Client client;
    private  List<InfoStudents> getStudents;
    private  List<MatchStudents> postStudents;

    /**
     * A class constructor
     *
     */
    public ApiAggregate(){
        client = new Client();
        getStudents=  new ArrayList<InfoStudents>();
        postStudents =  new ArrayList<MatchStudents>();
    }

    /**
     * Parses input JSON string, removing items stored within quotation marks
     * @param parse - JSON string to parse
     * @return - List<String> of parsed JSON tokens
     * from: https://github.com/cs0320-s2022/project-1-avonderg-bpiekarz-kku2
     */
    private List<String> parseActive(String parse) {
        List<String> tokens = new ArrayList<>();
        Pattern p = Pattern.compile("\"([^\"]*)\""); // regex to find matches on
        Matcher m = p.matcher(parse);
        while (m.find()) {
            tokens.add(m.group(1));
        }
        return tokens;
    }

    /**
     * A method that aggregated data from the studentInfo APi. returns list of info students
     */

    public void InfoAggregate() {
        String infoAPI = "https://studentinfoapi.herokuapp.com";
        String infoAPIActive = "https://studentinfoapi.herokuapp.com/get-active";
        HttpResponse<String>  activeEndPoints = client.makeRequest(ReqGenerator.getActive(infoAPIActive));
        assert  activeEndPoints != null;
        System.out.println("Students: ");
        String endPoints = activeEndPoints.body();
        List<String> tokens = parseActive(endPoints);
        for (String token : tokens) {
            assert !Objects.equals(token, "");
            System.out.println(token);
            HttpResponse<String> response = null;
            String temp = infoAPI + token;
            String apiKey = ClientAuth.getApiKey();
            String parameters = "auth=makel;key=" + apiKey;
            response = client.makeRequest(ReqGenerator.makeGetRequest(temp, parameters));
            while (response.statusCode() != 200) {
                response = client.makeRequest(ReqGenerator.makeGetRequest(String.valueOf(infoAPI), parameters));
            }
            List<InfoStudents> getStudentsToken = JsonParser.storeInfo(response);
            for (InfoStudents student : getStudentsToken) {
                getStudents.add(student);
                System.out.println(student.convertToString());
            }
        }
    }

    /**
     * A method that aggregated data from the MatchInfo API. returns void but populates
     * and prints info for students from the match API
     *
     */
    public void MatchAggregate() {
        String requestStr = "https://studentmatchapi.herokuapp.com";
        String requestStrActive = "https://studentmatchapi.herokuapp.com/get-active";
        HttpResponse<String>  activeEndPoints = client.makeRequest(ReqGenerator.getActive(requestStrActive));
        assert  activeEndPoints != null;
        System.out.println("Students: ");
        String endPoints = activeEndPoints.body();
        List<String> tokens = parseActive(endPoints);
        for (String token : tokens) {
            System.out.println(token);
            HttpResponse<String> response = null;
            String temp = requestStr + token;
            String apiKey = ClientAuth.getApiKey();
            String parameters = "auth=makel;key=" + apiKey;
            response = client.makeRequest(ReqGenerator.makePostRequest(temp, parameters));
            while (response.statusCode() != 200) {
                response = client.makeRequest(ReqGenerator.makePostRequest(temp, parameters));
            }
            List<MatchStudents> postStudentsToken = JsonParser.storeMatch(response);
            for (MatchStudents student : postStudentsToken) {
                postStudents.add(student);
                System.out.println(student.convertToString());
            }
        }
    }

    /**
     * a method that handles the management of aggregation tasks.
     *
     * @param method
     *         an indicator of which API to use
     * */
    public void aggregate(String method) {
        if (method.equals("info")) {
            this.InfoAggregate();
        } else if (method.equals("match")) {
            this.MatchAggregate();
        }
        else {
            System.out.println("Error: Invalid method argument");
        }
    }

    /**
     * a getter for students obtained from info API
     * @return  List<InfoStudents>
     * */
    public  List<InfoStudents> getInfoStudents(){
        return this.getStudents;
    }

    /**
     * a getter for students obtained from match API
     * @return List<MatchStudents>
     * */
    public  List<MatchStudents> getMatchStudents(){
        return this.postStudents;
    }

}
