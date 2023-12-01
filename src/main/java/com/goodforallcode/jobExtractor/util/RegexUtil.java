package com.goodforallcode.jobExtractor.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {


    public static Integer getValue(String descriptionLower, List<String> patterns) {
        Integer value = null;
        for (String patternText : patterns) {
            if (getValue(descriptionLower, patternText) != null) {
                value = getValue(descriptionLower, patternText);
                break;
            }
        }
        return value;
    }
    public static Integer getValue(String descriptionLower, String patternText) {
        return getValue(descriptionLower, patternText, null);
    }
     public static Integer getValue(String descriptionLower, String patternText,
                                    Integer defaultVal) {
        Integer value = defaultVal;
        Pattern pattern;;
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

    public static boolean matchesPattern(String descriptionLower, String patternText) {
        Pattern     pattern = Pattern.compile(patternText);
        Matcher matcher;
        boolean matches=false;

        matcher = pattern.matcher(descriptionLower);
        if (matcher.find()) {
            matches=true;
        }

        return matches;
    }

    public static boolean isPresentAndImportant(String descriptionLower, String basePattern, boolean expert) {
        if (!RegexUtil.matchesPattern(descriptionLower, basePattern)) {
            return false;
        } else {
            if (!expert) {
                if (RegexUtil.matchesPattern(descriptionLower, basePattern + ".*a bonus")) {
                    return false;
                }
                if (RegexUtil.matchesPattern(descriptionLower, basePattern + ".*a plus")) {
                    return false;
                }
                if (RegexUtil.matchesPattern(descriptionLower, basePattern + ".*preferred")) {
                    return false;
                }
                if (RegexUtil.matchesPattern(descriptionLower, "bonus.*" + basePattern)) {
                    return false;
                }
                return true;
            }
            return true;
        }
    }

    public static String getUntilNextBoundary(){
        return "[^\\d\\)\\.\\;]*";
    }
}
