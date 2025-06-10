package com.goodforallcode.jobExtractor.util;


public class BooleanUtil {
    public  boolean valuePopulatedAndTrue(Boolean value) {
        return value!=null && value;
    }

    public  boolean valuePopulatedAndFalse(Boolean value) {
        return value!=null && !value;
    }

    /**
     * Checks if at least one of the values is populated and true.
     * @param values
     * @return
     */
    public  boolean someValuesPopulatedAndTrue(Boolean... values) {
        boolean oneTrue = false;
        for(Boolean value : values) {
            if(valuePopulatedAndTrue(value)) {
                oneTrue=true;
                break;
            }
        }
        return oneTrue;
    }
}

