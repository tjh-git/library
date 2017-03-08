/*
 */
package com.simple.bsp.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author weidong
 */
public final class DateTool {
	
    /**
     * 获取当前系统日期 返回 8位 like 20050101
     * 
     * @return
     */
    public static String getToday() {
    	SimpleDateFormat dateTime = new SimpleDateFormat("yyyyMMdd");
		String today = dateTime.format(new Date());
        return today;
    }
    
    /**
     * 获取输入日期的下一天 返回 8位 like 20050101
     * 
     * @param today
     * @return
     */
    public static String getNextDay(String day) {
        return getNextDay(day, 1);
    }

    /**
     * 获取输入日期的下 n 天 返回 8位 like 20050101
     * 
     * @param day
     * @param n
     * @return
     */
    public static String getNextDay(String day, int n) {
        if (day == null || "".equals(day) || day.length() != 8) {
            throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
        }
        try {
            String sYear = day.substring(0, 4);
            int year = Integer.parseInt(sYear);
            String sMonth = day.substring(4, 6);
            int month = Integer.parseInt(sMonth);
            String sDay = day.substring(6, 8);
            int dday = Integer.parseInt(sDay);
            Calendar cal = Calendar.getInstance();
            cal.set(year, month - 1, dday);
            cal.add(Calendar.DATE, n);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            return df.format(cal.getTime());

        } catch (Exception e) {
            throw new RuntimeException("进行日期运算时输入得参数不符合系统规格." + e);

        }
    }

    /**
     * 获取输入 月份的下 n 月份 返回 6位 like 200501
     * 
     * @param month
     *            like 200404
     * @param n
     * @return
     */
    public static String getNextMonth(String month, int n) {
        if (month == null || "".equals(month) || month.length() != 6) {
            throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的月份换算.");
        }
        try {
            String sYear = month.substring(0, 4);
            int year = Integer.parseInt(sYear);
            String sMonth = month.substring(4, 6);
            int mon = Integer.parseInt(sMonth);
            Calendar cal = Calendar.getInstance();
            cal.set(year, mon - 1, 1);
            cal.add(Calendar.MARCH, n);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
            return df.format(cal.getTime());

        } catch (Exception e) {
            throw new RuntimeException("进行月份运算时输入得参数不符合系统规格." + e);

        }
    }

    /**
     * 返回星期 0 星期天 6 星期陆
     * 
     * @param date
     *            20040101
     * @return
     */
    public static int getWeekday(String date) {
        if (date == null || date.length() != 8) {
            throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的月份换算.");
        }
        String sYear = date.substring(0, 4);
        int year = Integer.parseInt(sYear);
        String sMonth = date.substring(4, 6);
        int mon = Integer.parseInt(sMonth);
        String sDay = date.substring(6, 8);
        int dday = Integer.parseInt(sDay);
        Calendar cal = Calendar.getInstance();
        cal.set(year, mon - 1, dday);
        int weekday = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekday;

    }


    public static Date getDate(String date) {
        if (date == null || date.length() != 8) {
            throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的月份换算.  "+date);
        }
        String sYear = date.substring(0, 4);
        int year = Integer.parseInt(sYear);
        String sMonth = date.substring(4, 6);
        int mon = Integer.parseInt(sMonth);
        String sDay = date.substring(6, 8);
        int dday = Integer.parseInt(sDay);
        Calendar cal = Calendar.getInstance();
        cal.set(year, mon - 1, dday);
        return cal.getTime();
    }
    /**
     * 
     * @param time
     * @return
     */
    public static Date getTime(String time){
        Calendar cal = Calendar.getInstance();
        //cal.set(year, mon - 1, dday);
        return cal.getTime();
    }
    /**
     * 获取输入 月份的前 n 月份 返回 6位 like 200501
     * 
     * @param month
     * @param n
     * @return
     */
    public static String getPreviousMonth(String month, int n) {
        return getNextMonth(month, -n);
    }

