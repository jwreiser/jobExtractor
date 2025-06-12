package com.goodforallcode.jobExtractor.extractor;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
/*
Indeed does not support automated scraping, so we are not using it. Their API does not cover job searching and I don't
see a free alternative that does.
 */
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
        }else if(url.contains("climatebase.org")) {
            extractor = new ClimateBaseExtractor();
        }else if(url.contains("techjobsforgood.com")) {
            extractor = new TechJobsForGoodExtractor();
        }else if(url.contains("idealist.org")) {
            extractor = new IdealistExtractor();
        }

        return extractor;
    }
}
