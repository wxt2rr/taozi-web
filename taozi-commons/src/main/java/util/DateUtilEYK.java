package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间帮助类
 * 
 * @author yinyh
 * 
 */
public class DateUtilEYK {

	/**
	 * 系统结束时间
	 */
	public static final Date END_TIME = parseStrToDate("2099-12-31 23:59:59", DateFormatEnum.u);
	
	public enum DateFormatEnum  {
		/**
		 * yyyyMMdd
		 */
		yyyyMMdd("yyyyMMdd"),
		
		/**
		 * yyyy-MM-dd
		 */
		d("yyyy-MM-dd"),
		
		/**
		 * yyyyMMddHHmmss
		 */
		yyyyMMddHHmmss("yyyyMMddHHmmss"),
		/**
		 * yyMMddHHmmssSSS
		 */
		yyMMddHHmmssSSS("yyMMddHHmmssSSS"),
		/**
		 * yyyyMMddHHmmssSSS
		 */
		yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS"),
		/**
		 * yyyy/MM/dd HH:mm:ss
		 */
		cn_G("yyyy/MM/dd HH:mm:ss"),
		/**
		 * yyyy-MM-dd HH:mm:ss
		 */
		u("yyyy-MM-dd HH:mm:ss"),
		/**
		 * HH:mm:ss
		 */
		T("HH:mm:ss");

		private String value;

		private DateFormatEnum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}
	
	/**
	 * 获取指定格式的当前时间yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getCurrTime() {
		String s = getCurrTime(DateFormatEnum.yyyyMMddHHmmss);
		return s;
	}

	public static Date changeMonth(Date date, int changeCount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, changeCount);
		return cal.getTime();
	}


	public static Date changeDay(Date date, int changeCount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, changeCount);
		return cal.getTime();
	}

	public static Date changeHour(Date date, int changeCount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, changeCount);
		return cal.getTime();
	}

	public static Date changeMinute(Date date, int changeCount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, changeCount);
		return cal.getTime();
	}

	public static Date changeSeconds(Date date, int changeCount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, changeCount);
		return cal.getTime();
	}

	/**
	 * 获取指定格式的当前时间戳
	 * 
	 * @return
	 */
	public static String getTimeStamp() {
		Date now = new Date();
		String s = now.getTime() + "";
		return s;
	}

	/**
	 * 获取指定格式的当前时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrTime(DateFormatEnum format) {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat(format.getValue());
		String s = outFormat.format(now);
		return s;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String parseDateToStr(Date date, DateFormatEnum format) {
		SimpleDateFormat outFormat = new SimpleDateFormat(format.getValue());
		String s = outFormat.format(date);
		return s;
	}
	/**
	 * 
	 * @param date
	 * @param format 
	 * @return
	 * @author yunheng E-mail: admin@yyh.hk
	 * @version 创建时间：2016年1月15日 下午2:37:24
	 */
	public static String parseDateToStr(Date date, String format) {
		SimpleDateFormat outFormat = new SimpleDateFormat(format);
		String s = outFormat.format(date);
		return s;
	}
	
	


	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            时间戳 /毫秒
	 * @param format
	 * @return
	 */
	public static String parseLongToStr(long date, DateFormatEnum format) {
		Date newDate = new Date(date);
		SimpleDateFormat outFormat = new SimpleDateFormat(format.getValue());
		String s = outFormat.format(newDate);
		return s;
	}

	
	
	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            字符串格式日期 如:2015-11-16 20:20:57
	 * @param format
	 * @return
	 */
	public static Date parseStrToDate(String date, DateFormatEnum format) {
		return parseStrToDate(date,format.getValue());
	}

