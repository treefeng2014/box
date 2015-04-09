package com.example.boxplatform.define;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
	
	public static String getDateEN() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = format1.format(new Date(System.currentTimeMillis()));
		return date1;// 2012-10-03 23:41:31
	}
	public static Date StrToDate(String str) {
		   
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = null;
	    try {
	     date = format.parse(str);
	    } catch (ParseException e) {
	     e.printStackTrace();
	    }
	    return date;
	 }

}
