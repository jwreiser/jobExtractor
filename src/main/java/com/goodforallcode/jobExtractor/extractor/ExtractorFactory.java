package com.goodforallcode.jobExtractor.extractor;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ExtractorFactory {
    public static Extractor getExtractor(String url){
        LinkedInExtractor extractor = new LinkedInExtractor();
        return extractor;
    }
}
