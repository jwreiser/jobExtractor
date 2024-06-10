package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class DevSecOpsTitleFilter implements JobFilter {
    List<String> titles = List.of("Datacenter Engineer","Devop","Devsecop",
            "Release Engineer","Build","Dev ops","Devops",
            " IT Engineer","Information Technology Engineer",
            "Monitoring","VDI ",
            "Infrastructure","Site Reliability","SRE ","Reliability Engineer","Observability",
            "Operations"," Ops Engineer","CSfC Engineer",
            "Information assurance","Integration Engineer","Release Management",
            "Platform Engineer","C3ISR","SysOps"
    );
    List<String> titleContains = List.of("Delivery","Configuration","Deployment"
    );

    @Override
    public boolean include(Preferences preferences, Job job) {
        String title = job.getTitle().toLowerCase();
        if(titles.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            return false;
        }
        if(titleContains.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            return false;
        }

        return true;
    }
}
