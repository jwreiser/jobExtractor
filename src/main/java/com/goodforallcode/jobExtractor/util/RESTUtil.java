package com.goodforallcode.jobExtractor.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodforallcode.jobExtractor.model.CompanySummary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RESTUtil {
    private static int NUM_RETRIES=1;
    public static CompanySummary callUrl(String urlString) {
        return callUrl(urlString,0);
    }
    public static CompanySummary callUrl(String urlString, int trialNumber){
        CompanySummary summary=null;
        try {
            URL url = new URL(urlString);
            System.err.println("Calling "+urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");

            // Collect the response code
            int responseCode = connection.getResponseCode();
            System.err.println("RETURNED FROM CALL "+urlString);

            if (responseCode == connection.HTTP_OK) {
                // Create a reader with the input stream reader.
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));) {

                    String inputLine;

                    // Create a string buffer
                    StringBuffer response = new StringBuffer();

                    // Write each of the input line
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    String json = response.toString();
                    ObjectMapper objectMapper = new ObjectMapper();
                    summary = objectMapper.readValue(json, CompanySummary.class);
                    //don't use an empty summary (this is only an approximate of empty but should work); sector can come back non null when everything else is null so ignore it
                    if(summary.getDefense()==null&&summary.getOffshores()==null&&summary.getPeopleFocused()==null&&summary.getPublicGood()==null
                    &&summary.getFastPaced()==null && summary.getFoundingYear()==null&&summary.getRecentLayoffs()==null&&
                            summary.getEmployeeRangeLow()==null){
                        summary=null;
                    }
                }
            } else if (trialNumber < NUM_RETRIES) {
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {

                }
                return callUrl(urlString, ++trialNumber);
            }
        }catch(IOException ex){
            //return null
        }
        return summary;
    }

}
