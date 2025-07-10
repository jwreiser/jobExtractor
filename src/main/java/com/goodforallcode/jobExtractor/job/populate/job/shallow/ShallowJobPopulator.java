package com.goodforallcode.jobExtractor.job.populate.job.shallow;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.BooleanUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeoutException;

public interface ShallowJobPopulator {
    Job populateJob(WebElement webElement,Element element, WebDriver driver, Preferences preferences, Integer jobIndex) throws TimeoutException;
    BooleanUtil booleanUtil = new BooleanUtil();
    default  void populateSalaryInformation(Job job, String salaryText) {
        salaryText=salaryText.toLowerCase().replace(",","").replaceAll("starting at","").replaceAll("up to","").replaceAll("from","").trim();
        String editedSalaryText=salaryText.replace("$", "").replaceAll("pay range:","").replaceAll("pay:","").trim();
        Boolean isYearly=null;
        int locationOBeyondSalaryText=locationOBeyondSalaryText= editedSalaryText.lastIndexOf("per hour");
        locationOBeyondSalaryText= editedSalaryText.lastIndexOf("per week");
        if (locationOBeyondSalaryText != -1) {
            System.err.println("Salary text"+salaryText+",  is not supported yet.");
            return;//I don't know how to handle this, so just return
        }

        locationOBeyondSalaryText= editedSalaryText.lastIndexOf("/wee");
        if (locationOBeyondSalaryText != -1) {
            System.err.println("Salary text"+salaryText+",  is not supported yet.");
            return;//I don't know how to handle this, so just return
        }

        locationOBeyondSalaryText= editedSalaryText.lastIndexOf("per month");
        if (locationOBeyondSalaryText != -1) {
            System.err.println("Salary text"+salaryText+",  is not supported yet.");
            return;//I don't know how to handle this, so just return
        }

        locationOBeyondSalaryText= editedSalaryText.lastIndexOf("per day");
        if (locationOBeyondSalaryText != -1) {
            System.err.println("Salary text"+salaryText+",  is not supported yet.");
            return;//I don't know how to handle this, so just return
        }

        locationOBeyondSalaryText= editedSalaryText.lastIndexOf("per hour");
        if (locationOBeyondSalaryText != -1) {
            editedSalaryText = editedSalaryText.substring(0, locationOBeyondSalaryText).trim();
            isYearly=false;
        }
        locationOBeyondSalaryText= editedSalaryText.lastIndexOf("/hr");
        if (locationOBeyondSalaryText != -1) {
            editedSalaryText = editedSalaryText.substring(0, locationOBeyondSalaryText).trim();
            isYearly=false;
            editedSalaryText =editedSalaryText.replace("/hr", "");
        }
        locationOBeyondSalaryText= editedSalaryText.lastIndexOf("per year");
        if (locationOBeyondSalaryText != -1) {
            editedSalaryText = editedSalaryText.substring(0, locationOBeyondSalaryText).trim();
            isYearly=true;
        }
        locationOBeyondSalaryText= editedSalaryText.lastIndexOf("/yr");
        if (locationOBeyondSalaryText != -1) {
            editedSalaryText = editedSalaryText.substring(0, locationOBeyondSalaryText).trim();
            isYearly=true;
            if(editedSalaryText.contains(".")){
                editedSalaryText =editedSalaryText.replace(".", "").replace("k", "00");
            }
            editedSalaryText =editedSalaryText.replace("/yr", "");
        }
        locationOBeyondSalaryText= editedSalaryText.lastIndexOf("k");
        if (locationOBeyondSalaryText != -1) {
            editedSalaryText = editedSalaryText.substring(0, locationOBeyondSalaryText+1);
            isYearly=true;
        }
        editedSalaryText=editedSalaryText.replaceAll("k","000");
        String[] salaryParts = editedSalaryText.trim().split("-");

        String firstPart=salaryParts[0].trim();
        if (!editedSalaryText.isEmpty() && booleanUtil.valuePopulatedAndFalse(isYearly)) {
            if (salaryParts.length > 1) {
                job.setMinHourlySalary((int) Double.parseDouble(salaryParts[0].trim()));
                job.setMaxHourlySalary((int) Double.parseDouble(salaryParts[1].trim()));
            } else if (salaryParts.length == 1) {
                job.setMinHourlySalary((int) Double.parseDouble(firstPart));
            }
        } else if(NumberUtils.isCreatable(firstPart)) {
            if (!editedSalaryText.isEmpty() && booleanUtil.valuePopulatedAndTrue(isYearly)) {
                if (salaryParts.length > 1) {
                    job.setMinYearlySalary((int) Double.parseDouble(firstPart));
                    job.setMaxYearlySalary((int) Double.parseDouble(salaryParts[1].trim()));
                }
            } else if (salaryText.contains(".")) {
                if (salaryParts.length > 1) {
                    job.setMinHourlySalary((int) Double.parseDouble(firstPart));
                    job.setMaxHourlySalary((int) Double.parseDouble(salaryParts[1].trim()));
                } else if (salaryParts.length == 1) {
                    job.setMinHourlySalary((int) Double.parseDouble(firstPart));
                }
            } else {
                if (salaryParts.length > 1) {
                    job.setMinYearlySalary(Integer.parseInt(firstPart));
                    job.setMaxYearlySalary(Integer.parseInt(salaryParts[1].trim()));
                } else if (salaryParts.length == 1 && NumberUtils.isParsable(firstPart)) {
                    job.setMinYearlySalary(Integer.parseInt(firstPart));
                }
            }
        }
    }

}

