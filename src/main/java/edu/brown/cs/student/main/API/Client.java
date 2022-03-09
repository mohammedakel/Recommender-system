package edu.brown.cs.student.main.API;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * a generic class that handles the logistics of requests
 *
 */

public class Client {
    private HttpClient client;

    /**
     * a  constructor that initializes the client field
     */
    public Client() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(60))
                .build();
    }

    /**
     * a  method that handles generic requests
     * recursively calls the method until valid status code is obtaibed
     *
     * @param
     *      req HttpRequest to be processed
     * @return
     *      HttpResponse<String> response of the request
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws IllegalArgumentException
     * @throws SecurityException
     *
     */
    public HttpResponse<String> makeRequest(HttpRequest req) {
        HttpResponse<String> result = null;
        try {
            result = client.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }



}
