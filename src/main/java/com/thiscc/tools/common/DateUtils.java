package com.thiscc.tools.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	private int week;
	private static final int[] dayArray = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"MM/dd/yyyy");
	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm");
	public static final SimpleDateFormat DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");
	public static final SimpleDateFormat ORA_DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMdd");
	public static final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmm");
	public static final SimpleDateFormat ORA_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	public static final SimpleDateFormat CHN_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat CHN_DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat CHN_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat CHN_DATE_TIME_SHORT_EXTENDED_FORMAT = new SimpleDateFormat(
			"HH:mm:ss");

	public DateUtils() {
		today();
	}

	public DateUtils(String paramString) {
		SetDate(paramString);
	}

	public DateUtils(long paramLong) {
		setTimeInMillis(paramLong);
	}

	public DateUtils(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4, int paramInt5, int paramInt6) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.set(paramInt1, paramInt2 - 1, paramInt3, paramInt4,
				paramInt5, paramInt6);
		this.year = localCalendar.get(1);
		this.month = (localCalendar.get(2) + 1);
		this.day = localCalendar.get(5);
		this.hour = localCalendar.get(11);
		this.minute = localCalendar.get(12);
		this.second = localCalendar.get(13);
		this.week = localCalendar.get(3);
	}

	public void SetDate(String paramString) {
		int i;
		if (paramString.length() != 14)
			for (i = paramString.length(); i < 14; ++i)
				paramString = paramString + "0";
		try {
			i = Integer.parseInt(paramString.substring(0, 4));
			int j = Integer.parseInt(paramString.substring(4, 6));
			int k = Integer.parseInt(paramString.substring(6, 8));
			int l = Integer.parseInt(paramString.substring(8, 10));
			int i1 = Integer.parseInt(paramString.substring(10, 12));
			int i2 = Integer.parseInt(paramString.substring(12));
			Calendar localCalendar = Calendar.getInstance();
			localCalendar.set(i, j - 1, k, l, i1, i2);
			this.year = localCalendar.get(1);
			this.month = (localCalendar.get(2) + 1);
			this.day = localCalendar.get(5);
			this.hour = localCalendar.get(11);
			this.minute = localCalendar.get(12);
			this.second = localCalendar.get(13);
			this.week = localCalendar.get(3);
		} catch (Exception localException) {
			System.out.println(localException.getMessage());
		}
	}

	public String getDate() {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.set(this.year, this.month - 1, this.day, this.hour,
				this.minute, this.second);
		return CHN_DATE_TIME_EXTENDED_FORMAT.format(localCalendar.getTime());
	}

	private void today() {
		Calendar localCalendar = Calendar.getInstance();
		this.year = localCalendar.get(1);
		this.month = (localCalendar.get(2) + 1);
		this.day = localCalendar.get(5);
		this.hour = localCalendar.get(11);
		this.minute = localCalendar.get(12);
		this.second = localCalendar.get(13);
		this.week = localCalendar.get(3);
	}

	public String format(SimpleDateFormat paramSimpleDateFormat) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.set(this.year, this.month - 1, this.day, this.hour,
				this.minute, this.second);
		return paramSimpleDateFormat.format(localCalendar.getTime());
	}

	public String format2(String paramString) {
		Calendar localCalendar = Calendar.getInstance();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				paramString);
		localCalendar.set(this.year, this.month - 1, this.day, this.hour,
				this.minute, this.second);
		return localSimpleDateFormat.format(localCalendar.getTime());
	}

	public String toString() {
		return format(CHN_DATE_TIME_EXTENDED_FORMAT);
	}

	public int getDay() {
		return this.day;
	}

	public void setDay(int paramInt) {
		this.day = paramInt;
	}

	public int getHour() {
		return this.hour;
	}

	public void setHour(int paramInt) {
		this.hour = paramInt;
	}

	public int getMinute() {
		return this.minute;
	}

	public void setMinute(int paramInt) {
		this.minute = paramInt;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int paramInt) {
		this.month = paramInt;
	}

	public int getSecond() {
		return this.second;
	}

	public void setSecond(int paramInt) {
		this.second = paramInt;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int paramInt) {
		this.year = paramInt;
	}

	public int getWeek() {
		return this.week;
	}

	public Map getSeasonDay() {
		return getSeasonDay(this.month);
	}

	public Map getSeasonDay(int paramInt) {
		int i = getSeason(paramInt);
		int[][] arrayOfInt = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 },
				{ 10, 11, 12 } };
		int j = arrayOfInt[(i - 1)][0];
		int k = arrayOfInt[(i - 1)][2];
		Date localDate = new Date();
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy");
		String str = localSimpleDateFormat.format(localDate);
		int l = Integer.parseInt(str);
		int i1 = 1;
		int i2 = getLastDayOfMonth(l, k);
		HashMap localHashMap = new HashMap();
		localHashMap.put("start", l + "-" + j + "-" + i1);
		localHashMap.put("end", l + "-" + k + "-" + i2);
		return localHashMap;
	}

	public int getSeason() {
		return getSeason(this.month);
	}

	public int getSeason(int paramInt) {
		int i = 1;
		switch (paramInt) {
		case 1:
		case 2:
		case 3:
			i = 1;
			break;
		case 4:
		case 5:
		case 6:
			i = 2;
			break;
		case 7:
		case 8:
		case 9:
			i = 3;
			break;
		case 10:
		case 11:
		case 12:
			i = 4;
		}
		return i;
	}

	private int getLastDayOfMonth(int paramInt1, int paramInt2) {
		if ((paramInt2 == 1) || (paramInt2 == 3) || (paramInt2 == 5)
				|| (paramInt2 == 7) || (paramInt2 == 8) || (paramInt2 == 10)
				|| (paramInt2 == 12))
			return 31;
		if ((paramInt2 == 4) || (paramInt2 == 6) || (paramInt2 == 9)
				|| (paramInt2 == 11))
			return 30;
		if (paramInt2 == 2) {
			if (isLeapYear(paramInt1))
				return 29;
			return 28;
		}
		return 0;
	}

	public long getTimeInMillis() {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.set(this.year, this.month - 1, this.day, this.hour,
				this.minute, this.second);
		return localCalendar.getTime().getTime();
	}

	public void setTimeInMillis(long paramLong) {
		Date localDate = new Date(paramLong);
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(localDate);
		this.year = localCalendar.get(1);
		this.month = (localCalendar.get(2) + 1);
		this.day = localCalendar.get(5);
		this.hour = localCalendar.get(11);
		this.minute = localCalendar.get(12);
		this.second = localCalendar.get(13);
	}

	public boolean isLeapYear() {
		return isLeapYear(this.year);
	}

	public boolean isLeapYear(int paramInt) {
		if (paramInt % 400 == 0)
			return true;
		if (paramInt % 4 == 0)
			return (paramInt % 100 == 0);
		return false;
	}

	public void _add(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4, int paramInt5, int paramInt6) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.set(this.year + paramInt1, this.month - 1 + paramInt2,
				this.day + paramInt3, this.hour + paramInt4, this.minute
						+ paramInt5, this.second + paramInt6);
		setTimeInMillis(localCalendar.getTime().getTime());
	}

	public void addYear(int paramInt) {
		if ((this.month == 2) && (this.day == 29))
			if (isLeapYear(this.year + paramInt) == true)
				_add(paramInt, 0, 0, 0, 0, 0);
			else
				_add(paramInt, 0, -1, 0, 0, 0);
		else
			_add(paramInt, 0, 0, 0, 0, 0);
	}

	public void addMonth(int paramInt) {
		int i = daysOfMonth();
		int j = getDayOfMonth(paramInt);
		if (this.day == i)
			this.day = j;
		else if (this.day > j)
			this.day = j;
		_add(0, paramInt, 0, 0, 0, 0);
	}

	public void addDay(int paramInt) {
		_add(0, 0, paramInt, 0, 0, 0);
	}

	public void addHour(int paramInt) {
		_add(0, 0, 0, paramInt, 0, 0);
	}

	public void addMinute(int paramInt) {
		_add(0, 0, 0, 0, paramInt, 0);
	}

	public void addSecond(int paramInt) {
		_add(0, 0, 0, 0, 0, paramInt);
	}

	public int daysOfMonth() {
		if ((this.month > 12) || (this.month < 0))
			return 0;
		if ((this.month == 2) && (isLeapYear()))
			return 29;
		return dayArray[(this.month - 1)];
	}

	public int getDayOfMonth(int paramInt) {
		int i = paramInt / 12;
		int j = paramInt % 12;
		int k = this.year + i;
		int l = this.month + j;
		if (l > 12) {
			l -= 12;
			k += 1;
		}
		if (l < 1) {
			l += 12;
			k -= 1;
		}
		if ((l == 2) && (isLeapYear(k)))
			return 29;
		return dayArray[(l - 1)];
	}

	public static long diffSec(DateUtils paramDateUtils1,
			DateUtils paramDateUtils2) {
		return ((paramDateUtils1.getTimeInMillis() - paramDateUtils2
				.getTimeInMillis()) / 1000L);
	}

	public static Date getDate(String paramString) {
		if ((paramString == null) || (paramString.equals("")))
			return null;
		Date localDate = null;
		int i = 1;
		if (paramString.indexOf("/") != -1)
			i = 0;
		if (paramString.length() > 10)
			paramString = paramString.substring(0, 10);
		try {
			if (i != 0)
				localDate = CHN_DATE_FORMAT.parse(paramString);
			else
				localDate = DATE_FORMAT.parse(paramString);
		} catch (ParseException localParseException) {
			localParseException.printStackTrace();
		}
		return localDate;
	}

	public static int diffYear(String paramString) throws Exception {
		Date localDate = getDate(paramString);
		if (localDate == null)
			return -1;
		return diffYear(localDate);
	}

	public static int diffYear(Date paramDate) throws Exception {
		Calendar localCalendar = Calendar.getInstance();
		if (localCalendar.before(paramDate))
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		int i = localCalendar.get(1);
		int j = localCalendar.get(2);
		int k = localCalendar.get(5);
		localCalendar.setTime(paramDate);
		int l = localCalendar.get(1);
		int i1 = localCalendar.get(2);
		int i2 = localCalendar.get(5);
		int i3 = i - l;
		if (j <= i1)
			if (j == i1)
				if (k < i2)
					--i3;
				else
					--i3;
		return i3;
	}
	
	/** 
     *   提供了精確的小數位四捨五入處理 
     *   @param   args 
     */ 
   
   public   static   double   round(double   v,   int   scale)   { 
           if   (scale <0)   { 
             throw   new   IllegalArgumentException( "The   scale   must   be   a   positive   integer   or   zero "); 
           } 
           BigDecimal   b   =   new   BigDecimal(Double.toString(v)); 
           BigDecimal   one   =   new   BigDecimal( "1"); 
           return   b.divide(one,   scale,   BigDecimal.ROUND_HALF_DOWN).doubleValue(); 
   }
   
   /**
	 * 截取日期部分
	 * 
	 * @param SimpleDateFormat
	 * @return
	 */
	public static Date getDatePart(Date date) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;

	}
	
	public static long diffSec(Date endDate, Date startDate) {
		long milliseconds = endDate.getTime() - startDate.getTime();

		return milliseconds / 1000;
	}
	
	public static long diffMins(Date endDate, Date startDate) {
		long milliseconds = endDate.getTime() - startDate.getTime();
		
		return milliseconds / 1000 / 60;
	}
	
	public static long diffDays(Date endDate, Date startDate) {
		
		long milliseconds = getDatePart(endDate).getTime() - getDatePart(startDate).getTime();

		return milliseconds / (1000 * 60 * 60 * 24);
	}
}

