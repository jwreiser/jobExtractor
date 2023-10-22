package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class AIFilter implements JobFilter {
    List<String> keywords = List.of("chatbot","chatbots","Conversational AI","ML","NLP"
            ,"natural language processing","NLU","Natural Language Understanding"
            ,"machine learning","TTS", "STT", "SSML","Tensorflow", "Pytorch"
            , "ONNX");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();

            long mainCount = keywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
            if (mainCount > 2) {
                System.err.println("AI description ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
