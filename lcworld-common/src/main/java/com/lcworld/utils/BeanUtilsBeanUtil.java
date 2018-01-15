package com.lcworld.utils;

import java.math.BigDecimal;

import org.apache.commons.beanutils.BeanUtilsBean;

public class BeanUtilsBeanUtil {
    private static  BeanUtilsBean beanUtilsBean ;
    static {
        beanUtilsBean = new BeanUtilsBean(); 
        beanUtilsBean.getConvertUtils().register(new org.apache.commons.beanutils.converters.BigDecimalConverter(null), BigDecimal.class);  
        beanUtilsBean.getConvertUtils().register(new org.apache.commons.beanutils.converters.DateConverter(null), java.util.Date.class);  
          
        beanUtilsBean.getConvertUtils().register(new org.apache.commons.beanutils.converters.SqlTimestampConverter(null), java.sql.Timestamp.class);  
        beanUtilsBean.getConvertUtils().register(new org.apache.commons.beanutils.converters.SqlDateConverter(null), java.sql.Date.class);  
        beanUtilsBean.getConvertUtils().register(new org.apache.commons.beanutils.converters.SqlTimeConverter(null), java.sql.Time.class);  
        
    }
    
    
   
    
    public static void copyProperties(Object dest, Object orig) throws Exception{
        beanUtilsBean.copyProperties(dest, orig);
    }
}