    /**
     * 获取输入日期的嵌 n 天 返回 8位 like 20050101
     * 
     * @param day
     * @param n
     * @return
     */
    public static String getPreviousDay(String day, int n) {
        return getNextDay(day, -n);
    }



    /**
     * 获取当前系统时间 返回 12:12:12
     * 
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * @param ctm
     *            long 系统时间
     * @param format
     * @return
     */
    public static String getTimeMillisAsString(long ctm, String format) {
        Date date = new Date(ctm);
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }    
    /**
     * 日期 和 格式得到相应的字符日期     * 
     * @param date
     * @param format
     * @return
     */
    public static String getDateAsString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
    /**
     * 获取当前系统时间 返回 yyyyMMdd HH:mm:ss
     * 
     * @return
     */
    public static String getCurrentTimeMillisAsString() {
        long currTimeM = getCurrentTimeMillis();
        return getTimeMillisAsString(currTimeM, "yyyyMMdd HHmmss");
    }

    /**
     * 获取当前系统时间 参数 format yyyyMMdd HHmmssS 其中的部分 返回与 format格式相同的时间
     * 
     * @return
     */
    public static String getCurrentTimeMillisAsString(String format) {
        long currTimeM = getCurrentTimeMillis();
        return getTimeMillisAsString(currTimeM, format);
    }
    //日期格式校验
    public static boolean checkDate(String sourceDate){
        if(sourceDate==null){
            return false;
        }
        if(sourceDate.length()>10){
        	return false;
        }
        try {
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
               dateFormat.setLenient(false);
               dateFormat.parse(sourceDate);
               return true;
        } catch (Exception e) {
        }
         return false;
    }
    //日期格式校验
    public static boolean checkDates(String sourceDate){
        if(sourceDate==null){
            return false;
        }
        try {
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
               dateFormat.setLenient(false);
               dateFormat.parse(sourceDate);
               return true;
        } catch (Exception e) {
        }
         return false;
    }
    //数字格式校验
    public static boolean digitCheck(String input) {
        if(input==null){
            return false;
        }
		char c0 = input.charAt(0); 
		if( (c0 < '1' || c0 > '9') ) { 
			return false; 
		} 
    	for(int i = 1; i < input.length(); i++) { 
    		char c = input.charAt(i); 
    		if( (c < '0' || c > '9') ) { 
    			return false; 
    		} 
    	} 
    	return true; 
    }
    //小数格式校验
    public static boolean checkNum(String input) {
    	if(input==null||input.length()==0){
            return false;
        }
    	for(int i = 0; i < input.length(); i++) { 
    		char c = input.charAt(i); 
    		if( (c < '0' || c > '9') ) { 
    			if(c!='.'){
    				return false; 
    			}
    			
    		} 
    	} 
    	return true; 
    }
    //长度校验
    public static boolean lengthCheck(String input,int length) {
        if(input==null){
            return false;
        }		
		if( input.getBytes().length>length ) { 
			return false; 
		} 
    	return true; 
    }
    //长度校验
    public static boolean lengthChecks(String input,int length) {
        if(input==null|| input.trim().length() == 0){
            return false;
        }		
		if( input.getBytes().length>length ) { 
			return false; 
		} 
    	return true; 
    }
    public static void main(String[] args) {
        //        System.out.println(DateTool.getNextDay("20040229"));
        //        System.out.println(DateTool.getNextDay("20040229", 4));
        //        System.out.println(DateTool.getPreviousDay("20040229", 4));
        //        System.out.println(DateTool.getCurrentTime());
        //        System.out.println(DateTool.getToday());
        //        System.out.println(DateTool.getCurrentTimeMillis());
        //        System.out.println(DateTool.getCurrentTimeMillisAsString());
        //        System.out.println(DateTool.getCurrentTimeMillisAsString("yyyyMM"));
        //        System.out.println(DateTool.getNextMonth("200401", 0));
        //        System.out.println(DateTool.getNextMonth("200401", -6));
    			//Date date=new  Date("04/01/1992");
                //System.out.println(DateTool.getDateAsString(date,"yyyyMMdd"));
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	String strDate = "20150317";

    	try {
			Date date1=sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    	System.out.println(daysBetween("20150317", "20150318"));
        
//
//        Lunar lunar = DateTool.getLunar("20050208");
//        System.out.println(lunar.getYear());
//        System.out.println(lunar.getMonth());
//        System.out.println(lunar.getDay());
//        System.out.println(lunar.getIsLeap());
//        System.out.println(lunar.monthDays(lunar.getYear(), lunar.getMonth()));
//        System.out.println(lunar.lYearDays(lunar.getYear()));
//        System.out.println(lunar.getLunar(2005, 2, 8));
    }
    
	public static int daysBetween(String dateQ, String dateZ){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dateDQ=new Date();
		Date dateDZ=new Date();
		try {
			dateDQ = sdf.parse(dateQ);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dateDZ = sdf.parse(dateZ);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateDQ);
		long time1 = cal.getTimeInMillis();
		cal.setTime(dateDZ);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1)/(1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	
	
	
	/**
	 * 
	 * 方法名:       
	 * 功能描述:    两个参数格式必须为20050827，而且不能为空。
	 * 参数说明：   第一个参数小于第二个参数返回true
	 * 返回值:      // 函数返回值的说明
	 * 其他:        // 其它说明
	 */
    public static boolean lessThan(String preDate, String date) {
    	if(preDate == null && date == null){
    		return false;
    	}
    	else if(preDate == null){
    		return true;
    	}
    	else if(date == null){
    		return false;
    	}
    	preDate = preDate.trim();
    	date = date.trim();
    	if(preDate.length() == 8 && date.length() == 8){
            Integer date1 = new Integer(preDate);
            Integer date2 = new Integer(date);
            if(date1.compareTo(date2) < 0){
            	return true;
            }
    	}
        return false;
    }

	
	/**
	 * 
	 * 方法名:       
	 * 功能描述:    两个参数格式必须为20050827，而且不能为空。
	 * 参数说明：   第一个参数大于第二个参数返回true
	 * 返回值:      // 函数返回值的说明
	 * 其他:        // 其它说明
	 */
    public static boolean greaterThan(String preDate, String date) {
    	if(preDate == null && date == null){
    		return false;
    	}
    	else if(preDate == null){
    		return false;
    	}
    	else if(date == null){
    		return true;
    	}
    	preDate = preDate.trim();
    	date = date.trim();
    	if(preDate.length() == 8 && date.length() == 8){
            Integer date1 = new Integer(preDate);
            Integer date2 = new Integer(date);
            if(date1.compareTo(date2) > 0){
            	return true;
            }
    	}
        return false;
    }

	
	/**
	 * 
	 * 方法名:       
	 * 功能描述:    两个参数格式必须为20050827，而且不能为空。
	 * 参数说明：
	 * 返回值:      // 函数返回值的说明
	 * 其他:        // 其它说明
	 */
    public static boolean equal(String preDate, String date) {
    	if(preDate == null && date == null){
    		return false;
    	}
    	else if(preDate == null){
    		return false;
    	}
    	else if(date == null){
    		return true;
    	}
    	preDate = preDate.trim();
    	date = date.trim();
    	if(preDate.length() == 8 && date.length() == 8){
            Integer date1 = new Integer(preDate);
            Integer date2 = new Integer(date);
            if(date1.compareTo(date2) == 0){
            	return true;
            }
    	}
        return false;
    }
    
    /**
     * 
     * @param 19位的时间 yyyy-MM-dd HH:mm:ss
     * 
     * @return 15位的时间 yyyyMMdd HHmmss
     */
	public static String _time19To15(String time_19) {
	    String time_15 = "";
		if( time_19 == null || "".equals(time_19) || time_19.length() != 19 ) {
			time_15 = "";		
		}else {
		    String[] r = time_19.replace('-','#').replace(':','#').split("#");
		    for(int i=0;i<r.length;i++)
		    {
		    	time_15 += r[i];
		    }
		}
	    return time_15;
	}

    /**
     * 
     * 
     * @param 15位的时间 yyyyMMdd HHmmss
     * 
     * @return 19位的时间 yyyy-MM-dd HH:mm:ss
     */
	public static String _time15To19(String time_15) {
		String time_19 = "";
		if( time_15 == null || "".equals(time_15) || time_15.length() != 15 ) {
			time_19 = "";
		}else {
			String y = time_15.substring(0, 4);
			String m = time_15.substring(4, 6);
			String d = time_15.substring(6, 8);
			String h = time_15.substring(9, 11);
			String mi = time_15.substring(11, 13);
			String s = time_15.substring(13, 15);
		    time_19 = y+"-"+m+"-"+d+" "+h+":"+mi+":"+s;
		}
	    return time_19;
	}

    /**
     * 
     * @param 16位的时间 yyyy-MM-dd HH:mm
     * 
     * @return 13位的时间 yyyyMMdd HHmm
     */
	public static String _time16To13(String time_16) {
	    String time_13 = "";
		if( time_16 == null || "".equals(time_16) || time_16.length() != 16 ) {
			time_13 = "";		
		}else {
		    String[] r = time_16.replace('-','#').replace(':','#').split("#");
		    for(int i=0;i<r.length;i++)
		    {
		    	time_13 += r[i];
		    }
		}
	    return time_13;
	}

    /**
     * 
     * 
     * @param 13位的时间 yyyyMMdd HHmm
     * 
     * @return 16位的时间 yyyy-MM-dd HH:mm
     */
	public static String _time13To16(String time_13) {
		String time_16 = "";
		if( time_13 == null || "".equals(time_13) || time_13.length() != 13 ) {
			time_16 = "";
		}else {
			String y = time_13.substring(0, 4);
			String m = time_13.substring(4, 6);
			String d = time_13.substring(6, 8);
			String h = time_13.substring(9, 11);
			String mi = time_13.substring(11, 13);
		    time_16 = y+"-"+m+"-"+d+" "+h+":"+mi;
		}
	    return time_16;
	}	
	
    /**
     * 
     * 
     * @param 10位的日期 yyyy-MM-dd
     * 
     * @return 8位的日期 yyyyMMdd
     */
	public static String _date10To8(String date_10) {
		String date_8 = "";
		if( date_10 == null || "".equals(date_10) || date_10.length() != 10 ) {
			date_8 = "";
		}else {
		    String[] r = date_10.split("-");
		    for(int i=0;i<r.length;i++)
		    {
		    	date_8 += r[i];
		    }
		}
		return date_8;
	}
    /**
     * 
     * 
     * @param 8位的日期 yyyyMMdd
     * 
     * @return 10位的日期 yyyy-MM-dd
     */
	public static String _date8To10(String date_8) {
		String date_10 = "";
		if( date_8 == null || "".equals(date_8) || date_8.length() != 8 ) {
			date_10 = "";
		}else {
			String y = date_8.substring(0, 4);
			String m = date_8.substring(4, 6);
			String d = date_8.substring(6, 8);
			date_10 = y+"-"+m+"-"+d;
		}
		return date_10;
	}
	
    /**
     * 
     * 
     * @param 7位的日期 yyyy-MM
     * 
     * @return 6位的日期 yyyyMM
     */
	public static String _date7To6(String date_7) {
		String date_6 = "";
		if( date_7 == null || "".equals(date_7) || date_7.length() != 7 ) {
			date_6 = "";
		}else {
		    String[] r = date_7.split("-");
		    for(int i=0;i<r.length;i++)
		    {
		    	date_6 += r[i];
		    }
		}
		return date_6;
	}
    /**
     * 
     * 
     * @param 6位的日期 yyyyMM
     * 
     * @return 7位的日期 yyyy-MM
     */
	public static String _date6To7(String date_6) {
		String date_7 = "";
		if( date_6 == null || "".equals(date_6) || date_6.length() != 6 ) {
			date_7 = "";
		}else {
			String y = date_6.substring(0, 4);
			String m = date_6.substring(4, 6);
			date_7 = y+"-"+m;
		}
		return date_7;
	}	
    
}