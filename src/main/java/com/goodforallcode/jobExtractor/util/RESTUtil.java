package com.goodforallcode.jobExtractor.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RESTUtil {
    private static int NUM_RETRIES=1;
    public static CompanySummary callUrl(String urlString) {
        return callUrl(urlString,0);
    }
    public static CompanySummary callUrl(String urlString, int trialNumber){
        CompanySummary summary=null;
        String json=callUrlJsonGet(urlString,trialNumber);
        if(json!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                summary = objectMapper.readValue(json, CompanySummary.class);
                //don't use an empty summary (this is only an approximate of empty but should work); sector can come back non null when everything else is null so ignore it
                if(summary.getDefense()==null&&summary.getOffshores()==null&&summary.getPeopleFocused()==null&&summary.getPublicGood()==null
                        &&summary.getFastPaced()==null && summary.getFoundingYear()==null&&summary.getRecentLayoffs()==null&&
                        summary.getEmployeeRangeLow()==null){
                    summary=null;
                }
            } catch (JsonProcessingException e) {

            }

        }
        return summary;
    }

    public static CompanySummary callUrl(String urlString, Job job){
        CompanySummary summary=null;
        String json=callUrlJson(urlString,job,0);
        if(json!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                summary = objectMapper.readValue(json, CompanySummary.class);
                //don't use an empty summary (this is only an approximate of empty but should work); sector can come back non null when everything else is null so ignore it
                if(summary.getDefense()==null&&summary.getOffshores()==null&&summary.getPeopleFocused()==null&&summary.getPublicGood()==null
                        &&summary.getFastPaced()==null && summary.getFoundingYear()==null&&summary.getRecentLayoffs()==null&&
                        summary.getEmployeeRangeLow()==null){
                    summary=null;
                }
            } catch (JsonProcessingException e) {

            }

        }
        return summary;
    }
    public static boolean callUrlBoolean(String urlString, Job job){
        boolean result=false;
        String json=callUrlJson(urlString,job,0);
        if(json!=null){
            if(json.equals("true")){
                result=true;
            }
        }
        return result;
    }
    public static String callUrlJson(String urlString, Object payload){
        return callUrlJson(urlString,payload,0);
    }

    private static boolean shouldLog(String urlString) {
        List<String> tested=List.of("summary/add",//"contains/description",
                "job/add/","summary/get","company/summarize","add/remaining");
        if(tested.stream().anyMatch(urlString::contains)){
            return false;
        }else{
            return true;
        }
    }
    public static String callUrlJson(String urlString, Object payload, int trialNumber) {
        String json=null;

        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String jsonInputString = ow.writeValueAsString(payload);
            json = callUrlWithJson(urlString, jsonInputString, trialNumber);
        } catch (JsonProcessingException e) {

        }
        return json;
    }
        public static String callUrlWithJson(String urlString, String jsonInputString, int trialNumber){
        String json=null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");



            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }catch (Exception e){
                System.err.println("!!!!!!!!!!!!!!!!!!!!!!       ERROR "+e.getMessage());
            }


            // Collect the response code
            int responseCode = connection.getResponseCode();

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

                    json = response.toString();
                }
            } else if (trialNumber < NUM_RETRIES) {
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {

                }
                return callUrlJson(urlString, ++trialNumber);
            }
        }catch(IOException ex){
            //return null
        }
        return json;
    }
    public static String callUrlJsonGet(String urlString,int trialNumber){
        String json=null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");

            // Collect the response code
            int responseCode = connection.getResponseCode();

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

                    json = response.toString();
                }
            } else if (trialNumber < NUM_RETRIES) {
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {

                }
                return callUrlJsonGet(urlString, ++trialNumber);
            }
        }catch(IOException ex){
            //return null
        }
        return json;
    }

    public static String callUrlJson(String urlString, int trialNumber){
        String json=null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");

            // Collect the response code
            int responseCode = connection.getResponseCode();

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

                    json = response.toString();
                }
            } else if (trialNumber < NUM_RETRIES) {
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {

                }
                return callUrlJson(urlString, ++trialNumber);
            }
        }catch(IOException ex){
            //return null
        }
        return json;
    }
}
