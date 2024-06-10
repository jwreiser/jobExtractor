package com.goodforallcode.jobExtractor.controller;

import com.goodforallcode.jobExtractor.extractor.Extractor;
import com.goodforallcode.jobExtractor.extractor.ExtractorFactory;
import com.goodforallcode.jobExtractor.extractor.JobResult;
import com.goodforallcode.jobExtractor.job.populate.JobInfoPopulator;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.QueryInput;
import com.goodforallcode.jobExtractor.util.CacheUtil;
import com.goodforallcode.jobExtractor.util.RESTUtil;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController()
public class ExtractionController {
    @Autowired
    ExtractorFactory extractorFactory;
    @Autowired
    CacheUtil cacheUtil;
    @GetMapping("/test/cache/contains")
    public String companySummaryTest(){
        String json="{\n" +
                "   \"url\" : \"http://linkedin.com/jobs/view/3859504408/?eBP=CwEAAAGO2Wot8LlgDiy3IpKmxuGKtS-SLKj0di9085TZ8FDIZi57mHZKfSPMGY71QKKIZWRz3vK2fAb3eVUixYWcijyC2Y3P10hiwdVS3REmjL_hvs1fbWKdKbtjazVMhixBMa9zy3_MoUsO16kBl_sHAvJszA-WOwyxdQMpN_aOFDUxYDGC2Q0KKv5_Cf2WU1tcPiNQEHuHLOACvkNR3F-HQApZ_HOe6hZ0DFBHvzayaeFbvTpcPDV-PChcGuJbK8SDWHVKhOSE-GUcRNCyWN_eyTVI2oOyJl9nhpQWXo3bGpejm8Ya_SymhxUoMuxfcEpBLVpL1RKScnw81f4ui5g3QyhYTk_TmFsT_Tg-8QDD6Yx6LrBftK8KCpcoyg&refId=gfiN2aW0V9uuXy8IiytF1w%3D%3D&trackingId=OLNyilb0okCbDBbSg3tyVw%3D%3D&trk=flagship3_job_collections_leaf_page\",\n" +
                "   \"location\" : \"Greater Chicago Area\",\n" +
                "   \"sourceUrl\" : \"https://www.linkedin.com/jobs/collections/higher-edu/?currentJobId=3848971164&discover=true&subscriptionOrigin=JOBS_HOME\",\n" +
                "   \"shallowExclude\" : false,\n" +
                "   \"easyApply\" : true,\n" +
                "   \"contract\" : false,\n" +
                "   \"remote\" : true,\n" +
                "   \"minimumNumEmployees\" : 1001,\n" +
                "   \"maximumNumEmployees\" : 5000,\n" +
                "   \"description\" : \"About the job Job Description: Senior Server Engineer Contract to hire - 100% remote VMware/Windows Build, update, and manage multiple VMware vSphere clusters including Azure AVS Provision on-premises and Azure cloud-based servers and services to support business applications Strong knowledge of Windows OS (2019/2016/2012/2008) and technologies such as Domains/Active Directory, DNS, DHCP/WINS, FTP/SFTP, SQL Server Strong knowledge & experience of Microsoft cluster technologies Knowledge and experience of IIS, .NET, Palo Alto firewall and Knowledge of F5 are a big plus Solid experience migrating/upgrading servers to higher OS versions Strong knowledge & experience with HP hardware. HP Synergy experience a plus Experience developing & maintaining Group Policy Objects Good working knowledge of networking functions (i.e. function of TCP/IP, SNMP), subnets, VLANS and routing. Manage VM snapshots. Perform system and data restores as required Azure Good understanding of Azure cloud architecture Experience with building & supporting PaaS, IaaS solutions in Azure Experience with RBAC, Azure AD Automation experience. Experience with scripting (PowerShell, JSON, Bicep & ARM) Experience with Azure Site recovery, Azure backup server Experience managing Azure costs Experience migrating applications/infrastructure from on premise to Azure Linux Experience building and managing Linux servers (Red Hat, CentOS and Ubuntu). Experience should include planning, configuring, maintaining, patching and monitoring Linux servers Security Good understanding of Anti-Virus software tools Experience with vulnerability management. Address security issues identified by Rapid7 & penetration tests. Others Incident and Problem management: take ownership and work with team to resolve production related issues. Service-Now experience a plus MCSE or Azure certification a plus\",\n" +
                "   \"skills\" : [ \"Domain Name System (DNS), Dynamic Host Configuration Protocol (DHCP), FTP, Internet Protocol Suite (TCP/IP), Networking, Role-Based Access Control (RBAC), SNMP, Secure File Transfer Protocol (SFTP), Transmission Control Protocol (TCP), and VM\" ],\n" +
                "   \"title\" : \"Sr. Server Engineer (VMware/Azure)\",\n" +
                "   \"companyName\" : \"The Judge Group\",\n" +
                "   \"industries\" : [ \"Staffing and Recruiting\" ],\n" +
                "   \"jobIndustry\" : \"Staffing and Recruiting\",\n" +
                "   \"excludeFilter\" : {\n" +
                "     \"name\" : \"ProgrammingLanguageFilter\"\n" +
                "   }\n" +
                " }";
        String result = RESTUtil.callUrlWithJson("http://cache:5088/contains/description/none",json,0);
        if (result == null) {
            System.err.println("Could not get cache result");
        }
        return result;
    }

