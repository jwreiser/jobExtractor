package com.goodforallcode.jobExtractor.util;

import java.time.LocalDate;
import java.time.Period;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

public class DateUtil {
    public static long getAgeInDays(String dateString) {
        LocalDate date = getLocalDate(dateString);
        Period period = date.until(LocalDate.now());
        long ageInDays = period.get(DAYS)+(period.get(MONTHS)*30);
        return ageInDays;
    }

    public static LocalDate getLocalDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }
}
