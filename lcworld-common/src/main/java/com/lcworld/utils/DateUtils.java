package com.lcworld.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    public static Date getDate(Date date, int calendarType, int amount) {
    	if(date != null){
    		Calendar calen = Calendar.getInstance();
    		calen.setTime(date);
    		calen.add(calendarType, amount);
    		return calen.getTime();
    	}
    	return null;
    }
    public static Date parse(String date, String pattern) {
    	if(date != null){
    		SimpleDateFormat df = new SimpleDateFormat(pattern);
    		try {
				return df.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
    	}
    	return null;
    }

	public static Integer compareTo(String s1,String s2,String pattern){
		java.text.DateFormat df=new java.text.SimpleDateFormat(pattern);
		java.util.Calendar c1=java.util.Calendar.getInstance();
		java.util.Calendar c2=java.util.Calendar.getInstance();
		try
		{
			c1.setTime(df.parse(s1));
			c2.setTime(df.parse(s2));
		}catch(java.text.ParseException e){
			System.err.println("格式不正确");
		}
		int result=c1.compareTo(c2);
		/*if(result==0)
			System.out.println("c1相等c2");
		else if(result<0)
			System.out.println("c1小于c2");
		else
			System.out.println("c1大于c2");*/
		return result;
	}
    

	/**
	 * 得到两个日期的间隔天数
	 */
	public static int getDaySub(Date startDate, Date endDate) {
		int day = 0;
		try {
			day = (int) ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
		} catch (Exception e) {
			return 0;
		}
		return day+1;
	}
	
	public static void main(String[] args) {
		Date startDate = parse("2018-01-11", "yyyy-MM-dd");
		Date endDate = parse("2018-01-15", "yyyy-MM-dd");
		System.out.println(getDaySub(startDate, endDate));
	}
}
