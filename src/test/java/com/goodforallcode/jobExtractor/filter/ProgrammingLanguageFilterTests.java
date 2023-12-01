package com.goodforallcode.jobExtractor.filter;

import com.goodforallcode.jobExtractor.filters.shallow.title.BackendTitleFilter;
import com.goodforallcode.jobExtractor.filters.both.ProgrammingLanguageFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgrammingLanguageFilterTests {
    ProgrammingLanguageFilter programmingLanguageFilter =new ProgrammingLanguageFilter(true);
    BackendTitleFilter backendTitleFilter =new BackendTitleFilter();
    @Test
    void testNotEnoughExperience() {
        assertFalse(ProgrammingLanguageFilter.notEnoughExperience(" 5+ years of development experience with java 8+",TestUtil.getDefaultPreferences()));

        assertTrue(ProgrammingLanguageFilter.notEnoughExperience(" 5+ years of development experience with ruby",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageFilter.notEnoughExperience(" 4+ years of solid coding experience working in python.",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageFilter.notEnoughExperience(" At least 5 years of experience building Python products with a focus on Network Architectures (PPP, routers, modems etc.).".toLowerCase(),TestUtil.getDefaultPreferences()));

    }
    @Test
    void testTitleContainsUnknownLanguage() {
        assertTrue(programmingLanguageFilter.titleContainsUnknownLanguage("Backend Node Engineer"));
    }
    @Test
    void testTitleContainsUnknownLanguage_TitleOnly() {
            assertTrue(programmingLanguageFilter.titleContainsUnknownLanguage("Go Developer"));
    }

    @Test
    void testDescriptionContainsUnknownLanguage_Proficiency() {
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy)", TestUtil.getDefaultPreferences()));
        assertFalse(programmingLanguageFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy,java)", TestUtil.getDefaultPreferences()));
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy). new sentence java", TestUtil.getDefaultPreferences()));
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy); new thought java", TestUtil.getDefaultPreferences()));
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy) outside of parenthesis java", TestUtil.getDefaultPreferences()));
    }
    @Test
    void testDescriptionContainsUnknownLanguage_Shared() {
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("expertise with python required, including debugging and testing", TestUtil.getDefaultPreferences()));
    }
    @Test
    void testDescriptionContainsUnknownLanguage() {
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("design and implement efficient, scalable, maintainable microservices using golang",TestUtil.getDefaultPreferences()));
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("Today we build with react, kotlin, python, spark, kafka, and postgreSQL",TestUtil.getDefaultPreferences()));

        assertTrue(programmingLanguageFilter.containsUnknownLanguage(
                "Development using Ionic Framework: html, javascript, css Integration, " +
                        "Android Studio, Google Play Store management, Xcode, " +
                        "Apple App Store management, Ruby on Rails (RoR) Application Development Should have built and maintained a RoR application that has been deployed to the public and maintained over a period of time",
                TestUtil.getDefaultPreferences()));

    }
    @Test
    void testDescriptionContainsUnknownLanguage_IncludesKnown() {
        assertFalse(programmingLanguageFilter.containsUnknownLanguage("Today we build with react, kotlin, python, spark, kafka, java and postgreSQL",TestUtil.getDefaultPreferences()));
    }

    @Test
    void testFluency() {
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("fluency in node.js",TestUtil.getDefaultPreferences()));
    }

    //not the language spark
    @Test
    void testContainsUnknownLanguage_Strong() {
        assertTrue(programmingLanguageFilter.containsUnknownLanguage(
                "strong skills with c#, LINQ, sql, Unit Testing, .net, javascript, html Knockout, angular, Azure CosmosDB, or other NoSQL database " +
                "Experience with extensive (500+ database table) system development a plus Eager to learn and grow."
                ,TestUtil.getDefaultPreferences()));
    }
    @Test
    void testContainsUnknownLanguagePattern_Strong() {
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("strong[^\\d\\)\\.\\;]*",
                "strong skills with c#, LINQ, sql, Unit Testing, .net, javascript, html Knockout, angular, Azure CosmosDB, or other NoSQL database " +
                        "Experience with extensive (500+ database table) system development a plus Eager to learn and grow."
                ,TestUtil.getDefaultPreferences(),true));
    }
    @Test
    void testContainsUnknownLanguagePattern_DotNet() {
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("strong[^\\d\\)\\.\\;]*",
                "strong skills with LINQ, sql, Unit Testing, .net, javascript, html Knockout, angular, Azure CosmosDB, or other NoSQL database " +
                        "Experience with extensive (500+ database table) system development a plus Eager to learn and grow.",TestUtil.getDefaultPreferences(),true));
    }
    @Test
    void testCreativeSpark() {
        assertFalse(programmingLanguageFilter.containsUnknownLanguage("expertise with a creative spark",TestUtil.getDefaultPreferences()));
    }
    @Test
    void testContainsUnknownLanguage_Javascript() {
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("degree in Computer Science or related field Experience with software development languages such as Java, C++, Python, and Ruby proficiency in javascript, html, and css."
                , TestUtil.getDefaultPreferences()));
        assertFalse(programmingLanguageFilter.containsUnknownLanguage("degree in Computer Science or related field Experience with software development languages such as Java, C++, Python, and Ruby proficiency in java, html, and css."
                , TestUtil.getDefaultPreferences()));
    }
        @Test
    void testContainsUnknownLanguage_NoFinalPunctuation() {
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("degree in Computer Science or related field Experience with software development languages such as Java, C++, Python, and Ruby proficiency in javascript, html, and css "
                ,TestUtil.getDefaultPreferences()));
    }
    /**
     * Tests that +'s are escaped correctly in the language part of the regex pattern
     */
    @Test
    void testUnknownLanguage_PreparingLanguageRegex_Cpp() {
        assertFalse(programmingLanguageFilter.containsUnknownLanguage("about the job company description crown point technologies, llc is an economically disadvantaged women-owned small business (edwosb) which specializes in providing full-stack software development and data engineering solutions to government and commercial customers across a variety of domains. the company is based in columbia, maryland and takes pride in serving its customers with critical solutions. role description crown point technologies is seeking a skilled full-stack software engineer to support meaningful research and development projects across various domains. successful candidate will work with subject matter experts and innovative technologies to solve real-world software and data challenges affecting our clients. candidates with a background in data and/or knowledge engineering using knowledge graph technologies is highly desirable for this opportunity. qualifications strong foundation in computer science principles proficiency in full-stack software development understanding of object-oriented programming (oop) concepts experience with popular programming languages, such as java and python experience with common javascript frameworks such as angular and react experience with software development tools, such as git and jira bachelor's or master's degree in computer science or a related field familiarity with graph technologies such as resource description framework (rdf), web ontology language (owl), sparql, and cypher strong problem-solving and analytical abilities ability to work both independently and as part of a team excellent written and verbal communication skills experience in data modeling is a huge plus experience with cloud-based technologies, such as aws or azure", TestUtil.getDefaultPreferences()));
        assertFalse(programmingLanguageFilter.containsUnknownLanguage("about the job are you a code aficionado with a flair for problem-solving? we are on the lookout for a software engineer who combines technical expertise with a creative spark. what you'll be doing: - writing elegant, efficient code that turns complex problems into simple solutions. - collaborating with a team of passionate developers, contributing to various stages of the software development lifecycle. - keeping up with the latest industry trends and technologies, and applying them to improve our products and processes. - debugging and troubleshooting to ensure our applications run smoothly for our users. who you are: - you've got 2+ years of hands-on software development experience. - you're fluent in [specific programming languages/technologies relevant to the client's needs]. - you've got a knack for thinking through user experiences and delivering solutions that delight. - you're a team player who loves to share ideas and learn from others. apply now and contribute your skills to a team that's shaping the digital landscape one line of code at a time!"
                , TestUtil.getDefaultPreferences()));
    }
    /**
     * Tests that .'s are escaped correctly in the language part of the regex pattern
     */
    @Test
    void testUnknownLanguage_PreparingLanguageRegex_DotNet() {
        assertFalse(programmingLanguageFilter.containsUnknownLanguage("duties & responsibilities work with a team of full-stack engineers to develop a scalable backend and infrastructure for vrchat design, implement, and maintain systems involving rest apis, websocket apis, databases, caching systems, mailing systems, queueing systems, networked storage, logging systems, cloud orchestration, and more be available to occasionally jump into action to solve an outage, even at inconvenient times experience, skills & qualifications four or more years of experience developing and architecting scalable backends and infrastructure for websites, online games, or similar systems able to architect applications and features (for example, social features and marketplaces) experienced with using cloud providers at scale, such as aws or google cloud able to work in a variety of languages,"
                , TestUtil.getDefaultPreferences()));
        assertTrue(programmingLanguageFilter.containsUnknownLanguage("duties & responsibilities work with a team of full-stack engineers to develop a scalable backend and infrastructure for vrchat design, implement, and maintain systems involving rest apis, websocket apis, databases, caching systems, mailing systems, queueing systems, .net, and more be available to occasionally jump into action to solve an outage, even at inconvenient times experience, skills & qualifications four or more years of experience developing and architecting scalable backends and infrastructure for websites, online games, or similar systems able to architect applications and features (for example, social features and marketplaces) experienced with using cloud providers at scale, such as aws or google cloud able to work in a variety of languages,"
                , TestUtil.getDefaultPreferences()));
    }

    @Test
    void testUnknownLanguageCpp() {
        assertFalse(programmingLanguageFilter.containsUnknownLanguage("about the job company description crown point technologies, llc is an economically disadvantaged women-owned small business (edwosb) which specializes in providing full-stack software development and data engineering solutions to government and commercial customers across a variety of domains. the company is based in columbia, maryland and takes pride in serving its customers with critical solutions. role description crown point technologies is seeking a skilled full-stack software engineer to support meaningful research and development projects across various domains. successful candidate will work with subject matter experts and innovative technologies to solve real-world software and data challenges affecting our clients. candidates with a background in data and/or knowledge engineering using knowledge graph technologies is highly desirable for this opportunity. qualifications strong foundation in computer science principles proficiency in full-stack C++ software development understanding of object-oriented programming (oop) concepts experience with popular programming languages, such as java and python experience with common javascript frameworks such as angular and react experience with software development tools, such as git and jira bachelor's or master's degree in computer science or a related field familiarity with graph technologies such as resource description framework (rdf), web ontology language (owl), sparql, and cypher strong problem-solving and analytical abilities ability to work both independently and as part of a team excellent written and verbal communication skills experience in data modeling is a huge plus experience with cloud-based technologies, such as aws or azure", TestUtil.getDefaultPreferences()));
    }
    @Test
    void testProgrammingLanguageIncluded(){
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Entry Level Java Back-End Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Entry Level Java Back-End Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Junior Java Back-End Developer (Entry Level)")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Java AND SQL Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Java AND PL/SQL Developer")));
    }
    @Test
    void testProgrammingLanguageIncluded_CaseSensitive() {
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Python and java Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Python and Java Developer")));
    }
    @Test
    void testBackendIncluded(){

        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Backend Developers- 100% remote")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Backend Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Backend Engineer(Entry-Level)")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Back End Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Entry Level Java Back-End Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Junior Java Back-End Developer (Entry Level)")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Junior Backend Engineer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Jr Backend Engineer")));

    }

    @Test
    void testNoCppButLetterC(){
        Job job =new Job("Researcher, Interactive AI");
        job.setCompanyName("Kelly Science, Engineering, Technology & Telecom");
        job.setDescription("5+ years of relevant project experience, with extent and type of experience dictating title and compensation");
        assertFalse(ProgrammingLanguageFilter.notEnoughExperience(job.getDescription(),TestUtil.getDefaultPreferences()));
    }

    @Test
    void testCpp(){
        Job job =new Job("Researcher, Interactive AI");
        job.setCompanyName("Kelly Science, Engineering, Technology & Telecom");
        job.setDescription("5+ years of relevant project experience, with extent and type of experience dictating title and c++");
        assertTrue(ProgrammingLanguageFilter.notEnoughExperience(job.getDescription(),TestUtil.getDefaultPreferences()));
    }
}