	/***
	 *
	 * @param date
	 * 			字符串格式日期 如:2015-11-16 20:20:57
	 * @param format
	 * 			格式如：yyyy-MM-dd
	 * @return
	 */
	public static Date parseStrToDate(String date, String format) {
		Date dt = null;
		if (date == null || date.length() == 0) {
			return dt;
		}
		try {

			SimpleDateFormat outFormat = new SimpleDateFormat(format);
			dt = outFormat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	
	/**
	 * 获取该日期是星期几 栗子：星期一返回1
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		int r = -1;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		r = cal.get(Calendar.DAY_OF_WEEK);
		r = r - 1;
		if (r == 0) {
			r = 7;
		}
		return r;
	}
	
	/**
	 * 获取某周周一的日期
	 * @return
	 */
	public static String getFirstDayOfWeek(int week){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int order_of_curDay = calendar.get(Calendar.DAY_OF_WEEK)-1;
		
		if(week==0){       //代表是本周
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-order_of_curDay+1);
		}else if(week>0){  //代表是前week周
			int day = week*7;
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-order_of_curDay+1-day);
		}else if(week<0){
			throw new IllegalStateException("参数不合法");
		}
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayOfCurWeek = sf.format(calendar.getTime());
		return firstDayOfCurWeek;
	}
	
