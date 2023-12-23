package com.goodforallcode.jobExtractor.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtil {
    /*
Must keep
    services: matches micro services
 */
    private static List<String> stopWords = List.of("a", "abilities","ability",
            "able", "about","accelerate","according",
            "across", "add", "addition","advancement", "afterwards", "aided",
            "all", "also",
            "an", "analytical", "and", "any", "applicant","applicants", "application", "applications", "apply",
            "are", "area", "areas", "as", "at","award-winning",
            "backgrounds","base", "be", "become", "been", "being", "belief",
            "believe", "best", "better",
            "between", "big", "bit",
            "both", "boundaries","brands", "brings","build","building",
            "built","business", "button", "by",
            "can", "candidate", "care","career", "careers", "category",
            "chance", "change", "changes", "check",
            "class","clear","click", "clicking", "code",
            "collaborate", "collaboration","collaborative","collective",
            "come", "communication", "community","company", "competitive", "completed",
            "complex", "confident","connected", "consider",
            "contact", "contributing","corporate","correcting", "could","country","create", "creating","creation",
            "cross", "culture", "curiosity","customers", "cutting-edge","cycle",
            "data-entry", "day","dedicated","deep","deeper","deliver","delivers","delivering",
            "dental", "description", "destination", "details",
            "determine","determining","develop", "developer", "developing", "development",
            "dice", "didn't", "difficult","different","diligent", "direction", "discover","dive","diverse",
            "do", "does", "doesn't", "doing",
            "domains", "don't", "drive", "due","dynamic",
            "each", "easily","easy", "efficient","effective", "email", "emerging", "employees",
            "employer", "empowered", "enables", "endless","enhance", "enhancing",
            "engineer", "engineering", "engineers",
            "enhance", "enjoy",
            "enterprise", "environment", "environments", "ensure",
            "errors", "execute", "excellent","exciting",
            "exemplary", "expect", "experience",  "experiences" , "experts",
            "extension", "externally", "every","everyone",
            "facilitate","features", "first", "fit", "focus", "follow", "following",
            "for", "founded","forefront","from", "functional", "future",
            "gain","get","gets", "global",  "go", "good", "greetings","growing","growth", "had", "hands-on",
            "harness","hasn't", "haven't", "hadn't", "has", "have",
            "having", "hearing", "held","hello","help", "helping", "hi","high","hiring","hosted", "how",
            "ideal", "identifying", "individuals", "if", "immediate", "impact",
            "implement", "implements", "implementing","improves",
            "in", "inc", "incidents","include", "includes", "including","inclusive",
            "incredible", "independent",
            "industry", "influence", "influential", "innovation",
            "innovative", "integral", "interested", "internally",
            "interview", "into","invite",
            "is", "isn't", "issues", "it", "it's", "its",
            "job", "jobot", "jobotcom", "join",
            "key", " kickstart","kindly", "knowledge",
            "languages", "learn", "llc", "life", "like", "linkedin",
            "location", "logo", "looking",
            "make", "managers", "many",
            "mantech", "may", "medical", "members", "might", "millions","minimum",
            "modality","more", "most", "motivated","multicultural","must", "my",
            "national","needed", "new", "no", "not", "now",
            "of", "offers"," old", "on", "only", "opportunity","opportunities", "or",
            "organization", "other","others", "ought",
            "our", "ours", "over","overall","overview",
            "page", "part", "participate","partner", "partnering", "path","pdf", "people",
            "performance", "perspectives","phone", "platform","please",
            "position", "power","privately","pro","proactive","problem",
            "process", "product","products",
            "production", "profile", "programming", "problem-solving",
            "problems", "projects","promotes","provide","provides",  "provider","providing",  "proven",
            "pursuit","push","pushing",
            "qualified","quality", "quickly",
            "read","record", "recognized", "redefining","related", "relationships",
            "research", "resume", "revolutionizing","roadmap", "robust","role",
            "salary", "savvy", "seeking", "self-directed","sending", "should",
            "skilled", "skills", "smarter","so", "software","solid",
            "solve", "solves","solutions", "solving", "some",
            "stage", "stakeholders", "stand", "step", "steven", "strategic",
            "strategy", "strengthen","strengths","strive", "strong", "subcategory",
            "such", "summary","supportive", "systems","sw",
            "talent", "talented", "team", "teams", "tech", "technical", "technology",
            "technologies", "than", "that",
            "the", "their", "they", "thinking", "this",
            "then", "these", "those", "thought", "thrive",
            "time","title", "to", "today",
            "too", "tooling", "tools", "top-ranked","track",
            "track-record", "transforming",
            "transferring", "twp",
            "understand", "understanding", "unique","up", "updated",
            "us","use","usability", "utilize",
            "varied","variety", "vendors","verbal", "very", "via", "video", "vision",
            "want", "was", "we", "welcome","welcomes",
            "we'll", "we're", "we've", "were", "what",
            "where", "wherever",
            "whether", "which", "while", "who", "whose","why",
            "will", "with", "within", "without", "word",
            "work", "worker", "working", "workplace","world","world's", "would", "written",
            "you", "you'd", "you'll", "your", "you're", "yourself","you've", "zacharias");

    private static List<String> startOfDescription = List.of("required", "requirements",
            "responsibilities", "the role", "you bring", "qualifications",
            "what you will do", "what you'll do");
    private static List<String> endOfDescription = List.of("pay transparency", "compensation", "benefits");

    public static List<String> breakDescriptionIntoWords(String description){
        return Arrays.asList(description.split("([.,!?:;'\\\"-]|\\\\s)+"));
    }

    public static String compressDescription(String description, String title) {
    String editedTitle=title.replaceAll("\\)", " ").replaceAll("\\(", " ").toLowerCase();
         String editedDescription = description.toLowerCase().
                replaceAll("’", "'").replaceAll("\\\\", "")
                 .replaceAll("\"", " ");

         editedDescription=editedDescription.replaceAll(" - ", " ")
                 .replaceAll("\\(", " ").
                replaceAll("\\)", " ").replaceAll("\\?", " ");
        editedDescription=editedDescription.replaceAll(",", " ")
                .replaceAll(":", "")
                .replaceAll("!", "").replaceAll("\\.", "");
        editedDescription=editedDescription.replaceAll("/", " ");

        if(!title.isEmpty()) {
            editedDescription = editedDescription.replaceAll(editedTitle, " ");
        }

        final String finalEditedDescription=editedDescription.replaceAll("Want to learn more about this role and Jobot? Click our Jobot logo and follow our LinkedIn page! Job details".toLowerCase(), " ").
                replaceAll("this job is sourced from a job board", "").
                replaceAll("dice is the leading career destination for tech experts at every stage of their careers", "");


        OptionalInt lastReqtsText = startOfDescription.stream().mapToInt(p -> finalEditedDescription.lastIndexOf(p.toLowerCase())).max();
        final int startingLoc = lastReqtsText.orElse(0);
        OptionalInt endOfRelevantText = endOfDescription.stream().mapToInt(p -> finalEditedDescription.lastIndexOf(p.toLowerCase())).filter(cv -> cv > startingLoc).min();
        String truncatedDesc = editedDescription;
        if (endOfRelevantText.isPresent()) {
            truncatedDesc = editedDescription.substring(0, endOfRelevantText.getAsInt());
        }

        ArrayList<String> allWords;
        allWords = Stream.of(truncatedDesc.toLowerCase().split(" "))
                .collect(Collectors.toCollection(ArrayList<String>::new));
        allWords.removeAll(stopWords);
        String compressed= allWords.stream().collect(Collectors.joining(" "));
        while (compressed.contains("  ")){
            compressed=compressed.replaceAll("  "," ");
        }
        return compressed.trim();
    }
}

