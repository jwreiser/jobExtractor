package com.goodforallcode.jobExtractor.filters;

import java.util.ArrayList;
import java.util.List;

public class DescriptionPhrasesAndCount {
    List<String> descriptionPhrases = new ArrayList<>();
    int excludeDescriptionCount = 0;

    public DescriptionPhrasesAndCount(List<String> descriptionPhrases, int excludeDescriptionCount) {
        this.descriptionPhrases = descriptionPhrases;
        this.excludeDescriptionCount = excludeDescriptionCount;
    }
}