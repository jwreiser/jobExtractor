package com.goodforallcode.jobExtractor.extractor;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ExtractorFactory {
    public static Extractor getExtractor(String url){
        Extractor extractor = null;
        if(url.contains("linkedin.com")) {
            extractor =new LinkedInExtractor();
        } else if(url.contains("ilr.cornell.edu")) {
            extractor = new ILRExtractor();
        } else if(url.contains("80000hours.org")) {
            extractor = new EightyKHoursExtractor();
        }else if(url.contains("usajobs.gov")) {
            extractor = new USAJobsExtractor();
        }else if(url.contains("glassdoor.com")) {
            extractor = new GlassdoorExtractor();
        }else if(url.contains("statejobs.ny.gov")) {
            extractor = new NyStateJobsExtractor();
        }
        return extractor;
    }
}