	/**
	 * 获取当月第一天的日期
	 * @return
	 */
	public static String getFirstDayOfCurMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-day_of_month+1);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayOfCurMonth = sf.format(calendar.getTime());
		return firstDayOfCurMonth;
	}
	
	public static Date getLifeTime(){
		return parseStrToDate("2093-12-24 00:00:00", DateFormatEnum.u);
	}

	/**
	 * Description: 判断两个日期是否是同一天
	 * @author HLJ
	 * @date 2017/3/13 19:54
	 * @param date 日期1
	 * @param otherDate 日期2
	 * @return 比较结果：true同一天;false非同一天
	 */
	public static boolean isSameDay(Date date, Date otherDate) {
		if (date == null || otherDate == null) {
			return false;
		}

		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(otherDate);

		boolean sameDay = c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
		sameDay = sameDay && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
		sameDay = sameDay && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
		return sameDay;
	}

	/**
	 * Description: 获取当天时间的起始值
	 * @author HLJ
	 * @date 2017/3/14 11:37
	 * @return Date;如果当天是2017年1月1日，则返回"2017-01-01 00:00:00"的Date对象
	 */
	public static Date getCurDateBeginTime() {
		Date targetDate = null;

		try {
			targetDate = parseStrToDate(parseDateToStr(new Date(), DateFormatEnum.d).concat(" 00:00:00"),
					DateFormatEnum.u);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return targetDate;
	}

	/**
	 * Description: 获取当天时间的结束值
	 * @author HLJ
	 * @date 2017/4/17 15:54
	 * @return Date;如果当天是2017年1月1日，则返回"2017-01-01 23:59:59"的Date对象
	 */
	public static Date getCurDateEndTime() {
		Date targetDate = null;

		try {
			targetDate = parseStrToDate(parseDateToStr(new Date(), DateFormatEnum.d).concat(" 23:59:59"),
					DateFormatEnum.u);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return targetDate;
	}

	/**
	 * Description: 将日期值字符串转换为时间值(时分秒为 00:00:00)
	 * @author HLJ
	 * @date 2017/5/9 19:22
	 * @param dateStr 日期值(如 "2017-01-01")
	 * @return Date
	 */
	public static Date getDateBeginTime(String dateStr) {
		Date targetDate = null;

		try {
			dateStr += " 00:00:00";
			targetDate = parseStrToDate(dateStr, DateFormatEnum.u);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return targetDate;
	}

	/**
	 * Description: 将日期值字符串转换为时间值(时分秒为 23:59:59)
	 * @author HLJ
	 * @date 2017/5/9 19:22
	 * @param dateStr 日期值(如 "2017-01-01")
	 * @return Date
	 */
	public static Date getDateEndTime(String dateStr) {
		Date targetDate = null;

		try {
			dateStr += " 23:59:59";
			targetDate = parseStrToDate(dateStr, DateFormatEnum.u);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return targetDate;
	}

	/**
	 * Description: 获取当天日期的字符串
	 * @author HLJ
	 * @date 2017/4/24 14:21
	 * @return 返回结果形式：yyyy-MM-dd
	 */
	public static String getCurDateStr() {
		return parseDateToStr(new Date(), DateFormatEnum.d);
	}

	/**
	 * Description: 获取当前时间的字符串
	 * @author HLJ
	 * @date 2017/4/6 18:32
	 * @return 时间的字符串 (形如 yyyy-MM-dd HH:mm:ss)
	 */
	public static String getNowStr() {
		return parseDateToStr(new Date(), DateFormatEnum.u);
	}

	/**
	 * Description: 判断参数日期是否在系统时间之后（只比较年月日）
	 * @author HLJ
	 * @date 2017/5/23 19:07
	 * @param date 参数日期
	 * @return 比较结果：true参数日期在系统时间之后;false参数日期在系统时间之前（或同一天）
	 */
	public static boolean afterCurDate(Date date) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(new Date());

		// 先比较年份值
		if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
			return true;
		} else if (c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR)) {
			return false;
		}

		// 再比较月份值
		if (c1.get(Calendar.MONTH) > c2.get(Calendar.MONTH)) {
			return true;
		} else if (c1.get(Calendar.MONTH) < c2.get(Calendar.MONTH)) {
			return false;
		}

		// 最后比较天数值
		return c1.get(Calendar.DAY_OF_MONTH) > c2.get(Calendar.DAY_OF_MONTH);
	}

	/*****   下面工具方法用于数据统计      ********/
	
	/**
	 * 根据日期获取属于当年第几周
	 * @author xiaodong
	 * @param dateStr  日期字符串-格式： yyyy-MM-dd
	 * @return
	 */
	public static Integer getWhichWeekByDate(String dateStr){
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		int num = 0;
		try{
			Date date = formatStr2Date(dateStr);
			calendar.setTime(date);
			num = calendar.get(Calendar.WEEK_OF_YEAR);// 设置周一为第一天 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * 把字符串格式化为Date类型
	 * @author xiaodong
	 * @param dateStr  日期字符串-格式： yyyy-MM-dd
	 * @return
	 */
	public static Date formatStr2Date(String dateStr){
		return formatStr2Date(dateStr, DateFormatEnum.d);
	}
	
	/**
	 * 把字符串格式化为Date类型
	 * @author xiaodong
	 * @param dateStr  日期字符串-格式： yyyy-MM-dd
	 * @param dateFormat  日期格式枚举：DateFormatEnum
	 * @return
	 */
	public static Date formatStr2Date(String dateStr, DateFormatEnum dateFormat){
		SimpleDateFormat format = new SimpleDateFormat(dateFormat.value);
		try {
			Date date = format.parse(dateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 根据日期区间，算出周的区间段
	 * @author xiaodong
	 * @param start 起始日期-格式： yyyy-MM-dd
	 * @param end   截止日期-格式： yyyy-MM-dd
	 * @return
	 */
	public static List<String> getWeekRegionByDateRegion(String start, String end){
		Calendar cal = Calendar.getInstance();
		cal.setTime(formatStr2Date(start));
		Integer year = cal.get(Calendar.YEAR);
		
		Integer startWeek = getWhichWeekByDate(start);
		Integer endWeek = getWhichWeekByDate(end);

		List<String> regionList = new LinkedList<String>();
		for(int i = startWeek; i<= endWeek ; i++){
			String weekStr = year.toString() + "-" + fillZero(i);
			regionList.add(weekStr);
		}
		return regionList;
	}
	
	/**
	 * 为一位正数填充0
	 * @author xiaodong
	 * @param single
	 * @return
	 */
	private static String fillZero(Integer single){
		if(single < 0){
			return "";
		}
		int len = single.toString().length();
		if(len > 1){
			return single.toString();
		} 
		return "0" + single.toString();
	}
	
	/**
	 * 获取时间段内所有日期
	 * @param start 起始日期-格式： yyyy-MM-dd
	 * @param end   截止日期-格式： yyyy-MM-dd
	 * @return
	 */
	public static List<String> getDayRegion(String start, String end) {
		List<String> lDate = new ArrayList<String>();
		if(start.equalsIgnoreCase(end)){
			lDate.add(start);
			return lDate;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormatEnum.d.value);
			
			Date dBegin = dateFormat.parse(start);
			Date dEnd = dateFormat.parse(end);
			Calendar calBegin = Calendar.getInstance();
			calBegin.setTime(dBegin);

			// 测试此日期是否在指定日期之后
			while (dEnd.after(calBegin.getTime()) || dEnd.equals(calBegin.getTime())) {
				Date cur = calBegin.getTime();
				lDate.add(dateFormat.format(cur));
				calBegin.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return lDate;
	}

	public static String getDateStrByStartStrAndNDay(String startStr, int n){
		return parseDateToStr(getDateByStartStrAndNDay(startStr, n), DateFormatEnum.d);
	}
	
	public static Date getDateByStartStrAndNDay(String startStr, int n){
		Date start = formatStr2Date(startStr);
		return getDateByStartAndNDay(start, n);
	}
	
	public static Date getDateByStartAndNDay(Date start, int n){
		if(start == null){
			start = getCurDateBeginTime();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		// n>0 表示n天以后，n<0表示n天以前
		calendar.add(Calendar.DATE, n);
		Date nDay = calendar.getTime();
		return nDay;
	}

	/**
	 * 取明天
	 * @return
	 */
	public static String getTomorrowStr(){
		return parseDateToStr(getTomorrow(), DateFormatEnum.d);
	}
	
	/**
	 * 取明天
	 * @return
	 */
	public static Date getTomorrow(){
		return getDateByStartAndNDay(getCurDateBeginTime(), 1);
	}
	
	/**
	 * 取昨天
	 * @param
	 * @return
	 */
	public static Date getYesterday(){
		return getDateByStartAndNDay(getCurDateBeginTime(), -1);
	}
	
	/**
	 * 取昨天
	 * @param
	 * @return
	 */
	public static String getYesterdayStr(){
		return parseDateToStr(getYesterday(), DateFormatEnum.d);
	}
	
	/**
	 * 根据日期取昨天
	 * @param day
	 * @return
	 */
	public static Date getYesterdayByDay(String day){
		Date today = formatStr2Date(day);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, -1);
		Date yesterday = calendar.getTime();
		return yesterday;
	}
	
	/**
	 * 根据日期取昨天
	 * @param day
	 * @return
	 */
	public static String getYesterdayStrByDay(String day){
		return parseDateToStr(getYesterdayByDay(day), DateFormatEnum.d);
	}
	
	/**
	 * 根据日期取明天
	 * @param day
	 * @return
	 */
	public static Date getTommorrowByDay(String day){
		Date today = formatStr2Date(day);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.DATE, 1);
		Date tommorrow = calendar.getTime();
		return tommorrow;
	}
	
	/**
	 * 根据日期取明天
	 * @param day
	 * @return
	 */
	public static String getTommorrowStrByDay(String day){
		return parseDateToStr(getTommorrowByDay(day), DateFormatEnum.d);
	}
	
	/**
	 * 将日期区间按照周期进行切割
	 * @param region
	 * @param cycle
	 * @return
	 */
	public static List<String> separate2Cycle(List<String> region, Integer cycle){
		List<String> weekList = new LinkedList<String>();
		int len = region.size();
		int multiple = len / cycle; // 倍数
		int mod = len % cycle; // 余数
		int left = mod == 0 ? 0 : 1;
		int times = multiple + left;
		// 循环cycle的倍数
		for(int i = 0;i < times;i++){
			int  index = i*cycle;
			if(index >= len){
				index = len - 1;
			}
			String cycleStart = region.get(index);
			
			if(i == (times - 1) && mod != 0){
				cycle = mod;
			}
			String cycleEnd = DateUtilEYK.getDateStrByStartStrAndNDay(cycleStart, cycle - 1);
			String key = cycleStart + "\n" + cycleEnd;
			weekList.add(key);
		}
		
		return weekList;
	}
	
	public static List<String> separate2Week(List<String> region){
		// 将日期按照周期进行分割，并且每个周期进行累加
		// 累加完后，进入下一个周期
		return separate2Cycle(region, 7);
	}
	
	public static List<String> separate2Month(List<String> region){
		// 将日期按照周期进行分割，并且每个周期进行累加
		// 累加完后，进入下一个周期
		return separate2Cycle(region, 30);
	}
}
