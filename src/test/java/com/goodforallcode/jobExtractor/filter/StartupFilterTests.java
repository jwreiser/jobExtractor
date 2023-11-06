package com.goodforallcode.jobExtractor.filter;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.filters.deep.always.StartupFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StartupFilterTests {
    JobFilter filter =new StartupFilter();
    @Test
    void testIncludeCompanyInDescription() {
        Job job=new Job("NeoLoad Performance Engineer");
        job.setDescription("About the job Dice is the leading career destination for tech experts at every stage of their careers. Our client, Ascendion Inc., is seeking the following. Apply via Dice today! About Ascendion Ascendion is a full-service digital engineering solutions company. We make and manage software platforms and products that power growth and deliver captivating experiences to consumers and employees. Our engineering, cloud, data, experience design, and talent solution capabilities accelerate transformation and impact for enterprise clients. Headquartered in New Jersey, our workforce of 6,000+ Ascenders delivers solutions from around the globe. Ascendion is built differently to engineer the next. Ascendion | Engineering to elevate life | ;/strong> We have a culture built on opportunity, inclusion and a spirit of partnership. Come, change the world with us: Build the coolest tech for the world’s leading brands Solve complex problems – and learn new skills Experience the power of transforming digital engineering for Fortune 500 clients Master your craft with leading training programs and hands-on experience Experience a community of change-makers! Join a culture of high-performing innovators with endless ideas and a passion for tech. Our culture is the fabric of our company, and it is what makes us unique and diverse. The way we share ideas, learning, experiences, successes, and joy allows everyone to be their best at Ascendion. About the Role: Location: Remote work Description: Day to day: Manage the full project lifecycle from requirements gathering to deployment and closure. Negotiate with stakeholders to identify resources, resolve issues, and mitigate risk/handle dependencies. Ability to work autonomously. Run cross-functional meetings. Monitor the creation of all project deliverables. Ability to handle cross multiple workstreams. Experience with enterprise-level integration projects. Experience with Oracle/SQL ServerDB experience, and MongoDB. Familiarity with Basic Unix/Linux. Experience Agile methodologies and comes with a background in the healthcare domain. Must Haves: 8-10 Years of experience in Performance Testing. At least 4-5 years of recent hands-on experience with NeoLoad. Should be an individual contributor as well as have the ability to lead. Salary Range: The salary for this position is between $110k - $120k annually. Factors that may affect pay within this range may include geography/market, skills, education, experience, and other qualifications of the successful candidate. Benefits: The Company offers the following benefits for this position, subject to applicable eligibility requirements: [medical insurance] [dental insurance] [vision insurance] [401(k) retirement plan] [long-term disability insurance] [short-term disability insurance] [5 personal day accrued each calendar year. The Paid time off benefits meet the paid sick and safe time laws that pertain to the City/ State] [10-15 day of paid vacation time] [6 paid holidays and 1 floating holiday per calendar year] [Ascendion Learning Management System] Want to change the world? Let us know. Tell us about your experiences, education, and ambitions. Bring your knowledge, unique viewpoint, and creativity to the table. Let’s talk! NeoLoad Performance Engineer");
        assertFalse(filter.include(TestUtil.getDefaultPreferences(),job));
    }

}
