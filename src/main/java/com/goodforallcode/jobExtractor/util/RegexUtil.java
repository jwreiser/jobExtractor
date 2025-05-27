package com.goodforallcode.jobExtractor.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {


    public static Integer getIntegerValue(String descriptionLower, List<String> patterns) {
        Integer value = null;
        for (String patternText : patterns) {
            if (getIntegerValue(descriptionLower, patternText) != null) {
                value = getIntegerValue(descriptionLower, patternText);
                break;
            }
        }
        return value;
    }

    public static Integer getIntegerValue(String descriptionLower, String patternText) {
        return getIntegerValue(descriptionLower, patternText, null);
    }

    public static Integer getIntegerValue(String descriptionLower, String patternText,
                                          Integer defaultVal) {
        Integer value = defaultVal;
        Pattern pattern;
        ;
        Matcher matcher;
        pattern = Pattern.compile(patternText);
        matcher = pattern.matcher(descriptionLower);
        if (matcher.find()) {
            String percentText = matcher.group(1);
            if (NumberUtils.isCreatable(percentText)) {
                value = Integer.parseInt(percentText);
            }
        }

        return value;
    }

    public static String getValue(String descriptionLower, List<String> patterns) {
        String value = null;
        for (String patternText : patterns) {
            if (getValue(descriptionLower, patternText) != null) {
                value = getValue(descriptionLower, patternText);
                break;
            }
        }
        return value;
    }

    public static String getValue(String descriptionLower, String patternText) {
        String value = null;
        Pattern pattern;
        ;
        Matcher matcher;
        pattern = Pattern.compile(patternText);
        matcher = pattern.matcher(descriptionLower);
        if (matcher.find()) {
            value = matcher.group(1);
            if (value != null) {
            /*
            if that long this is probably a description not a company name;
            setting the right length is tough as company names can be 30 characters and descriptions can be 50
             */
                if (value.length() > 40) {
                    value = null;
                } else {
                    long spaceNum = value.chars().filter(c -> c == ' ').count();
                    if (spaceNum > 4) {
                        value = null;
                    } else {
                        value = value.trim();
                    }
                }
            }
        }

        return value;
    }

    public static boolean matchesPattern(String descriptionLower, String patternText) {
        Pattern pattern = Pattern.compile(patternText);
        Matcher matcher;
        boolean matches = false;

        matcher = pattern.matcher(descriptionLower);
        if (matcher.find()) {
            matches = true;
        }

        return matches;
    }
}
