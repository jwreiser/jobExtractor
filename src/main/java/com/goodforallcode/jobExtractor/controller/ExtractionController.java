package com.goodforallcode.jobExtractor.controller;

import com.goodforallcode.jobExtractor.extractor.Extractor;
import com.goodforallcode.jobExtractor.extractor.ExtractorFactory;
import com.goodforallcode.jobExtractor.extractor.ExtractorRunner;
import com.goodforallcode.jobExtractor.extractor.JobResult;
import com.goodforallcode.jobExtractor.model.QueryInput;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Set;

@RestController()
public class ExtractionController {
    @PostMapping("/extract")
    public JobResult extractMatchingJobs(@RequestBody QueryInput queryInput){
        ExtractorRunner extractorRunner = new ExtractorRunner();
        return extractorRunner.extractMatchingJobs(queryInput);
    }
}
