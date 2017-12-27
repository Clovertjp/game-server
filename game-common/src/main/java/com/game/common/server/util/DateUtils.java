package com.game.common.server.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 14-4-3.
 */
public class DateUtils {

	/**
	 * 14-12-4 下午7:24
	 * 
	 * @param time
	 * @return
	 */
	public static String fromTimeToStr(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 2014-12-04 19:24:29
	 * 
	 * @param time
	 * @return
	 */
	public static String fromTimeToStandardStr(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return sdf.format(calendar.getTime());
	}

	public static String fromTimeToFromatStr(long time, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 添加天数后按格式输出
	 * 
	 * @param time
	 * @param day
	 * @param format
	 * @return
	 */
	public static String addDayToFromatStr(long time, int day, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return sdf.format(calendar.getTime());
	}

	public static long parseTime(String timeStr, String formatStr) {
		SimpleDateFormat smdf = new SimpleDateFormat(formatStr);
		Date st = null;
		try {
			st = smdf.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return st.getTime();
	}

	/**
	 * MM-DD-HH-mm，表示在MM月DD天HH时mm分
	 * 
	 * @param timeStr
	 * @return
	 */
	public static long parseTime(String timeStr) {
		String[] timeArr = StringUtils.split(timeStr, "-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Integer.parseInt(timeArr[0]) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(timeArr[1]));
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArr[2]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(timeArr[3]));
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * YYYY-MM-DD-HH-MM 年-月-日-时-分
	 * 
	 * @param timeStr
	 * @return
	 */
	public static long parseFullTime(String timeStr) {
		String[] timeArr = StringUtils.split(timeStr, "-");
		if (timeArr.length < 5)
			return -1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(timeArr[0]));
		calendar.set(Calendar.MONDAY, Integer.parseInt(timeArr[1]) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(timeArr[2]));
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArr[3]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(timeArr[4]));
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * D-HH-MM，表示在每周第D天HH时MM分
	 * 
	 * @param timeStr
	 * @return
	 */
	public static long parseWeekTime(String timeStr) {
		String[] timeArr = StringUtils.split(timeStr, "-");
		Calendar calendar = Calendar.getInstance();
		int week = Integer.parseInt(timeArr[0]);
		int currWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.set(Calendar.DAY_OF_WEEK, week);
		if (currWeek > week) {
			calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		}
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArr[1]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(timeArr[2]));
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取某个时间戳的下一个周几： 周1 ~ 周日：1 ~ 7
	 * 
	 * @param millisTime
	 * @param dayOfWeekNum
	 * @return
	 */
	public static long getNextWeekNumTime(long millisTime, int dayOfWeekNum) {
		dayOfWeekNum = (dayOfWeekNum % 7) + 1;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millisTime);
		int currWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.set(Calendar.DAY_OF_WEEK, dayOfWeekNum);
		if (currWeek >= dayOfWeekNum) {
			calendar.add(Calendar.WEEK_OF_MONTH, 1);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取某个时间戳的上一个周几： 周1 ~ 周日：1 ~ 7
	 * 
	 * @param millisTime
	 * @param dayOfWeekNum
	 * @return
	 */
	public static long getLastWeekNumTime(Long millisTime, int dayOfWeekNum) {
		dayOfWeekNum = (dayOfWeekNum % 7) + 1;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millisTime);
		int currWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.set(Calendar.DAY_OF_WEEK, dayOfWeekNum);
		if (currWeek <= dayOfWeekNum) {
			calendar.add(Calendar.WEEK_OF_MONTH, -1);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取某个时间戳的上一个周几： 周1 ~ 周日：1 ~ 7
	 * 
	 * @param millisTime
	 * @param dayOfWeekNum
	 * @return
	 */
	public static Calendar getLastWeek(Long millisTime, int dayOfWeekNum) {
		dayOfWeekNum = (dayOfWeekNum % 7) + 1;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millisTime);
		int currWeek = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.set(Calendar.DAY_OF_WEEK, dayOfWeekNum);
		if (currWeek <= dayOfWeekNum) {
			calendar.add(Calendar.WEEK_OF_MONTH, -1);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	public static String unixTime2String(long time) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
	}

	public static long parseTimeInLiuWenStyle(String timeStr) throws ParseException {
		return parseFullTimeStr(timeStr, "yyyy-MM-dd-HH-mm");
	}

	public static long parseFullTimeStr(String fullTimeStr, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(fullTimeStr);
		return date.getTime();
	}

	public static long parseFullTimeStr(String fullTimeStr) throws ParseException {
		return parseFullTimeStr(fullTimeStr, "yyyy-MM-dd HH:mm:ss");
	}

	public static long parseTimeForCN(String timeStr) throws ParseException {
		long cnTime = parseFullTimeStr(timeStr);
		return cnTime - 8 * 3600 * 1000;
	}

	public static long parseTimeForTstore(String timeStr) throws ParseException {
		// 20150924113656
		long krTime = parseFullTimeStr(timeStr, "yyyyMMddHHmmss");
		return krTime - 9 * 3600 * 1000;
	}

	/**
	 * 获取下一天的零点
	 */
	public static long getNewDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTimeInMillis();
	}

	public static long getNewDay(int offset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, offset);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取某时间下一天的0点
	 * 
	 * @param time
	 * @return
	 */
	public static long getNewDay(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, 1);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取当天零点
	 */
	public static long getToday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取当天零点
	 */
	public static Calendar getTodayCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	/**
	 * 获取当前日历日期
	 * 
	 * @return
	 */
	public static int getCalDay() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		m++;
		int d = cal.get(Calendar.DATE);

		return y * 10000 + m * 100 + d;
	}

	/**
	 * 获取指定时间的日历日期
	 * 
	 * @return
	 */
	public static int getCalDay(long time) {
		Calendar cal = Calendar.getInstance();

		cal.setTimeInMillis(time);

		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		m++;
		int d = cal.get(Calendar.DATE);

		return y * 10000 + m * 100 + d;
	}

	public static int getTimeByField(int field) {
		Calendar cal = Calendar.getInstance();
		return cal.get(field);
	}

	/**
	 * 获取下一个整点的时间
	 */
	public static long getNextHour() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.HOUR_OF_DAY, 1);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取固定长整型时间的整点时间值
	 * 
	 * @param time
	 * @return
	 */
	public static long getHourTime(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();

	}

	/*
	 * 获取某天零点
	 */
	public static long getZeroClock(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static long getZeroClock(long dateInMillis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dateInMillis);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static boolean isSameDay(long day1, long day2) {
		return getZeroClock(day1) == getZeroClock(day2);
	}

	/**
	 * 周日 ~ 周六 : 1 ~ 7
	 */
	public static int getDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getWeekOfYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取第几小时：0 ~ 23
	 * 
	 * @return
	 */
	public static int getHourOfDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 周日 ~ 周六 : 1 ~ 7
	 */
	public static int getDayOfWeek(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/*
	 * 计算两个时间戳间隔几天
	 */
	public static int getDayInterval(long time1, long time2) {
		long zeroTime1 = getZeroClock(time1);
		long zeroTime2 = getZeroClock(time2);
		long interval = Math.abs(zeroTime2 - zeroTime1);
		int dayInterval = (int) (interval / 86400000);
		return dayInterval;
	}

	/**
	 * 取得当前日期所在周的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); // Sunday
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 取得当前日期所在周的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6); // Saturday
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取偏移 天数 后的的时间
	 * 
	 * @param time
	 * @param offset
	 *            正数为+天数，负数为-天数
	 * @return
	 */
	public static long getOffsetDayTime(long time, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		calendar.add(Calendar.DAY_OF_MONTH, offset);
		return calendar.getTimeInMillis();
	}

	public static int getDayOfMonth(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static void main(String[] args) {
		System.out.println(getTimeByField(Calendar.MINUTE));
	}
}
