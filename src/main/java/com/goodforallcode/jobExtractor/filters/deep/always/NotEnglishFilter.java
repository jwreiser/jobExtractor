package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class NotEnglishFilter implements JobFilter {
    List<String> keywords = List.of("Entwickler", "korean", "Versicherung", "Softwarový"
            , "inženýr", "Japanese", "Pessoa", "Desenvolvedor", "Pleno", "Sênior", "Especialista",
            "Trabalho", "Remoto", "automatizadora", " de ", "testes", "Pleno", "Sênior",
            "Geliştirici", "NİTELİKLER","merkezli","şirketi","deneyimi","Güçlü","analizi");

    @Override
    public boolean include(Preferences preferences, Job job) {
        String title = job.getTitle().toLowerCase();

        if (keywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("Not english title ->reject: " + job);
            return false;
        }
        if (job.getDescription() != null) {
            String text = job.getDescription().toLowerCase();

            if (keywords.stream().filter(k -> text.contains(k.toLowerCase())).count() > 2) {
                System.err.println("Not english ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
