package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class NumApplicantsFilter implements JobFilter {
    boolean skipping;

    public NumApplicantsFilter(boolean skipping) {
        this.skipping = skipping;
    }

    public boolean include(Preferences preferences, Job job){
        if(skipping && weAreNotSkipping(preferences)){
            return true;
        }
        if(preferences.isSkipUnknownNumberOfApplicants()&&skipping&&job.getNumApplicants()==null){
            System.err.println("skipping uknown number of applicants  ->reject: " + job);
            return false;
        }
        if (job.getNumApplicants()!=null && job.getNumApplicants()>preferences.getMaxApplicants()) {
            System.err.println("num applicants ("+job.getNumApplicants()+") > "+preferences.getMaxApplicants()+" ->reject: " + job);
            return false;
        }
        return true;

    }

    private  boolean weAreNotSkipping(Preferences preferences){
        return !(preferences.isSkipTooManyApplicants()||preferences.isSkipUnknownNumberOfApplicants());
    }
}
