package com.goodforallcode.jobExtractor.filter;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.filters.both.LocalFilter;
import com.goodforallcode.jobExtractor.filters.deep.ApplicationEngineerFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class LocalFilterTests {
    LocalFilter filter =new LocalFilter();
    @Test
    void testGetLocation() {
        Job job=new Job("SOLID BACK END JAVA DEVELOPER - DIRECT HIRE - REMOTE - MUST RESIDE W/IN 3 HRS OF RALEIGH NC - - GC USC ONLY");
        job.setDescription(null);
        job.setIndustry("Staffing and Recruiting");
        job.setCompanyName("W3Global");
        job.setLocation("New York");

        assertEquals("Raleigh",filter.getLocation(job));
    }

    @Test
    void testGetFirstLocationInDescription() {
        Optional<String> location = filter.getFirstLocationInDescription("We are actively looking for a full-stack engineer with 3-8 years of experience in the washington,dc area.");
        assertTrue(location.isPresent());
        assertTrue(location.get().toLowerCase().contains("washington"));
    }

    @Test
    void testIncludeLocationInDescription() {
        Job job = new Job("SOLID BACK END JAVA DEVELOPER");
        job.setDescription("We are actively looking for a full-stack engineer with 3-8 years of experience in the Washington,DC Area.");
        assertFalse(filter.include(TestUtil.getDefaultPreferences(),job));
    }
    @Test
    void testIncludeRemoteUnitedStates() {
        Job job=new Job("SOLID BACK END JAVA DEVELOPER");
        job.setDescription("\n" +
                "About the job\n" +
                "\n" +
                "***No third-party or H1-B candidates will be accepted.***\n" +
                "\n" +
                "\n" +
                "Title: Java Developer - Remote\n" +
                "\n" +
                "\n" +
                "Location: Remote (must reside in the United States)\n" +
                "\n" +
                "\n" +
                "Hiring Model: Initial full-time, 6-month contract with the possibility to convert to a permanent employee or extend long term\n" +
                "\n" +
                "\n" +
                "About the Client:\n" +
                "\n" +
                "Our Client is one of the largest private universities in the United States with over 5,000 employees.\n" +
                "\n" +
                "\n" +
                "Our Client provides over 19+ undergraduate and graduate degrees and offers full-time as well as part-time programs designed to help working students. Besides offering on-site support for students and staff, our Client prides itself on offering robust online and state-of-the-art technology resources.\n" +
                "\n" +
                "    Minority Access Incorporated - Diversity Award\n" +
                "    Human Rights Campaign - LGBT Best Places to Work\n" +
                "    LinkedIn - Largest Student and Alumni Network\n" +
                "\n" +
                "\n" +
                "About the Job:\n" +
                "\n" +
                "We are looking for highly skilled programmers with experience building web applications in Java. Java Developers are responsible for analyzing user requirements and business objectives, determining application features and functionality, and recommending changes to existing Java-based applications, among other duties.\n" +
                "\n" +
                "\n" +
                "Java Developers need to compile detailed technical documentation and user assistance material, requiring excellent written communication.\n" +
                "\n" +
                "\n" +
                "Java Developer Responsibilities:\n" +
                "\n" +
                "    Designing and implementing Java-based applications.\n" +
                "    Analyzing user requirements to inform application design.\n" +
                "    Defining application objectives and functionality.\n" +
                "    Aligning application design with business goals.\n" +
                "    Developing and testing software.\n" +
                "    Debugging and resolving technical problems that arise.\n" +
                "    Producing detailed design documentation.\n" +
                "    Recommending changes to existing Java infrastructure.\n" +
                "    Developing multimedia applications.\n" +
                "    Developing documentation to assist users.\n" +
                "    Ensuring continuous professional self-development.\n" +
                "\n" +
                "\n" +
                "Java Developer Requirements:\n" +
                "\n" +
                "    Java (3-5 Years)\n" +
                "    AWS – General Experience – Not necessarily expert (1-2 years)\n" +
                "    Linux (1-2 years)\n" +
                "    Degree in Computer Science or related field.\n" +
                "    Experience with user interface design, database structures, and statistical analyses.\n" +
                "    Analytical mindset and good problem-solving skills.\n" +
                "    Excellent written and verbal communication.\n" +
                "    Good organizational skills.\n" +
                "    Ability to work as part of a team.\n" +
                "    Attention to detail.\n" +
                "\n" +
                "\n" +
                "Java Developer Nice to Haves:\n" +
                "\n" +
                "    Terraform\n" +
                "    Node.js / Next.js\n" +
                "    Python\n" +
                "    Windows Admin Experience\n" +
                "    Docker (pipeline – containerization – Images)\n" +
                "    Database Experience – SQL\n" +
                "\n");
        job.setIndustry("Staffing and Recruiting");
        job.setCompanyName("W3Global");
        job.setLocation("United States");

        assertTrue(filter.include(TestUtil.getDefaultPreferences(),job));
    }

    @Test
    void testIncludeInOneOfTheFollowingStates_NotInPreferences() {
        Job job=new Job("SOLID BACK END JAVA DEVELOPER");
        job.setDescription("About the job City/State Virginia Beach, VA Overview Work Shift First (Days) (United States of America) Sentara Healthcare is seeking to hire a qualified individual to join our team as a Associate Software Engineer. Position Status: Full-time, Day Shift Position Location: This position is remote. Remote Candidates must have residency in one of the following states: Alabama, Delaware, Florida, Georgia, Idaho, Indiana, Kansas, Louisiana, Maine, Maryland, Minnesota, Nebraska, Nevada, New Hampshire, North Dakota, Ohio, Oklahoma, Pennsylvania, South Carolina, South Dakota, Tennessee, Texas, Utah, Washington State, West Virginia, Wyoming Standard Working Hours: 8:00AM to 5:00PM (ET). Position Responsibilities: Supporting Epic Client System Administrators (ECSA) in the below tasks: Install, configure, maintain and deploy the Epic client and other delivery infrastructure components on Microsoft Windows servers Maintain and support Epic ancillary services like Hyperspace and Interconnect and the related services Help manage the Change Control processes for all Epic environments build migration Monitor Epic system performance as part of daily operational requirements. Review Sherlock and Galaxy notes and apply routine upgrades to all Epic environment client applications and back end services Assist in integrating other applications with Epic using web services Minimum Requirements: 0-2 years of relevant experience with a degree 3 years of relevant experience without a degree Diversity and Inclusion at Sentara Our vision is that everyone brings the strengths that come with diversity to work with them every day. When we are achieving our vision, we have team members that feel they belong and can be their authentic selves, and our workforce is reflective of the communities we serve. We are realizing this vision through our Diversity and Inclusion strategy, which has three pillars: A diverse and talented workforce, an inclusive and supportive workplace, and outreach and engagement with our community. We have made remarkable strides in these areas over the past several years and, as our world continues to evolve, we know our work is never done. Our strategies focus on both structural inclusion, which looks at our organizational structures, processes, and practices; as well as behavioral inclusion, which evaluates our mindsets, skillsets, and relationships. Together, these strategies are moving our organization forward in an environment that fosters a culture of mutual respect and belonging for all. Please visit the link below to learn more about Sentara’s commitment to diversity and inclusion: https://www.sentara.com/aboutus/mission-vision-and-values/diversity.aspx Sentara Overview For more than a decade, Modern Healthcare magazine has ranked Sentara Healthcare as one of the nation's top integrated healthcare systems. That's because we are dedicated to growth, innovation, and patient safety at more than 300 sites of care in Virginia and northeastern North Carolina, including 12 acute care hospitals. Sentara Benefits As the third-largest employer in Virginia, Sentara Healthcare was named by Forbes Magazine as one of America's best large employers. We offer a variety of amenities to our employees, including, but not limited to: Medical, Dental, and Vision Insurance Paid Annual Leave, Sick Leave Flexible Spending Accounts Retirement funds with matching contribution Supplemental insurance policies, including legal, Life Insurance and AD&D among others Work Perks program including discounted movie and theme park tickets among other great deals Opportunities for further advancement within our organization Sentara employees strive to make our communities healthier places to live. We're setting the standard for medical excellence within a vibrant, creative, and highly productive workplace. For information about our employee benefits, please visit: Benefits - Sentara (sentaracareers.com) Please Note: The yearly Flu Vaccination is required for employment. Sentara Health offers employees comprehensive health care and retirement benefits designed with you in mind. Our benefits packages are designed to change with you by meeting your needs now and anticipating what comes next. You have a variety of options for medical. dental, and vision insurance, life insurance, disability and voluntary benefits as well as Paid Time Off in the form of sick time, vacation time and paid parental leave. Team Members have the opportunity to earn an annual flat amount bonus payment if established system and employee eligibility criteria is met. For applicants within Washington State, the following hiring range will be applied: $58,141.2 - $96,902.00 Job Summary Software Product Development focuses on developing multiple types/categories of software including end-user applications and operating systems/utility software, that provides a platform for running end-user applications for external customers. The work includes: Understanding the domain of the software problem and/or functionality, the interfaces between hardware and software, and the overall software characteristics Using programming, scripting, and/or database languages to write the software code Supporting software test engineering, DevOps, deployment, maintenance, and evolution activities by correcting programming errors, responding to scope changes, and coding software enhancements Applying knowledge of software development best practices, including coding standards, code reviews, source control management, build processes, testing, and operations. An Entry Professional applies broad theoretical job knowledge typically obtained through advanced education. Responsibilities typically include: Work is closely supervised. Problems faced are not typically difficult or complex. Explains facts, policies, and practices related to the job area. Experience in lieu of Bachelor's Degree 0-2 years of relevant experience with a degree 3 years of relevant experience without a degree Position will also include supporting Epic Client System Administrators (ECSA) in the below tasks: Install, configure, maintain and deploy the Epic client and other delivery infrastructure components on Microsoft Windows servers Maintain and support Epic ancillary services like Hyperspace and Interconnect and the related services Help manage the Change Control processes for all Epic environment build migration Monitor Epic system performance as part of daily operational requirements Review Sherlock and Galaxy notes and apply routine upgrades to all Epic environment client applications and back end services Assist in integrating other applications with Epic using web services Qualifications: BLD - Bachelor's Level Degree Skills Sentara Healthcare prides itself on the diversity and inclusiveness of its close to an almost 30,000-member workforce. Diversity, inclusion, and belonging is a guiding principle of the organization to ensure its workforce reflects the communities it serves. Per Clinical Laboratory Improvement Amendments (CLIA), some clinical environments require proof of education; these regulations are posted at ecfr.gov for further information. In an effort to expedite this verification requirement, we encourage you to upload your diploma or transcript at time of application. In support of our mission “to improve health every day,” this is a tobacco-free environment.");
        job.setIndustry("Staffing and Recruiting");
        job.setCompanyName("W3Global");
        job.setLocation("United States");

        assertFalse(filter.include(TestUtil.getDefaultPreferences(),job));
    }

    @Test
    void testIncludeInOneOfTheFollowingStates_InPreferences() {
        Job job=new Job("SOLID BACK END JAVA DEVELOPER");
        job.setDescription("About the job City/State Virginia Beach, VA Overview Work Shift First (Days) (United States of America) Sentara Healthcare is seeking to hire a qualified individual to join our team as a Associate Software Engineer. Position Status: Full-time, Day Shift Position Location: This position is remote. Remote Candidates" +
                " must have residency in one of the following states: New York, Delaware, Florida, Georgia, Idaho, Indiana, Kansas, Louisiana, Maine, Maryland, Minnesota, Nebraska, Nevada, New Hampshire, North Dakota, Ohio, Oklahoma, Pennsylvania, South Carolina, South Dakota, Tennessee, Texas, Utah, Washington State, West Virginia, Wyoming Standard Working Hours: 8:00AM to 5:00PM (ET). Position Responsibilities: Supporting Epic Client System Administrators (ECSA) in the below tasks: Install, configure, maintain and deploy the Epic client and other delivery infrastructure components on Microsoft Windows servers Maintain and support Epic ancillary services like Hyperspace and Interconnect and the related services Help manage the Change Control processes for all Epic environments build migration Monitor Epic system performance as part of daily operational requirements. Review Sherlock and Galaxy notes and apply routine upgrades to all Epic environment client applications and back end services Assist in integrating other applications with Epic using web services Minimum Requirements: 0-2 years of relevant experience with a degree 3 years of relevant experience without a degree Diversity and Inclusion at Sentara Our vision is that everyone brings the strengths that come with diversity to work with them every day. When we are achieving our vision, we have team members that feel they belong and can be their authentic selves, and our workforce is reflective of the communities we serve. We are realizing this vision through our Diversity and Inclusion strategy, which has three pillars: A diverse and talented workforce, an inclusive and supportive workplace, and outreach and engagement with our community. We have made remarkable strides in these areas over the past several years and, as our world continues to evolve, we know our work is never done. Our strategies focus on both structural inclusion, which looks at our organizational structures, processes, and practices; as well as behavioral inclusion, which evaluates our mindsets, skillsets, and relationships. Together, these strategies are moving our organization forward in an environment that fosters a culture of mutual respect and belonging for all. Please visit the link below to learn more about Sentara’s commitment to diversity and inclusion: https://www.sentara.com/aboutus/mission-vision-and-values/diversity.aspx Sentara Overview For more than a decade, Modern Healthcare magazine has ranked Sentara Healthcare as one of the nation's top integrated healthcare systems. That's because we are dedicated to growth, innovation, and patient safety at more than 300 sites of care in Virginia and northeastern North Carolina, including 12 acute care hospitals. Sentara Benefits As the third-largest employer in Virginia, Sentara Healthcare was named by Forbes Magazine as one of America's best large employers. We offer a variety of amenities to our employees, including, but not limited to: Medical, Dental, and Vision Insurance Paid Annual Leave, Sick Leave Flexible Spending Accounts Retirement funds with matching contribution Supplemental insurance policies, including legal, Life Insurance and AD&D among others Work Perks program including discounted movie and theme park tickets among other great deals Opportunities for further advancement within our organization Sentara employees strive to make our communities healthier places to live. We're setting the standard for medical excellence within a vibrant, creative, and highly productive workplace. For information about our employee benefits, please visit: Benefits - Sentara (sentaracareers.com) Please Note: The yearly Flu Vaccination is required for employment. Sentara Health offers employees comprehensive health care and retirement benefits designed with you in mind. Our benefits packages are designed to change with you by meeting your needs now and anticipating what comes next. You have a variety of options for medical. dental, and vision insurance, life insurance, disability and voluntary benefits as well as Paid Time Off in the form of sick time, vacation time and paid parental leave. Team Members have the opportunity to earn an annual flat amount bonus payment if established system and employee eligibility criteria is met. For applicants within Washington State, the following hiring range will be applied: $58,141.2 - $96,902.00 Job Summary Software Product Development focuses on developing multiple types/categories of software including end-user applications and operating systems/utility software, that provides a platform for running end-user applications for external customers. The work includes: Understanding the domain of the software problem and/or functionality, the interfaces between hardware and software, and the overall software characteristics Using programming, scripting, and/or database languages to write the software code Supporting software test engineering, DevOps, deployment, maintenance, and evolution activities by correcting programming errors, responding to scope changes, and coding software enhancements Applying knowledge of software development best practices, including coding standards, code reviews, source control management, build processes, testing, and operations. An Entry Professional applies broad theoretical job knowledge typically obtained through advanced education. Responsibilities typically include: Work is closely supervised. Problems faced are not typically difficult or complex. Explains facts, policies, and practices related to the job area. Experience in lieu of Bachelor's Degree 0-2 years of relevant experience with a degree 3 years of relevant experience without a degree Position will also include supporting Epic Client System Administrators (ECSA) in the below tasks: Install, configure, maintain and deploy the Epic client and other delivery infrastructure components on Microsoft Windows servers Maintain and support Epic ancillary services like Hyperspace and Interconnect and the related services Help manage the Change Control processes for all Epic environment build migration Monitor Epic system performance as part of daily operational requirements Review Sherlock and Galaxy notes and apply routine upgrades to all Epic environment client applications and back end services Assist in integrating other applications with Epic using web services Qualifications: BLD - Bachelor's Level Degree Skills Sentara Healthcare prides itself on the diversity and inclusiveness of its close to an almost 30,000-member workforce. Diversity, inclusion, and belonging is a guiding principle of the organization to ensure its workforce reflects the communities it serves. Per Clinical Laboratory Improvement Amendments (CLIA), some clinical environments require proof of education; these regulations are posted at ecfr.gov for further information. In an effort to expedite this verification requirement, we encourage you to upload your diploma or transcript at time of application. In support of our mission “to improve health every day,” this is a tobacco-free environment.");
        job.setIndustry("Staffing and Recruiting");
        job.setCompanyName("W3Global");
        job.setLocation("United States");

        assertTrue(filter.include(TestUtil.getDefaultPreferences(),job));
    }
}
