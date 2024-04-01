package com.goodforallcode.jobExtractor.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.goodforallcode.jobExtractor.filters.both.LocalFilter.americanCitiesAndStates;

public class StringUtil {
    final static List<String> stateAbbreviatsions=List.of("AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY","AS","DC","GU","MP","PR","VI");
    /*
Must keep
    services: matches micro services
    not/without: without pressure is a lot different than pressure
    people: it'd be good to see if they are about people
    vision: I want to see if they have a vision
    supportive
 */
    private static List<String> stopWords = List.of("$000","a", "abilities","ability",
            "able", "about","accelerate","according",
            "across", "add", "addition","advancement", "afterwards", "agency",
            "aided",
            "all", "also",
            "an", "analytical", "and", "annual","any", "applicant","applicants", "application", "applications", "apply",
            "are", "area", "areas", "as", "at","attract","available","award",

            "backgrounds","base", "based", "be", "become", "been", "being", "belief",
            "believe", "benefits","best", "better",
            "between", "big", "bit","board",
            "both", "boundaries","brands", "bridge","bring",
            "brings","build","building",
            "built","business", "button", "by",




            "can", "candidate", "candidates","care","career", "careers", "category",
            "chance", "change", "changes", "check",
            "class","clear","click", "clicking", "client's","clients","code",
            "collaborate", "collaboration","collaborative","collective",
            "come", "communication", "community","companies","company",
            "competitive", "comprehensive","completed",
            "complex", "conduct","confident","connected", "connecting","connects",
            "consider",
            "contact", "continued","continuous","contribute","contributing","corporate","correcting",
            "could","country","create", "creating","creation",
            "cross", "cross","crucial", "curiosity","customer","customers", "cutting","cycle",
             "day","dedicated","deep","deeper","definition","deliver","delivers","delivering",
            "dental", "description", "destination", "details",
            "determine","determining","develop", "developer", "developing", "development",
            "dice", "didn't", "difficult","different","diligent", "directed","direction",
            "discover","displaying","displays","dive","diverse",
            "do", "does", "doesn't", "doing",
            "domains", "don't", "drive", "due","dynamic",


            "each", "eager","easily","easy", "edge","education","efficient","effective", "elevate",
            "email", "emerging", "employees",
            "employer", "empowered", "enables", "endless","engage","enhance", "enhancing",
            "engage","engineer", "engineering", "engineers",
            "enhance", "enjoy",
            "enterprise", "entire","environment", "environments", "ensure","entry",
            "errors", "execute", "excellent","exceptional","excellence","exciting",
            "exemplary", "expect", "experience",  "experienced","experiences" ,
            "experts","exposure",
            "extension", "externally", "every","everyone",




            "facilitate","features", "firm","first", "fit", "focus", "focused","follow", "following",
            "for", "founded","forefront","from", "functional", "future",
            "gain","gap","get","gets", "global",  "go", "good",
            "great","greetings","grow","growing","growth", "had", "hands",
            "harness","hasn't", "haven't", "hadn't", "has", "have",
            "having", "hearing", "held","hello","help", "helping","here",
            "hi","high","highly","hire","hiring","hope","hosted", "how",




            "ideal", "ideally","identify","identifying", "individuals", "if", "imagine","immediate", "impact",
            "implement", "implements", "implementation","implementing","improvement","improves",
            "in", "inc", "incidents","include", "includes", "including","inclusive",
            "incredible", "independent","individual","industries",
            "industry", "influence", "influential", "innovation",
            "innovative", "insurance","insurtech","integral", "interest","interested", "internally",
            "interview", "into","invite",
            "is", "isn't", "issues", "it", "it's", "its",
            "job", "jobot", "jobotcom", "join",
            "key", " kickstart","kindly", "know","knowledge",

            "languages", "largest","learn", "leave","let","llc", "life", "lifecycle","like", "linkedin",
            "location", "logo", "looking","love",
            "make", "managers", "many",
            "mantech", "may", "me","medical", "members", "might", "millions","minimum",
            "modality","more", "morning","most", "motivated","multicultural","multiple","must", "my",
            "national","need","needed","needing", "new", "no", "now",
            "of", "offer","offers"," old", "on", "only", "open","opportunity","opportunities", "or",
            "organization", "organizations","oriented","other","others", "ought",
            "our", "ours", "over","overall","overview",





            "package","page", "paid", "parental","part", "participate","partner", "partnering", "path","pdf",
            "performance", "period","personal","perspectives","phone", "platform","play","plays",
            "please",
            "position","positions", "power","prefers","premier","present", "presented","privately",
            "pro","proactive","problem","problems",
            "process", "product","products",
            "production", "profile", "programming", "problem",
             "professional","projects","prominent","promotes","proud",
            "provide","provides",  "provider","providing",  "proven",
            "pursuit","push","pushing","qualifications",
            "qualified","quality", "qualities","quickly",

            "ranked","read","record", "recognized", "recruit","recruitment","recruiting","record",
            "redefining","reimbursement","related", "relationships",
            "research", "remote","resume", "revolutionizing","right",
            "roadmap", "robert","robust","role",




            "salary", "savvy", "seasoned","seeking", "seekers","self","sending", "set","should","sick","sizing",
            "skill","skilled", "skills", "skillset","smarter","so", "software","solid",
            "solve", "solves","solution","solutions", "solving", "some","source","sourced","sourcing",
            "specialists","specialized","specializing","sr",
            "stage", "stakeholders", "stand", "staffing","step",
            "steven", "stevenzacharias@jobotcom","still",
            "strategic","strategies",
            "strategy", "strengthen","strengths","strive", "strong", "subcategory",
            "success","such", "summary", "systems","sw",

            "talent", "talented", "team", "teams", "tech", "technical", "technology",
            "technologies", "than", "that",
            "the", "their", "they", "think","thinking", "this",
            "then", "these", "those", "thought", "thrive","through","throughout","thru",
            "tier","time","title", "to", "today",
            "too", "tooling", "tools", "top","towards","track",
            "track", "training","transforming",
            "transferring", "tuition","two","type",




            "understand", "understanding", "unique","up", "updated",
            "us","use","usability", "utilize","utilizes","vacation",
            "varied","variety", "various","vast","vendors","verbal", "very", "via",
            "video", "vision","waiting","want", "was", "we", "welcome","welcomes",
            "we'll", "we're", "we've", "well","were", "what",
            "where", "wherever",
            "whether", "which", "while", "who", "whose","why",
            "will", "windows","winning","with", "within", "word",
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
    String editedTitle=title.replaceAll("\\)", " ").replaceAll("\\*", "").
            replaceAll("\\|", " ").replaceAll("\\(", " ").toLowerCase();
         String editedDescription = description.toLowerCase().
                replaceAll("’", "'").replaceAll("\\\\", "")
                 //needed for tx-austin
                 .replaceAll("-", "")
                 .replaceAll("\"", " ") .replaceAll("\\|", " ");;

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

        final String finalEditedDescription=editedDescription.replaceAll("Want to learn more about this role and Jobot\\? Click our Jobot logo and follow our LinkedIn page! Job details".toLowerCase(), " ").
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
        allWords = Stream.of(truncatedDesc.toLowerCase().split(" ")).filter(w->!w.startsWith("#"))
                .collect(Collectors.toCollection(ArrayList<String>::new));
        allWords.removeAll(stopWords);
        allWords.removeAll(americanCitiesAndStates.stream().map(f->f.toLowerCase()).collect(Collectors.toList()));
        allWords.removeAll(stateAbbreviatsions.stream().map(f->f.toLowerCase()).collect(Collectors.toList()));


        String compressed= allWords.stream().collect(Collectors.joining(" "));
        while (compressed.contains("  ")){
            compressed=compressed.replaceAll("  "," ");
        }
        return compressed.trim();
    }

    public static String trimUrl(String url){
        String result=url;
        if(url!=null) {
            int end = url.indexOf("?");
            if (end > 0) {
                result = url.substring(0, end);
            }
        }
        return result;
    }
}

