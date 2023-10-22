package com.goodforallcode.jobExtractor;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
    public static List<String> breakDescriptionIntoWords(String description){
        return Arrays.asList(description.split("([.,!?:;'\\\"-]|\\\\s)+"));
    }


}
