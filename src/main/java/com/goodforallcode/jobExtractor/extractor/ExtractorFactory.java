package com.goodforallcode.jobExtractor.extractor;

public class ExtractorFactory {
    static public Extractor getExtractor(String url){
        return new LinkedInExtractor();
    }
}
