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

    public static long getAgeInDaysSlashes(String dateString) {
        LocalDate date = getLocalDateSlashes(dateString);
        Period period = date.until(LocalDate.now());
        long ageInDays = period.get(DAYS)+(period.get(MONTHS)*30);
        return ageInDays;
    }

    public static LocalDate getLocalDateSlashes(String dateString) {
        String[] dateParts = dateString.split("/");
        String year= dateParts[2];
        if(year.length()==2){
            year="20"+year; //assume 21st century for two digit years
        }
        String rearrangedDateString =year+ "-" + dateParts[0] + "-" + dateParts[1];
        LocalDate date = LocalDate.parse(rearrangedDateString);
        return date;
    }
}
