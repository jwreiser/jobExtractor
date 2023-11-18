package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class NumApplicantsFilter implements JobFilter {
    boolean skipping;
    boolean readDetails;

    public NumApplicantsFilter(boolean skipping,boolean readDetails)
    {
        this.skipping = skipping;
        this.readDetails = readDetails;
    }

    public boolean include(Preferences preferences, Job job){
        if(skipping && weAreNotSkipping(preferences)){
            return true;
        }
        //don't skip at the outset as we might not have read in the number of applicants yet
        if(readDetails && preferences.isSkipUnknownNumberOfApplicants()&&skipping&&job.getNumApplicants()==null){
            System.err.println("skipping unknown number of applicants  ->reject: " + job);
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

