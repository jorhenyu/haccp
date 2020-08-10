package com.jorhen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期常用方法
 * 
 * @author
 * 
 */

public class DateUtils {

	/**
	 * 常用變數
	 */
	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	

	/**
	 * 日期轉換為制定格式字串
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String formatDateToString(Date time, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(time);
	}
	
	public static String formatDateTommdd(String time) {
		String[] tokens = time.split("-");
		
	    StringBuffer sb = new StringBuffer();
	    sb.append(tokens[1].replaceFirst("^0*", ""));
	    sb.append(".");
	    sb.append(tokens[2].replaceFirst("^0*", ""));
		
		return sb.toString();
	}

}
