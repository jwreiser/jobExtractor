package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.PreferencesWithDefaults;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedInExtractorTests {
    LinkedInExtractor extractor=new LinkedInExtractor();
    @Test
    void testIncludeDefense() {
        Job job=new Job("Java Software Engineer - Remote");
        job.setDescription("About the job Are you ready to take your career to the next level? If you're a seasoned Software Engineer with 3-5+ years of experience in Java, TypeScript, Spring, and a passion for developing mission-critical software solutions, VES, LLC has an exhilarating opportunity for you. Join our dynamic team at the forefront of technology innovation, dedicated to providing cutting-edge software solutions for the defense industry, including the US Army Mission Command systems. At VES, we're not just building software; we're shaping the future. Candidates should be located in a commutable distance to Picatinny, NJ, but remote work will be considered for ideal candidates. About Ves, Llc We are a small business headquartered out of Aberdeen Proving Ground, MD. We have grown up to 54 employees, many of them getting our footprints established in other areas of the United States. This particular role will support our team in Picatinny Arsenal, NJ. Established in 2014, our core competencies are the development of custom government off the Shelf (GOTS) infrastructure solutions, integrating mission command systems, and prototyping emerging technologies for use in the Army tactical architecture. We are a company built entirely of engineers, focused completely on solving the DoD's hardest software systems integration challenges. Many software government contractors only hire to fill a slot and then release the person with the contract time is up. We truly strive to be more of a close-knit group, hiring people for their talent and making sure they want to stay for the long haul. We offer highly competitive pay, generous benefits, and a friendly work culture. General Job Duties Develop, test, and manage software as directed and determined by the project lead Manage the product SDK for the system including creating/maintaining developer VMs, creating developer documentation, and developing code samples as new features are created Maintain and improve the performance of existing software solutions Clearly and regularly communicate with management and technical support colleagues Recommend improvements to existing software solutions as necessary Work closely with the customer and other stakeholders to understand requirements and design software solutions to meet their needs. Required Skills Solid problem-solving and debugging ability. Must be comfortable using a debugger. Solid CS fundamentals including object orientation, data structures, advanced algorithms, complexity, automata, operating system fundamentals, computer architecture, and systems analysis and design Familiarity with professional software engineering practices for the full software development life cycle, including requirements elicitation, coding standards, code reviews, source control management, agile development, build processes, testing, and operations Strong Git experience, including branching, merging, and resolving conflicts. Have developed unit tests and useful code documentation Ability to work independently as well as in a collaborative environment Passionate about learning new technologies, tools, and platforms Ability to take strategic guidance and execute in a self-motivated manner Ability to write code and documentation that is maintainable for years down the line Ability to write code that is easily adaptable to changing requirements Working knowledge of Java and TypeScript Familiarity with the Atlassian Tool Suite, including JIRA and Confluence. Strong Git experience, including branching, merging, and resolving conflicts. Experience managing project SDKs. Proficiency in testing methodologies and popular testing frameworks. Excellent problem-solving skills. Strong communication and teamwork skills. Ability to work independently and in a team-oriented environment. Desired Skills Strong proficiency in Java and TypeScript. Experience with the Spring framework. Proven experience in building RESTful web services. Experience using a profiler to debug performance issues Experience with Atlassian (JIRA, Confluence). Required Education And Experience Bachelor's degree in Computer Science, Software Engineering, or related field (or equivalent work experience). 3-5+ years of professional experience in software development. Ability to obtain and maintain a Security Clearance. Excellent oral and written communication skills with respect to all the above requirements. VES Offers a Rich Benefits Package That Includes 401(k) match Highly Competitive Salary Up to 15 Paid Vacation days / year 11 Paid Holidays Flexible work/life balance culture VES is an equal opportunity employer and all qualified applicants will receive consideration for employment without regard to race, color, religion, sex, national origin, disability status, protected veteran status, or any other characteristic protected by law. Job Posted by ApplicantPro");
        job.setIndustries(List.of("Defense & Space"));
        job.setCompanyName("VES LLC");
        IncludeOrExcludeJobResults results=extractor.runDeepPopulatedFilters(job,TestUtil.getDefaultPreferences(),null,null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.excludeJob());
    }
    @Test
    void testIncludeFullStack() {
        Job job=new Job("Jira Developer");
        job.setDescription("About the job An employer is looking for a full stack engineer with extensive experience using all of the different Atlassian services, specifically Jira. This engineer will be joining a team in the warranty & issue department to help rebuild a new workflow in Jira for over 24,000 users. This candidate can sit remote but must be willing to travel onsite to Warren, MI as needed for collaboration. Must Haves: Extensive experience with Atlassian modules- specifically Jira 7+ years of experience as a Jira developer/admin 7+ years of experience setting up workflows Experience with script runner in Jira Programming experience using groovy & java Implementation experience with REST APIs Plusses: Background in software development using java Troubleshooting experience");
        job.setIndustries(List.of("Staffing and Recruiting"));
        job.setCompanyName("Insight Global");
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeComplexJobs(true);
        preferences.setMaxEmployees(9000);
        IncludeOrExcludeJobResults results=extractor.runDeepPopulatedFilters(job,preferences,null,null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.excludeJob());
    }
//    @Test too slow to run regularly and is likely that the url won't be valid at some point
    void testDeepLoad() {
        Job job=new Job("Jira Developer");
        job.setDescription("About the job An employer is looking for a full stack engineer with extensive experience using all of the different Atlassian services, specifically Jira. This engineer will be joining a team in the warranty & issue department to help rebuild a new workflow in Jira for over 24,000 users. This candidate can sit remote but must be willing to travel onsite to Warren, MI as needed for collaboration. Must Haves: Extensive experience with Atlassian modules- specifically Jira 7+ years of experience as a Jira developer/admin 7+ years of experience setting up workflows Experience with script runner in Jira Programming experience using groovy & java Implementation experience with REST APIs Plusses: Background in software development using java Troubleshooting experience");
        job.setIndustries(List.of("Staffing and Recruiting"));
        job.setCompanyName("Insight Global");
        job.setUrl("https://www.linkedin.com/jobs/view/3765387527/?eBP=CwEAAAGL2VJfGmyxc6ALeknuFv7JRWDpkxCGeq4dBHJ2dmjtBq_xiNFiuYSTlo21xyK7H0KVpyfKD1_dRoRKMctd0T8vFxFUbs7Lr2k8_JJ1oTTOx4tJTHNe9uJRMQaVDQEm3YQZoJsdL-PETq1th9XwUgAO7CftGzH_NbStPPDhOj2OHqWoUx0Lr-q0Dgh89VpKMneUwXroyF4zOYFh4rsALiJNiXN0D8j4La6CItZhS-J_WVxov_FIxmqFs7_5mOLWT5MOLu80Nwf5foMM5jsZVLdYHsHSM6hOH6lL1Fgjq8aALhsS4QKm6qwtwSvEjYaPm4dbz5H9OXC62vlYn92zHeWky2fTJDD1HX3B7GqgW8yEAZlWRYKCc9camEGFnySYSKEv9KiQhA&refId=%2FGOO6QJKl3zaH85N35D4Sw%3D%3D&trackingId=vO1k%2FcjhLuqOw2cx2oBIeg%3D%3D");
//        WebDriver webDriver = LinkedInExtractor.getWebDriver();
//        boolean success = extractor.deepLoadJob(job, webDriver);
//        webDriver.close();
//        assertTrue(success);
        assertFalse(job.isAcceptingApplications());
    }

//    @Test too slow to run regularly and is likely that the url won't be valid at some point

    void testDeepLoad_Contract() {
        Job job=new Job("Jira Developer");
        job.setDescription("About the job An employer is looking for a full stack engineer with extensive experience using all of the different Atlassian services, specifically Jira. This engineer will be joining a team in the warranty & issue department to help rebuild a new workflow in Jira for over 24,000 users. This candidate can sit remote but must be willing to travel onsite to Warren, MI as needed for collaboration. Must Haves: Extensive experience with Atlassian modules- specifically Jira 7+ years of experience as a Jira developer/admin 7+ years of experience setting up workflows Experience with script runner in Jira Programming experience using groovy & java Implementation experience with REST APIs Plusses: Background in software development using java Troubleshooting experience");
        job.setIndustries(List.of("Staffing and Recruiting"));
        job.setCompanyName("Insight Global");
        job.setUrl("https://www.linkedin.com/jobs/view/3760879009/?eBP=CwEAAAGL2VJlLlUWRmaYHW7HknSIiaQlh7dZzbDhaIfg34RwHROWNUH5RCnOPrqMNeYZtqMLpGUJrq_XDYvnivOLb-kI5BgaromQtP12IwhOiB48pvebekTawM8v8um0g78eMBR2hd83ERyI_-a2h8GaOzaIW4qvhEJ97zBiZXJqwAv-l4u3_Zi-UnX0Luxbp5I4jXFeVgOrS1BNgLedkutB6KB3ycX7fF4XcK233AeB0quouUpuaGZeuhogTPfYxaSy5swRhC5VHFbzYUPibZTEHlvSVtO2Fx1qqh2CzownOUSL9SVHmcdaXtk-c3CZpUU2FbGOK0I7gGPPtv5v1iJisSdlyUniAXpuVIm7u_zrjHW20F9dwtNP4oXEveBCPFyzuhP_jYk2di1U&refId=plljqbh09Djw445SiUI%2Fpg%3D%3D&trackingId=rpNKTtL7gGqNtn2XvfOqmw%3D%3D");
//        WebDriver webDriver = LinkedInExtractor.getWebDriver();
//        boolean success = extractor.deepLoadJob(job, webDriver);
//        webDriver.close();
//        assertTrue(success);
        assertTrue(job.getContract());
    }

    @Test
    void testIncludeBusinessIntelligenceTitle() {
        Job job=new Job("Jira Developer");
        //description can't trigger exclude if this test is to be true job.setDescription("About the job An employer is looking for a full stack engineer with extensive experience using all of the different Atlassian services, specifically Jira. This engineer will be joining a team in the warranty & issue department to help rebuild a new workflow in Jira for over 24,000 users. This candidate can sit remote but must be willing to travel onsite to Warren, MI as needed for collaboration. Must Haves: Extensive experience with Atlassian modules- specifically Jira 7+ years of experience as a Jira developer/admin 7+ years of experience setting up workflows Experience with script runner in Jira Programming experience using groovy & java Implementation experience with REST APIs Plusses: Background in software development using java Troubleshooting experience");
        job.setIndustries(List.of("Staffing and Recruiting"));
        job.setCompanyName("Insight Global");
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeComplexJobs(true);
        preferences.setMaxEmployees(9000);
        IncludeOrExcludeJobResults results=extractor.runDeepPopulatedFilters(job,preferences,null,null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.excludeJob());
    }
    @Test
    void testIncludeHardwareKnowledgeable() {
        Job job=new Job("Software Engineer");
        job.setDescription("About the job Position Details We are looking for a driven and experienced engineer to join our robotic automation team to help design, build, and deliver best-in-class laboratory robotic automation systems. As a member of the team, you will work with a small project team to develop and maintain our driver library for varied laboratory equipment, as well as understand customer process requirements to help develop best-of-class automation solutions. We are looking for a passionate engineer who is excited to learn and develop along with our company as we develop remarkable, innovative products. You will be working with cross-functional teams on multiple projects simultaneously, so you will need to be flexible, adaptable, responsive, and collaborative. Our customers are excited to work with us because our team strives to deliver high quality automation systems, ensuring their success with everyday automation. Responsibilities Develop new instrument drivers for controlling lab instrumentation and maintain existing drivers using C#. Test and validate instrument driver functionality, remotely and at customer sites. Gather integration documentation on instrument command sets and APIs. Work with other members of the software team to track issues and development on GitLab. Interface with and train customers on using robotic arms, specific device drivers, and full room-scale automated systems. Conduct Acceptance Testing of components for projects. Generate project documentation, such as: scoping documents, acceptance testing criteria, and user manuals. Test the core software, Director by building processes for automation systems and custom engineering projects. Become an expert with Director Scheduling Software and assist with the development and testing of new features. Qualifications Proficiency in C#, Java, or other class-based object-oriented languages with a minimum 3 years of experience. Experience using Git for version control. Bachelor's degree in computer science, engineering, or related field, or equivalent experience. Excellent verbal and written communication skills. Demonstrated high level of accountability, responsibility, and technical competence. Ability to travel domestically 1-2 times per quarter, with the possibility of traveling internationally once per year. Travel is estimated to be approximately 10% per year on average. Preferred Candidate Attributes Working knowledge of various API and control schemes (e.g.: REST, TCP/IP, Serial, etc.) C# programming skills preferred. Experience managing all aspects of a product's lifecycle. Experience in Life Sciences industry and using laboratory instruments from Beckman, Tecan, Agilent, etc. Experience in robotic automation (SCARA or 6 DOF robot arms). We offer a competitive benefits package including benefits such as: Health Insurance Dental Insurance Vision Insurance Retirement plan options 4% contribution to all employees 401K");
        job.setIndustries(List.of("Staffing and Recruiting"));
        job.setCompanyName("W3Global");
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeComplexJobs(true);
        preferences.setMaxEmployees(9000);
        IncludeOrExcludeJobResults results=extractor.runDeepPopulatedFilters(job,preferences,null,null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.excludeJob());
    }

    @Test
    void testIncludeNonRemoteTitle() {
        Job job=new Job("SOLID BACK END JAVA DEVELOPER - DIRECT HIRE - REMOTE - MUST RESIDE W/IN 3 HRS OF RALEIGH NC - - GC USC ONLY");
        job.setDescription(null);
        job.setIndustries(List.of("Staffing and Recruiting"));
        job.setCompanyName("W3Global");
        job.setMunicipality("New York");
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeComplexJobs(true);
        preferences.setMaxEmployees(9000);
        IncludeOrExcludeJobResults results=extractor.runDeepPopulatedFilters(job,preferences,null,null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.excludeJob());
    }

    @Test
    void testIncludeNonRemoteTitleCase2() {
        Job job=new Job("Software Engineer (Remote in Wisconsin)");
        job.setDescription(null);
        job.setIndustries(List.of("Software Development"));
        job.setCompanyName("Talentify.io");
        job.setMunicipality("United States");
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeComplexJobs(true);
        preferences.setMaxEmployees(9000);
        IncludeOrExcludeJobResults results=extractor.runDeepPopulatedFilters(job,preferences,null,null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.excludeJob());
    }

    @Test
    void testIncludeNonRemoteDescription() {
        Job job=new Job("Developer II (Java Stack)");
        job.setDescription("About the job This position is remote from within Colorado ONLY. Application Deadline November 15, 2023 Are you a developer, who is not only collaborative and autonomous, but also skilled in coding across the full Software Development Life Cycle? As our new Developer II, you will be an integral part of coding, testing, minor fixes, major enhancements and planning for application sunset. You will consult with others to design applications, collaborate to create estimates and timelines and utilize Agile and/or Waterfall methodologies as needed. Some of your important responsibilities will include: Perform coding (in-house applications), unit testing, configuration management (COTS applications), source code control, deployment and release management. Coordinate update releases and other system changes, contribute to the implementation of break/fix solutions. Organize, build, and validate all segments of the code for final User Acceptance Testing. Identify and recommend changes to development policies, processes, templates and standard operating procedures. Find unique ways to resolve issues in the development process, leading to more efficient implementation. Minimum Qualifications: 2 years of hands-on programming experience in applications development. Preferred Qualifications: 3 years experience in Java development 3 years experience in Oracle database development");
        job.setIndustries(List.of("Information Technology & Services"));
        job.setCompanyName("Colorado Governor's Office of Information Technology");
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeComplexJobs(true);
        preferences.setMaxEmployees(9000);
        IncludeOrExcludeJobResults results=extractor.runDeepPopulatedFilters(job,preferences,null,null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.excludeJob());
    }

    @Test
    void testIncludeDefenseIndustry() {
        Job job=new Job("Programming Analyst");
        job.setDescription("About the job Oracle ERP Programmer Analyst This position is fully remote BAE Systems Enterprise Shared Services IT is searching for an Oracle eBusiness Suite Technical Developer to join our team providing support to manufacturing operations across our enterprise. You will act as a face of IT to the customer and the face of the customer to the Development teams and IT management. Required Experience US CITIZEN OR GREEN CARD HOLDER Oracle Application Express (APEX) development/programming experience e-Business Suite R12 experience Experience in Oracle ERP financials and/or manufacturing Knowledge of Oracle e-Business Suite technical tools including SQL, PL/SQL and Personalization Experience providing operational support, troubleshoot defects, and resolve issues using various tools including internal documentation and interaction with Oracle Support Experience create ad-hoc scripts to analyze data and support operational requests Experience providing coordination of small projects to implement custom solutions, application changes, and Oracle patches/upgrades Experience in presenting alternative requirements for solution design and suggest improvements in business processes. Experience in collaborating with development team to ensure deliverables meet expectations, assist in solution development, and bug troubleshooting. Experience translating requirements into test scenarios and test scripts, conduct first-level unit testing, and support end-user testing.");
        job.setIndustries(List.of("Defense and Space Manufacturing"));
        job.setCompanyName("Test");
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeComplexJobs(true);
        preferences.setMaxEmployees(9000);
        IncludeOrExcludeJobResults results=extractor.runDeepPopulatedFilters(job,preferences,null,null,null);
        assertFalse(results.includeJob());
        assertFalse(results.skipRemainingJobs());
        assertTrue(results.excludeJob());
    }
}
