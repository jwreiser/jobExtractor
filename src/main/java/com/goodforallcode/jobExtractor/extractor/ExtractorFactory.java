package com.goodforallcode.jobExtractor.extractor;

import org.springframework.hateoas.Link;

public class ExtractorFactory {
    static public Extractor getExtractor(String url){
        return new LinkedInExtractor();
    }
}
