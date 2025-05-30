package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.NumUtil;

public class TextJobPopulator {
    public static void updateJob(String text, Job job) {
        if (text.contains("top applicant")) {
            job.setTopApplicant(true);
        }
        if (text.contains("Promoted")) {
            job.setPromoted(true);
        }
        if (text.contains("Easy Apply")) {
            job.setEasyApply(true);
        }
        if (text.contains("Remote")) {
            job.setFullyRemote(true);
            if (!text.equals("Remote")) {
                job.setMunicipality(text.replaceAll("\\(Remote\\)", "").trim());
            }
        }
        if (text.equals("Contract")) {
            job.setContract(true);
        }

        if (text.contains("/yr") || text.contains("/hr")) {
            addSalaryInformation(text, job);
        }

        if (text.contains("This job is sourced from a job board.")) {
            job.setSourcedFromJobBoard(true);
        }

        if (text.equals("No longer accepting applications")) {
            job.setAcceptingApplications(false);
        }

        if (text.startsWith("Applied")) {
            job.setApplied(true);
        }
    }

    private static void addSalaryInformation( String salaryRange, Job job) {
        int benefitsPortion = salaryRange.indexOf("·");
        if(benefitsPortion>0){
            salaryRange=salaryRange.substring(0,benefitsPortion);
        }
        boolean hourlySalary=false;
        if(salaryRange.contains("/hr")){
            hourlySalary=true;
        }
        salaryRange=salaryRange.replaceAll("/hr","").replaceAll("/yr","");
        String[] parts = salaryRange.split(" ");
        if (parts.length >= 1) {
            Integer minSalary = NumUtil.convertSalaryToLong(parts[0]);
            if (minSalary != null) {
                if(hourlySalary){
                    job.setMinHourlySalary(minSalary);
                }else {
                    job.setMinYearlySalary(minSalary);
                }
            }
            if (parts.length >= 3) {//part 2 is the dash
                Integer maxSalary = NumUtil.convertSalaryToLong(parts[2]);
                if (maxSalary != null) {
                    if(hourlySalary){
                        job.setMaxHourlySalary(maxSalary);
                    }else {
                        job.setMaxYearlySalary(maxSalary);
                    }

                }
            }
        }
    }

}
