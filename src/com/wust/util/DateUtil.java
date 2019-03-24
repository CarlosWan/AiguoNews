package com.wust.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public DateUtil() {
		super();
		// TODO Auto-generated constructor
	}

	public static final String DEFAULT_DATE_SAMPLE_FORMAT = "yyyy-MM-dd";

	public static final String DEFAULT_TIME_SAMPLE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final java.text.SimpleDateFormat DATE_FORMATTER = new java.text.SimpleDateFormat(
			DEFAULT_DATE_SAMPLE_FORMAT);

	private static final java.text.SimpleDateFormat TIME_FORMATTER = new java.text.SimpleDateFormat(
			DEFAULT_TIME_SAMPLE_FORMAT);

	//转换日期到字符串  带十 分 秒
	public static String getTimeSampleString(java.util.Date date) {
		if (date == null)
			return null;
		return TIME_FORMATTER.format(date);
	}

	//转换日期到字符串  不带十 分 秒
	public static String getDateSampleString(java.util.Date date) {
		if (date == null)
			return null;
		return DATE_FORMATTER.format(date);
	}

	//按照指定格式化日期  返回日期字符串
	public static String getDateSampleString(java.util.Date date, String format) {
		if (date == null)
			return null;
		if (format == null)
			return getDateSampleString(date);
		java.text.SimpleDateFormat dateFormater = new java.text.SimpleDateFormat(
				format);
		return dateFormater.format(date);
	}

	//得到当前年份
	public static int getCurYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	//得到当前月份
	public static int getCurMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	//得到当前是几号
	public static int getCurDay() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		return day;
	}
	
	//得到当前小时
	public static int getCurHour() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR);
		return hour;
	}
	
	//得到当前分
	public static int getCurMinute() {
		Calendar cal = Calendar.getInstance();
		int minute = cal.get(Calendar.MINUTE);
		return minute;
	}

	//得到当前秒
	public static int getCurSecond() {
		Calendar cal = Calendar.getInstance();
		int second = cal.get(Calendar.SECOND);
		return second;
	}
	
	//转换日期字符串到Timestamp类型
	public static Timestamp getTimestamp(String dateTimeFormat) {
		String year = null, month = null, date = null, hour = null, minute = null, second = null;
		if (dateTimeFormat == null)
			return null;
		if (dateTimeFormat.length() > 3)
			year = dateTimeFormat.substring(0, 4);

		if (dateTimeFormat.length() > 6)
			month = dateTimeFormat.substring(5, 7);

		if (dateTimeFormat.length() > 9)
			date = dateTimeFormat.substring(8, 10);

		if (dateTimeFormat.length() > 12)
			hour = dateTimeFormat.substring(11, 13);

		if (dateTimeFormat.length() > 15)
			minute = dateTimeFormat.substring(14, 16);

		if (dateTimeFormat.length() > 18)
			second = dateTimeFormat.substring(17);
		StringBuffer str = new StringBuffer();

		if (year != null)
			str.append(year);

		if (month != null) {
			str.append("-");
			str.append(month);
		}

		if (date != null) {
			str.append("-");
			str.append(date);
		}

		if (hour != null) {
			str.append(" ");
			str.append(hour);
		}

		if (minute != null) {
			str.append(":");
			str.append(minute);
		}

		if (second != null) {
			str.append(":");
			str.append(second);
		}

		if (year != null)
			return Timestamp.valueOf(str.toString());
		else
			return null;
	}

	//转换日期字符串为Date类型 带 时分秒
	public static Date getDate(String time) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date date = null;
		try {
			date = bartDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	//转换日期字符串为Date类型 不带 时分秒
	public static Date getBirthDate(String time) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = bartDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	// 获取当前系统时间
	public static Date getCurrentTime() {
		Date dateTime = new Date();
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String todayAsString = bartDateFormat.format(dateTime);
		java.util.Date date = null;
		try {
			date = bartDateFormat.parse(todayAsString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static java.sql.Date toSqlDate(java.util.Date date){
		if (date!=null){
			return new java.sql.Date(date.getTime());
		}else
			return null;
	}
	
	public static  java.util.Date toUtilDate(java.sql.Date date){
		if (date!=null){
			return new java.util.Date(date.getTime());
		}else
			return null;
	}
	
	//将java.util.Date --> java.sql.Timestatmp
	public static java.sql.Timestamp toTimeStamp(java.util.Date date){
		if (date!=null){
			return new java.sql.Timestamp(date.getTime());
		}else
			return null;
	}

	public static void main(String[] args) {

		int year = DateUtil.getCurYear();
		int month = DateUtil.getCurMonth();
		int day = DateUtil.getCurDay();

		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(DateUtil.getCurHour());
		System.out.println(DateUtil.getCurMinute());
		System.out.println(DateUtil.getCurSecond());


		System.out.println(DateUtil.getTimeSampleString(new Date()));

		Date date1 = DateUtil.getDate("2011-11-8 12:26:21");
		System.out.println(DateUtil.getTimeSampleString(date1));

	}
}
