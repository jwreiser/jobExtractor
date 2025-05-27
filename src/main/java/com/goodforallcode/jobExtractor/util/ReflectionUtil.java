package com.goodforallcode.jobExtractor.util;

import com.goodforallcode.jobExtractor.model.Job;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ReflectionUtil {
    public static boolean isAttributeTrue(Object object, String propertyName) {
        boolean result=false;
        if(object!=null) {
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, object.getClass());
                Method getter = descriptor.getReadMethod();
                Object value = getter.invoke(object);
                if (value instanceof Boolean) {
                    result = (Boolean) value;
                }
            } catch (IntrospectionException|InvocationTargetException|IllegalAccessException e) {
                //leave as null
            }
        }
        return result;
    }

    public static Float getAttributeFloat(Object object, String propertyName) {
        Float result=null;
        if(object!=null) {
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, object.getClass());
                Method getter = descriptor.getReadMethod();
                Object value = getter.invoke(object);
                if (value instanceof Float) {
                    result = (Float) value;
                }
            } catch (IntrospectionException|InvocationTargetException|IllegalAccessException e) {
                //leave as null
            }
        }
        return result;
    }

    public static String getAttributeString(Object object, String propertyName) {
        String result=null;
        if(object!=null) {
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, object.getClass());
                Method getter = descriptor.getReadMethod();
                Object value = getter.invoke(object);
                if (value instanceof String) {
                    result = (String) value;
                }
            } catch (IntrospectionException|InvocationTargetException|IllegalAccessException e) {
                //leave as null
            }
        }
        return result;
    }
}
