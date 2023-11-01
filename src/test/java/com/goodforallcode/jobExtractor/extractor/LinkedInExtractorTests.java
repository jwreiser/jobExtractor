package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.filters.deep.ApplicationEngineerFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.PreferencesWithDefaults;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedInExtractorTests {
    LinkedInExtractor extractor=new LinkedInExtractor();
    @Test
    void testIncludeDefense() {
        Job job=new Job("Java Software Engineer - Remote");
        job.setDescription("About the job Are you ready to take your career to the next level? If you're a seasoned Software Engineer with 3-5+ years of experience in Java, TypeScript, Spring, and a passion for developing mission-critical software solutions, VES, LLC has an exhilarating opportunity for you. Join our dynamic team at the forefront of technology innovation, dedicated to providing cutting-edge software solutions for the defense industry, including the US Army Mission Command systems. At VES, we're not just building software; we're shaping the future. Candidates should be located in a commutable distance to Picatinny, NJ, but remote work will be considered for ideal candidates. About Ves, Llc We are a small business headquartered out of Aberdeen Proving Ground, MD. We have grown up to 54 employees, many of them getting our footprints established in other areas of the United States. This particular role will support our team in Picatinny Arsenal, NJ. Established in 2014, our core competencies are the development of custom government off the Shelf (GOTS) infrastructure solutions, integrating mission command systems, and prototyping emerging technologies for use in the Army tactical architecture. We are a company built entirely of engineers, focused completely on solving the DoD's hardest software systems integration challenges. Many software government contractors only hire to fill a slot and then release the person with the contract time is up. We truly strive to be more of a close-knit group, hiring people for their talent and making sure they want to stay for the long haul. We offer highly competitive pay, generous benefits, and a friendly work culture. General Job Duties Develop, test, and manage software as directed and determined by the project lead Manage the product SDK for the system including creating/maintaining developer VMs, creating developer documentation, and developing code samples as new features are created Maintain and improve the performance of existing software solutions Clearly and regularly communicate with management and technical support colleagues Recommend improvements to existing software solutions as necessary Work closely with the customer and other stakeholders to understand requirements and design software solutions to meet their needs. Required Skills Solid problem-solving and debugging ability. Must be comfortable using a debugger. Solid CS fundamentals including object orientation, data structures, advanced algorithms, complexity, automata, operating system fundamentals, computer architecture, and systems analysis and design Familiarity with professional software engineering practices for the full software development life cycle, including requirements elicitation, coding standards, code reviews, source control management, agile development, build processes, testing, and operations Strong Git experience, including branching, merging, and resolving conflicts. Have developed unit tests and useful code documentation Ability to work independently as well as in a collaborative environment Passionate about learning new technologies, tools, and platforms Ability to take strategic guidance and execute in a self-motivated manner Ability to write code and documentation that is maintainable for years down the line Ability to write code that is easily adaptable to changing requirements Working knowledge of Java and TypeScript Familiarity with the Atlassian Tool Suite, including JIRA and Confluence. Strong Git experience, including branching, merging, and resolving conflicts. Experience managing project SDKs. Proficiency in testing methodologies and popular testing frameworks. Excellent problem-solving skills. Strong communication and teamwork skills. Ability to work independently and in a team-oriented environment. Desired Skills Strong proficiency in Java and TypeScript. Experience with the Spring framework. Proven experience in building RESTful web services. Experience using a profiler to debug performance issues Experience with Atlassian (JIRA, Confluence). Required Education And Experience Bachelor's degree in Computer Science, Software Engineering, or related field (or equivalent work experience). 3-5+ years of professional experience in software development. Ability to obtain and maintain a Security Clearance. Excellent oral and written communication skills with respect to all the above requirements. VES Offers a Rich Benefits Package That Includes 401(k) match Highly Competitive Salary Up to 15 Paid Vacation days / year 11 Paid Holidays Flexible work/life balance culture VES is an equal opportunity employer and all qualified applicants will receive consideration for employment without regard to race, color, religion, sex, national origin, disability status, protected veteran status, or any other characteristic protected by law. Job Posted by ApplicantPro");
        job.setIndustry("Defense & Space");
        job.setCompanyName("VES LLC");
        ExcludeJobResults results=extractor.excludeJob(job,TestUtil.getDefaultPreferences(),null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.exludeJob());
    }
    @Test
    void testIncludeFullStack() {
        Job job=new Job("Jira Developer");
        job.setDescription("About the job An employer is looking for a full stack engineer with extensive experience using all of the different Atlassian services, specifically Jira. This engineer will be joining a team in the warranty & issue department to help rebuild a new workflow in Jira for over 24,000 users. This candidate can sit remote but must be willing to travel onsite to Warren, MI as needed for collaboration. Must Haves: Extensive experience with Atlassian modules- specifically Jira 7+ years of experience as a Jira developer/admin 7+ years of experience setting up workflows Experience with script runner in Jira Programming experience using groovy & java Implementation experience with REST APIs Plusses: Background in software development using java Troubleshooting experience");
        job.setIndustry("Staffing and Recruiting");
        job.setCompanyName("Insight Global");
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeFresher(true);
        preferences.setExcludeSenior(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeRealEstate(true);
        preferences.setMaxEmployees(9000);
        ExcludeJobResults results=extractor.excludeJob(job,preferences,null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.exludeJob());
    }
    @Test
    void testIncludeBusinessIntelligenceTitle() {
        Job job=new Job("Jira Developer");
        //description can't trigger exclude if this test is to be true job.setDescription("About the job An employer is looking for a full stack engineer with extensive experience using all of the different Atlassian services, specifically Jira. This engineer will be joining a team in the warranty & issue department to help rebuild a new workflow in Jira for over 24,000 users. This candidate can sit remote but must be willing to travel onsite to Warren, MI as needed for collaboration. Must Haves: Extensive experience with Atlassian modules- specifically Jira 7+ years of experience as a Jira developer/admin 7+ years of experience setting up workflows Experience with script runner in Jira Programming experience using groovy & java Implementation experience with REST APIs Plusses: Background in software development using java Troubleshooting experience");
        job.setIndustry("Staffing and Recruiting");
        job.setCompanyName("Insight Global");
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeFresher(true);
        preferences.setExcludeSenior(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeRealEstate(true);
        preferences.setMaxEmployees(9000);
        ExcludeJobResults results=extractor.excludeJob(job,preferences,null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.exludeJob());
    }
    @Test
    void testIncludeHardwareKnowledgeable() {
        Job job=new Job("Software Engineer");
        job.setDescription("About the job Position Details We are looking for a driven and experienced engineer to join our robotic automation team to help design, build, and deliver best-in-class laboratory robotic automation systems. As a member of the team, you will work with a small project team to develop and maintain our driver library for varied laboratory equipment, as well as understand customer process requirements to help develop best-of-class automation solutions. We are looking for a passionate engineer who is excited to learn and develop along with our company as we develop remarkable, innovative products. You will be working with cross-functional teams on multiple projects simultaneously, so you will need to be flexible, adaptable, responsive, and collaborative. Our customers are excited to work with us because our team strives to deliver high quality automation systems, ensuring their success with everyday automation. Responsibilities Develop new instrument drivers for controlling lab instrumentation and maintain existing drivers using C#. Test and validate instrument driver functionality, remotely and at customer sites. Gather integration documentation on instrument command sets and APIs. Work with other members of the software team to track issues and development on GitLab. Interface with and train customers on using robotic arms, specific device drivers, and full room-scale automated systems. Conduct Acceptance Testing of components for projects. Generate project documentation, such as: scoping documents, acceptance testing criteria, and user manuals. Test the core software, Director by building processes for automation systems and custom engineering projects. Become an expert with Director Scheduling Software and assist with the development and testing of new features. Qualifications Proficiency in C#, Java, or other class-based object-oriented languages with a minimum 3 years of experience. Experience using Git for version control. Bachelor's degree in computer science, engineering, or related field, or equivalent experience. Excellent verbal and written communication skills. Demonstrated high level of accountability, responsibility, and technical competence. Ability to travel domestically 1-2 times per quarter, with the possibility of traveling internationally once per year. Travel is estimated to be approximately 10% per year on average. Preferred Candidate Attributes Working knowledge of various API and control schemes (e.g.: REST, TCP/IP, Serial, etc.) C# programming skills preferred. Experience managing all aspects of a product's lifecycle. Experience in Life Sciences industry and using laboratory instruments from Beckman, Tecan, Agilent, etc. Experience in robotic automation (SCARA or 6 DOF robot arms). We offer a competitive benefits package including benefits such as: Health Insurance Dental Insurance Vision Insurance Retirement plan options 4% contribution to all employees 401K");
        job.setIndustry("Staffing and Recruiting");
        job.setCompanyName("W3Global");
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeFresher(true);
        preferences.setExcludeSenior(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeRealEstate(true);
        preferences.setMaxEmployees(9000);
        ExcludeJobResults results=extractor.excludeJob(job,preferences,null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.exludeJob());
    }
}
