package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class DevSecOpsFilter implements JobFilter {
    List<String> keywords = List.of("network", "security", "install",
            "VMware", "Servers", "Sccm", "administration", "administer",
            "configuration management"," configure"," deploy","maintain","setup");
    List<String> titles = List.of("Datacenter Engineer","Devop","Devsecop",
            "Release Engineer","Build","Dev ops","Devops",
            " IT Engineer","Information Technology Engineer",
            "devsecops","Monitoring","VDI ",
            "Infrastructure","Site Reliability","SRE","Reliability Engineer","Observability",
            "Operations"," Ops Engineer","CSfC Engineer",
            "Information assurance","Integration Engineer","Release Management",
            "Platform Engineer","C3ISR","SysOps"
    );
    List<String> titleContains = List.of("Kubernetes","Delivery","Configuration","Deployment"
    );

    @Override
    public boolean include(Preferences preferences, Job job) {
        String title = job.getTitle().toLowerCase();
        if(titles.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("DevSecOps title ->reject: " + job);
            return false;
        }
        if(titleContains.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("DevSecOps title contains ->reject: " + job);
            return false;
        }
        if (job.getDescription() != null) {
            String text = job.getDescription().toLowerCase();
            long count = keywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
            if (count > 3) {
                System.err.println("DevSecOps " + count + " ->reject: " + job);
                return false;
            }
        }

        return true;
    }
}
