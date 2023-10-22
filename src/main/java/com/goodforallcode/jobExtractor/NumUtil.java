package com.goodforallcode.jobExtractor;

import org.apache.commons.lang3.math.NumberUtils;

public class NumUtil {
    public static Integer convertSalaryToLong(String salary){
        Integer result=null;
        String parseableSalary=salary.replaceAll("\\$","").replaceAll("Bonus","").replaceAll(" \\+","")
                .replaceAll(",","").replaceAll("\\.00","");
        if(parseableSalary.endsWith("K")){
            parseableSalary=parseableSalary.replaceAll("K","000");
        }
        int periodIndex=parseableSalary.indexOf(".");
        if(periodIndex>0){
            parseableSalary=parseableSalary.substring(0,periodIndex).trim();
        }
        if(NumberUtils.isCreatable(parseableSalary)) {
            result = Integer.parseInt(parseableSalary);
        }
        return result;
    }
}
