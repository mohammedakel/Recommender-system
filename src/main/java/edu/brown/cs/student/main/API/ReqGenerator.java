package edu.brown.cs.student.main.API;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * a class that deals with generating requests to be used by the client class
 * Citations:
 *  cs0320-s2022/project-1-avonderg-bpiekarz-kku2
 *  cs0320-s2022/project-1-ihuang8-jjia6-mhe36
 *  https://docs.oracle.com/en/database/oracle/oracle-database/
 *  http/java/net/http/HttpRequest.html
 *  https://docs.oracle.com/en/java/javase/11/docs/api/java.net.
 */

public class ReqGenerator {
    /**
     * The basic introductory GET request. Calls the server at the given URL.
     *
     * @return
     *          an HttpRequest object for accessing the introductory resource.
     */
    public static HttpRequest getIntroGetRequest(String reqUri) {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(reqUri)).build();
        return req;
    }

    /**
     * This method gets a GET request that is restricted to api key holders only.
     *
     * @param
     *          requestUri the given uri request
     * @return
     *          an HttpRequest object for accessing the secured resource.
     */
    public static HttpRequest getSecuredGetRequest(String requestUri) {
        String apiKey = ClientAuth.getApiKey();
        return HttpRequest.newBuilder(URI.create(requestUri))
                .GET()
                .header("apikey.txt", apiKey)
                .build();
    }

    /**
     * This method gets a POST request that is restricted to api key holders only.
     *
     * @param requestUri
     *          the given uri request
     * @param param
     *           the parameter
     * @return
     *            an HttpRequest object for accessing the introductory resource.
     */
    public static HttpRequest getSecuredPostRequest(String requestUri, String param) {
        String apiKey = ClientAuth.getApiKey();
        return HttpRequest.newBuilder(URI.create(requestUri)).GET().header("apikey.txt", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"" + param + "\"}")).build();
    }

    public static HttpRequest getActive(String reqUri) {
        return HttpRequest.newBuilder().uri(URI.create(reqUri)).build();
    }

    /**
     * Method that makes a Get request
     * @param url
     * @param params
     * @return HttpRequest
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
        System.out.println(reqUri);
        return HttpRequest.newBuilder().uri(URI.create(reqUri)).build();
    }

    /**
     * Method that makes a Get request
     * @param url
     * @param params
     * @return HttpRequest
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