    @GetMapping("/test/summary")
    public CompanySummary companySummaryTest2(){
        CompanySummary summary = RESTUtil.callUrl("http://summary:9999/company/summarize/Walmart");
        if (summary == null) {
            System.err.println("~~~~~~~~~~~~    Could not get summary    ~~~~~~~~~~~~");
        }else{
            System.out.println("Summary: " + summary);
        }
        return summary;
    }

    @GetMapping("/test/summary/add")
    public boolean companySummaryAddTest(){
        CompanySummary summary=new CompanySummary("Test Company");
        try {
            cacheUtil.addSummary(summary);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return true;
    }

    @GetMapping("/test/summary/add/remaining")
    public boolean companySummaryAddRemainingTest(){
        try {
            cacheUtil.addRemainingSummaries();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return true;
    }
    @GetMapping("/test/cache/add")
    public boolean cacheAddTest(){
        Job job=new Job("Test Title","Test Company","Test Location","https://www.linkedin.com/jobs/view/1234567890");
        job.setDescription("Test Description");
        try {
            cacheUtil.addJobToCache(job, false);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return true;
    }
    @GetMapping("/test/cache/add/include")
    public boolean cacheAddIncludeTest(){
        Job job=new Job("Test Title","Test Company","Test Location","https://www.linkedin.com/");
        job.setDescription("lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum ");
        job.setIndustries(List.of("Staffing and Recruiting","IT/Computer Science"));
        try {
            cacheUtil.addJobToCache(job, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return true;
    }

    @GetMapping("/test/cache/add/remaining")
    public boolean cacheAddRemainingTest(){
        try {
            cacheUtil.addRemainingJobs();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return true;
    }
    @PostMapping("/extract")
    public JobResult extractMatchingJobs(@RequestBody QueryInput queryInput){
        Extractor extractor= extractorFactory.getExtractor(queryInput.getUrls().get(0));
        extractor.setCacheUtil(cacheUtil);
        System.err.println("Logging in");
        WebDriver driver = extractor.login(queryInput.getUserName(), queryInput.getPassword());
        System.err.println("Logged in");
        Set<Cookie> cookies = driver.manage().getCookies();
        try{
            driver.quit();
        }catch(Exception ex){

        }
        JobResult result=extractor.getJobs(cookies,queryInput.getPreferences(), queryInput.getUrls());


        return result;
    }
}
