package com.arktech.waqasansari.thescholarsinn.http_requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Linta Ansari on 6/27/2016.
 */
public class ApiCalls {
    private static ApiCalls apiCalls;
    private String BASE_URL = "http://api.thescholarsinn.com/api/values/";
    private HttpURLConnection urlConnection;
    private URL url;
    private OutputStream outputStream;
    private final String USER_AGENT = "Mozilla/5.0";

    public static ApiCalls initializeAPI(){
        if(apiCalls == null)
            apiCalls = new ApiCalls();
        return apiCalls;
    }

    //******************************Class Methods***************************************************
    //Close connection
    private void done() throws IOException {
        urlConnection.disconnect();
        outputStream.close();
    }

    //Open Connection
    private void prepareConnection(String requestMethod) throws IOException {
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(requestMethod);
        urlConnection.setRequestProperty("Accept","*/*");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        outputStream = urlConnection.getOutputStream();
    }

    //Convert Stream
    private String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        done();
        return sb.toString();
    }
    //******************************Class Methods***************************************************



    // HTTP GET request
    public String sendGet(String signature, String date) throws Exception {

        String url = BASE_URL + signature + "?date=" + date;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public String sendGet(String signature, String date, String studentId) throws Exception {

        String url = BASE_URL + signature + "?date=" + date + "&studentid=" + studentId;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public String sendGet(String signature) throws Exception{
        String url = BASE_URL + signature;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

}
