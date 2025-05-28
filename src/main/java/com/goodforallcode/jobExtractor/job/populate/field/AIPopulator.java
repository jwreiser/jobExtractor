package com.goodforallcode.jobExtractor.job.populate.field;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Optional;

public class AIPopulator implements FieldPopulator{
    public void populateField(Job job, Preferences preferences) {

                ExcludeJobFilter filter = ExcludeJobFilter.build("AI")
                        .titlePhrases(List.of("Machine Learning", " ML ", "NLP", " AI ", "AI Programmer",
                                "Artificial Intelligence", "AI/ML", "ML/AI"))
                        .descriptionPhrasesAndCount(List.of("chatbot", "chatbots", "Conversational AI", "ML", "NLP"
                                , "natural language processing", "NLU", "Natural Language Understanding","Generative AI","GenAI"
                                , "machine learning", "TTS", "STT", "SSML", "Tensorflow", "Pytorch","AI services","AI concepts"
                                , "ONNX", "MXNet"), 2);
                if (filter.exclude(job) != null) {
                    job.setAI(true);
                }


    }

    }
