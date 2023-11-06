package com.goodforallcode.jobExtractor.job.populate;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeepJobPopulatorTest {
    DeepJobPopulator populator =new LinkedInDeepJobPopulator();
    @Test
    void testGetMaxExperienceNeeded(){
        Optional<Integer> maxExperienceNeeded = populator.getMaxExperienceNeeded("About the job Dice is the leading career destination for tech experts at every stage of their careers. Our client, Cogent IBS, Inc, is seeking the following. Apply via Dice today! Technical Business Analyst with 10 + yrs of exp - Heavy Java Backgroud Irving TX Hybrid work 2 positions In Person Interview Preferred - Video Is OK Technical Business Analyst with 10 + yrs of exp - Heavy Java Backgroud Irving TX Hybrid work 2 positions In person Interview preferred - Video is OK");
        assertTrue(maxExperienceNeeded.isPresent());
        assertEquals(10,maxExperienceNeeded.get());
    }

    @Test
    void testGetContractDuration(){
        Optional<Integer> result = populator.getContractDuration("About the job Dice is the leading career destination for tech experts at every stage of their careers. Our client, Generis TEK Inc., is seeking the following. Apply via Dice today! Please Contact : To discuss this amazing opportunity, reach out to our Talent Acquisition Specialist Bhupendra Chopade at email address can be reached on # . We have Contract for API Developer-Remote for our client Washington D C. Please let me know if you or any of your friends would be interested in this position. Position Details: API Developer-Remote-Washington D C Location : Washington D.C- Remote Project Duration : 5+ months of contract Pay Range: : $56 an hour API Development Profile: Responsibilities and Requirements: String experience in working with AWS cloud platform and its feature which includes (but not limited to) API gateway, Lambda, SQS, SNS. AWS certified developer is preferred. Experience with REST and Graphql Hands on with python development and write unit tests. Work with technical leads on low-level design and develop code as per specification. Ability to debug complex problem and provide optimized solutions. Optional: Knowledge of AWS cloud development kit (CDK). Process Flows Mentor and Knowledge transfer to client project team members Participate as primary, co and/or contributing author on any and all project deliverables associated with their assigned areas of responsibility Participate in data conversion and data maintenance Provide best practice and industry specific solutions Advise on and provide alternative (out of the box) solutions Provide thought leadership as well as hands on technical configuration/development as needed. Participate as a team member of the functional team Perform other duties as assigned. To discuss this amazing opportunity, reach out to our Talent Acquisition Specialist Bhupendra Chopade at email address can be reached on # .");
        assertTrue(result.isPresent());
        assertEquals(5,result.get());
    }

    @Test
    void testGetContractDuration_DoubleMonths(){
        Optional<Integer> result = populator.getContractDuration("About the job Dice is the leading career destination for tech experts at every stage of their careers. Our client, Generis TEK Inc., is seeking the " +
                "following. Apply via Dice today! Please Contact : To discuss this amazing opportunity, reach out to our Talent Acquisition " +
                "Specialist Bhupendra Chopade at email address can be reached on # . We have Contract for " +
                "API Developer-Remote for our client Washington D C. Please let me know if you or any of your friends would " +
                "be interested in this position. Position Details: API Developer-Remote-Washington D C " +
                "Location : Washington D.C- Remote Project Duration : 12+ months of contract Pay Range: : $56 an hour API Development Profile: Responsibilities and Requirements: String experience in working with AWS cloud platform and its feature which includes (but not limited to) API gateway, Lambda, SQS, SNS. AWS certified developer is preferred. Experience with REST and Graphql Hands on with python development and write unit tests. Work with technical leads on low-level design and develop code as per specification. Ability to debug complex problem and provide optimized solutions. Optional: Knowledge of AWS cloud development kit (CDK). Process Flows Mentor and Knowledge transfer to client project team members Participate as primary, co and/or contributing author on any and all project deliverables associated with their assigned areas of responsibility Participate in data conversion and data maintenance Provide best practice and industry specific solutions Advise on and provide alternative (out of the box) solutions Provide thought leadership as well as hands on technical configuration/development as needed. Participate as a team member of the functional team Perform other duties as assigned. To discuss this amazing opportunity, reach out to our Talent Acquisition Specialist Bhupendra Chopade at email address can be reached on # .");
        assertTrue(result.isPresent());
        assertEquals(12,result.get());
    }
}
