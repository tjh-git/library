package com.simple.bsp.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String getDate(String format) {
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		String strDate = dateformat.format(new Date());
		return strDate;
	}
}
