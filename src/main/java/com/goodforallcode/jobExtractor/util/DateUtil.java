package com.goodforallcode.jobExtractor.util;

import com.goodforallcode.jobExtractor.model.Job;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.Period;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

public class DateUtil {
    public static long getAgeInDays(String dateString) {
        if(dateString.contains("/")) {
            return getAgeInDaysSlashes(dateString);
        }
        LocalDate date = getLocalDate(dateString);
        Period period = date.until(LocalDate.now());
        long ageInDays = period.get(DAYS)+(period.get(MONTHS)*30);
        return ageInDays;
    }

    public static LocalDate getLocalDate(String dateString) {
        if(dateString.contains("/")) {
            return getLocalDateSlashes(dateString);
        }
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }

    private static long getAgeInDaysSlashes(String dateString) {
        LocalDate date = getLocalDateSlashes(dateString);
        Period period = date.until(LocalDate.now());
        long ageInDays = period.get(DAYS)+(period.get(MONTHS)*30);
        return ageInDays;
    }

    private static LocalDate getLocalDateSlashes(String dateString) {
        String[] dateParts = dateString.split("/");
        String year= dateParts[2];
        if(year.length()==2){
            year="20"+year; //assume 21st century for two digit years
        }
        String rearrangedDateString =year+ "-" + dateParts[0] + "-" + dateParts[1];
        LocalDate date = LocalDate.parse(rearrangedDateString);
        return date;
    }

    public static void setDateInformation(String dateString , Job job) {
        dateString = dateString.replace("Posted ", "").replace("Published ", "");
        Long daysAgo=null;
        if(dateString.contains("days ago") || dateString.contains("day ago")) {
            daysAgo=Long.parseLong(dateString.split(" ")[0]);
        }else if(dateString.contains("hours ago") || dateString.contains("hour ago")) {
            daysAgo=0L;
        }else if(dateString.contains("week ago")) {
            daysAgo=7L;
        }else if(dateString.contains("weeks ago")) {
            long weeksAgo = Long.parseLong(dateString.split(" ")[0]);
            daysAgo=weeksAgo*7L;
        }

        if (daysAgo!=null) {
            job.setJobAgeInDays(daysAgo);
        }
    }


}
