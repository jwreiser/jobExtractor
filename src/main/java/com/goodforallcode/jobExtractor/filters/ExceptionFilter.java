package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class ExceptionFilter implements JobFilter {
    Exception exception;

    public ExceptionFilter(Exception exception) {
        this.exception = exception;
    }

    public boolean include(Preferences preferences, Job job) {
        return false;
    }
    //TODO remove stack trace for security purposes
     public String getName(){
        return "Exception: "+ exception.getMessage()+"~~~"+exception.getStackTrace();

    }

}
