package edu.brown.cs.student.main.API;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Class that handles API aggregation tasks including loading data, parsing Jsons and student lists
 *
 */
public class ApiAggregate {
    private String method = "";
    private Client client = new Client();
    public List<InfoStudents> getStudents= new ArrayList<InfoStudents>();
    public List<MatchStudents> postStudents = new ArrayList<MatchStudents>();;

    /**
     * A class constructor
     *
     */
    public ApiAggregate(){
    }

    /**
     * A method that aggregated data from the studentInfo APi. returns void but populates
     * and prints the student info list
     *
     */
    public void InfoAggregate() {
        String requestStr = "https://studentinfoapi.herokuapp.com";
        ReqGenerator generator = new ReqGenerator();
        System.out.println("Students: ");
        HttpResponse<String>  activeEndPoints = client.makeRequest(generator.getActive("https://studentinfoapi.herokuapp.com/get-active"));
        System.out.println("Current Active Endpoints Are: " + activeEndPoints);
        String endPoints = activeEndPoints.body();
        String[] endPointsClean = endPoints.substring(1, endPoints.length()-1).split(", ");
        for (String token : endPointsClean) {
            HttpResponse<String> response = null;
            requestStr += token.substring(1, token.length()-1); // append correct token to the URL
            String apiKey = ClientAuth.getApiKey();
            String parameters = "auth=makel;key=" + apiKey;
            response = client.makeRequest(generator.makeGetRequest(requestStr, parameters));
            if (response.statusCode() != 200) {
                response = client.makeRequest(generator.makeGetRequest(requestStr, parameters));
            }
            getStudents = JsonStudentsParser.storeInfo(response);
            for (InfoStudents student : getStudents) {
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
        ReqGenerator generator = new ReqGenerator();
        System.out.println("Students: ");
        HttpResponse<String>  activeEndPoints = client.makeRequest(generator.getActive("https://studentmatchapi.herokuapp.com/get-active"));
        String endPoints = activeEndPoints.body();
        String[] endPointsClean = endPoints.substring(1, endPoints.length()-1).split(", ");
        String baseString = "";
        for (String token : endPointsClean) {
            HttpRequest activeRequest = null;
            HttpResponse<String> response = null;
            baseString = requestStr;
            baseString +=  token.substring(1, token.length()-1);;
            System.out.println(baseString);
            String auth = "makel";
            String apiKey = ClientAuth.getApiKey();
            activeRequest = HttpRequest.newBuilder().uri(URI.create(baseString))
                    .header("x-api-key", apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString("{\"auth\":\"" + auth + "\"}"))
                    .build();
            response = client.makeRequest(activeRequest);
            if (response.statusCode() != 200) {
                response = client.makeRequest(activeRequest);
            }
            postStudents = JsonStudentsParser.storeMatch(response);
            for (MatchStudents student : postStudents) {
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
        else if (method.equals("all")) {
            this.InfoAggregate();
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
    public List<InfoStudents> getInfoStudents(){
        return this.getStudents;
    }

    /**
     * a getter for students obtained from match API
     * @return List<MatchStudents>
     * */
    public List<MatchStudents> getMatchStudents(){
        return this.postStudents;
    }

}
