package edu.brown.cs.student.main.API;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * a class that deals with generating requests to be used by the client class
 * Reference:
 *  https://docs.oracle.com/en/database/oracle/oracle-database/
 *  http/java/net/http/HttpRequest.html
 *  https://docs.oracle.com/en/java/javase/11/docs/api/java.net.
 */

public class ReqGenerator {

    /**
     * Method that creates HTTP request to get active endpoints
     * @param reqUri
     * @return
     *          HttpRequest
     *
     */
    public static HttpRequest getActive(String reqUri) {
        return HttpRequest.newBuilder().uri(URI.create(reqUri)).build();
    }

    /**
     * Method that makes a Get request
     * @param
     *          url
     * @param
     *          params
     * @return
     *          HttpRequest
     * FORMAT: key=<key>;auth=<cslogin> //
     * FORMAT: auth=<cslogin>;key=<key>
     * Method referenced from: project-1-ihuang8-jjia6-mhe36
     */
    public static HttpRequest makeGetRequest(String url, String params) {
        StringBuilder urlParams = new StringBuilder("?");
        String[] parameters = params.split(";");
        for (String param : parameters) {
            urlParams.append(param);
            urlParams.append("&");
        }
        urlParams.deleteCharAt(urlParams.length() - 1);
        String reqUri = url + urlParams;
        return HttpRequest.newBuilder().uri(URI.create(reqUri)).build();
    }

    /**
     * Method that makes a Get request
     * @param
     *      url
     * @param
     *      params
     * @return
     *      HttpRequest
     * Method referenced from: project-1-ihuang8-jjia6-mhe36
     */
    public static HttpRequest makePostRequest(String url, String params) {
        String[] parameters = params.split(";");
        String csLogin = null;
        String apiKey = null;
        for (String param : parameters) {
            String[] paramParsed = param.split("=");
            if (paramParsed[0].equals("key")) {
                apiKey = paramParsed[1];
            } else if (paramParsed[0].equals("auth")) {
                csLogin = paramParsed[1];
            }
        }
        return HttpRequest.newBuilder().uri(URI.create(url))
                .header("x-api-key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString("{\"auth\":\"" + csLogin + "\"}"))
                .build();
    }

}
