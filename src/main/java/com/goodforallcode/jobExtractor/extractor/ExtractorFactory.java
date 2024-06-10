package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.job.populate.JobInfoPopulator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ExtractorFactory {
    @Autowired
    JobInfoPopulator jobInfoPopulator = new JobInfoPopulator();
    public Extractor getExtractor(String url){
        LinkedInExtractor extractor = new LinkedInExtractor();
        extractor.setJobInfoPopulator(jobInfoPopulator);
        return extractor;
    }
}
