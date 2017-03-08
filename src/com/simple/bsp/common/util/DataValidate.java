package com.simple.bsp.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class DataValidate {
	public static void main(String[] args) {
		//System.out.println(validateString("aaaaaa", 1));
		//System.out.println(isNumeric("123.1"));
		//System.out.println(checkDateFormatAndValite("1906-01-01 111","yyyy-MM-dd HH"));
	}
	//str表示传入参数，l表示限制长度
	public static boolean validateString(String str, int l) {
		if(str!=null&&!str.equals("")){
			if (str.length() <= l) {
				return true;
			} else {
				return false;
			}			
		}else{
			return false;
		}

	}
	//str表示传入参数
	public static boolean isNumeric(String str) {
		if(str!=null&&!str.equals("")){
			return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
		}else{
			return false;
		}
		 
	}
	//strDateTime表示传入参数，dateFormat表示日期格式，如strDateTime为1986-09-20 01   dateFormat为yyyy-MM-dd HH
    public static boolean checkDateFormatAndValite(String strDateTime,String dateFormat) {
        //update it according to your requirement.
		if(strDateTime!=null&&!strDateTime.equals("")){
	        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
	        try {
	            Date ndate = format.parse(strDateTime);
	            String str = format.format(ndate);
	            //System.out.println(ndate);
	            //System.out.println(str);
	            //System.out.println("strDateTime=" + strDateTime);
	            //success
	            if (str.equals(strDateTime))
	                return true;
	            //datetime is not validate
	            else
	                return false;
	        } catch (Exception e) {
	            e.printStackTrace();
	            //format error
	            return false;
	        }			
		}else{
			return false;
		}

    }
}
