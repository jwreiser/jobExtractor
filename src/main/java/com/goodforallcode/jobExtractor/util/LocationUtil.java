package com.goodforallcode.jobExtractor.util;

import com.goodforallcode.jobExtractor.model.Job;

import java.util.Map;

public class LocationUtil {
    static Map<String,String> acronyms = Map.of(
            "Alabama","AL",
            "Tennessee","TN"
    );

    public void addLocation(String location, Job job) {
        location= location.toLowerCase();
        if(location.contains("(onsite)")){
            job.setFullyRemote(false);
            location = location.replace("(onsite)","").trim();
        }else if(location.contains("(hybrid)")){
            job.setFullyRemote(false);
            location = location.replace("(hybrid)","").trim();
        }else if(location.contains("(on-site)")){
            job.setFullyRemote(false);
            location = location.replace("(on-site)","").trim();
        }else if(location.contains("(on site)")){
            job.setFullyRemote(false);
            location = location.replace("(on site)","").trim();
        }else if(location.contains("(remote)")){
            job.setFullyRemote(true);
            location = location.replace("(remote)","").trim();
        }
        if(!location.equalsIgnoreCase("united states")){
            if(location.contains(",")){
                String[] parts = location.split(",");
                if(parts.length>1) {
                    job.setMunicipality(parts[0].trim());
                    job.setState(parts[1].trim().toUpperCase());
                }

            }else {
                if(acronyms.containsKey(location)) {
                    job.setState(acronyms.get(location));
                }else {
                    job.setMunicipality(location);
                }
            }
        }
    }
}
