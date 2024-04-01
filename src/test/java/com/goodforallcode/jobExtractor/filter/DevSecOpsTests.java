package com.goodforallcode.jobExtractor.filter;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.filters.deep.always.DevSecOpsDescriptionFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DevSecOpsTests {
    JobFilter filter =new DevSecOpsDescriptionFilter();
    @Test
    void testIncludeInstallMaintain() {
        Job job=new Job("SOLID BACK END JAVA DEVELOPER");
        job.setDescription("About the job City/State Virginia Beach, VA Overview Work Shift First (Days) (United States of America) Sentara Healthcare is seeking to hire a qualified individual to join our team as a Associate Software Engineer. Position Status: Full-time, Day Shift Position Location: This position is remote. Remote Candidates must have residency in one of the following states: Alabama, Delaware, Florida, Georgia, Idaho, Indiana, Kansas, Louisiana, Maine, Maryland, Minnesota, Nebraska, Nevada, New Hampshire, North Dakota, Ohio, Oklahoma, Pennsylvania, South Carolina, South Dakota, Tennessee, Texas, Utah, Washington State, West Virginia, Wyoming Standard Working Hours: 8:00AM to 5:00PM (ET). Position Responsibilities: Supporting Epic Client System Administrators (ECSA) in the below tasks: Install, configure, maintain and deploy the Epic client and other delivery infrastructure components on Microsoft Windows servers Maintain and support Epic ancillary services like Hyperspace and Interconnect and the related services Help manage the Change Control processes for all Epic environments build migration Monitor Epic system performance as part of daily operational requirements. Review Sherlock and Galaxy notes and apply routine upgrades to all Epic environment client applications and back end services Assist in integrating other applications with Epic using web services Minimum Requirements: 0-2 years of relevant experience with a degree 3 years of relevant experience without a degree Diversity and Inclusion at Sentara Our vision is that everyone brings the strengths that come with diversity to work with them every day. When we are achieving our vision, we have team members that feel they belong and can be their authentic selves, and our workforce is reflective of the communities we serve. We are realizing this vision through our Diversity and Inclusion strategy, which has three pillars: A diverse and talented workforce, an inclusive and supportive workplace, and outreach and engagement with our community. We have made remarkable strides in these areas over the past several years and, as our world continues to evolve, we know our work is never done. Our strategies focus on both structural inclusion, which looks at our organizational structures, processes, and practices; as well as behavioral inclusion, which evaluates our mindsets, skillsets, and relationships. Together, these strategies are moving our organization forward in an environment that fosters a culture of mutual respect and belonging for all. Please visit the link below to learn more about Sentara’s commitment to diversity and inclusion: https://www.sentara.com/aboutus/mission-vision-and-values/diversity.aspx Sentara Overview For more than a decade, Modern Healthcare magazine has ranked Sentara Healthcare as one of the nation's top integrated healthcare systems. That's because we are dedicated to growth, innovation, and patient safety at more than 300 sites of care in Virginia and northeastern North Carolina, including 12 acute care hospitals. Sentara Benefits As the third-largest employer in Virginia, Sentara Healthcare was named by Forbes Magazine as one of America's best large employers. We offer a variety of amenities to our employees, including, but not limited to: Medical, Dental, and Vision Insurance Paid Annual Leave, Sick Leave Flexible Spending Accounts Retirement funds with matching contribution Supplemental insurance policies, including legal, Life Insurance and AD&D among others Work Perks program including discounted movie and theme park tickets among other great deals Opportunities for further advancement within our organization Sentara employees strive to make our communities healthier places to live. We're setting the standard for medical excellence within a vibrant, creative, and highly productive workplace. For information about our employee benefits, please visit: Benefits - Sentara (sentaracareers.com) Please Note: The yearly Flu Vaccination is required for employment. Sentara Health offers employees comprehensive health care and retirement benefits designed with you in mind. Our benefits packages are designed to change with you by meeting your needs now and anticipating what comes next. You have a variety of options for medical. dental, and vision insurance, life insurance, disability and voluntary benefits as well as Paid Time Off in the form of sick time, vacation time and paid parental leave. Team Members have the opportunity to earn an annual flat amount bonus payment if established system and employee eligibility criteria is met. For applicants within Washington State, the following hiring range will be applied: $58,141.2 - $96,902.00 Job Summary Software Product Development focuses on developing multiple types/categories of software including end-user applications and operating systems/utility software, that provides a platform for running end-user applications for external customers. The work includes: Understanding the domain of the software problem and/or functionality, the interfaces between hardware and software, and the overall software characteristics Using programming, scripting, and/or database languages to write the software code Supporting software test engineering, DevOps, deployment, maintenance, and evolution activities by correcting programming errors, responding to scope changes, and coding software enhancements Applying knowledge of software development best practices, including coding standards, code reviews, source control management, build processes, testing, and operations. An Entry Professional applies broad theoretical job knowledge typically obtained through advanced education. Responsibilities typically include: Work is closely supervised. Problems faced are not typically difficult or complex. Explains facts, policies, and practices related to the job area. Experience in lieu of Bachelor's Degree 0-2 years of relevant experience with a degree 3 years of relevant experience without a degree Position will also include supporting Epic Client System Administrators (ECSA) in the below tasks: Install, configure, maintain and deploy the Epic client and other delivery infrastructure components on Microsoft Windows servers Maintain and support Epic ancillary services like Hyperspace and Interconnect and the related services Help manage the Change Control processes for all Epic environment build migration Monitor Epic system performance as part of daily operational requirements Review Sherlock and Galaxy notes and apply routine upgrades to all Epic environment client applications and back end services Assist in integrating other applications with Epic using web services Qualifications: BLD - Bachelor's Level Degree Skills Sentara Healthcare prides itself on the diversity and inclusiveness of its close to an almost 30,000-member workforce. Diversity, inclusion, and belonging is a guiding principle of the organization to ensure its workforce reflects the communities it serves. Per Clinical Laboratory Improvement Amendments (CLIA), some clinical environments require proof of education; these regulations are posted at ecfr.gov for further information. In an effort to expedite this verification requirement, we encourage you to upload your diploma or transcript at time of application. In support of our mission “to improve health every day,” this is a tobacco-free environment.");
        job.setIndustries(List.of("Staffing and Recruiting"));
        job.setCompanyName("W3Global");
        job.setLocation("United States");

        assertFalse(filter.include(TestUtil.getDefaultPreferences(),job));
    }
}
