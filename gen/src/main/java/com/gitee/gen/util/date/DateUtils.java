package com.gitee.gen.util.date;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2019-02-20 18:00
 * @Copyright Copyright@2019
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * @param @param  date 时间
     * @param @param  format 格式化之后的样式
     * @param @return
     * @return String
     * @Title FormatDate
     * @Description 将时间返回相应的格式
     * @author xwc1125
     * @date 2016年6月12日 下午3:57:14
     */
    public static String FormatDate(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String dateStr = df.format(date);
        return dateStr;
    }

    public static String FormatDate(Date date, DateFormat dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat.getValue());
        String dateStr = df.format(date);
        return dateStr;
    }

    /**
     * <p>
     * Title: setStartDay
     * </p>
     * <p>
     * Description: 设置当前时间为当天的0点时间（即00点00分00秒）
     * </p>
     * <p>
     *
     * </p>
     *
     * @param cal
     * @return
     * @author xwc1125
     * @date 2015-7-14下午2:08:46
     */
    public static Calendar setStartDay(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * <p>
     * Title: setEndDay
     * </p>
     * <p>
     * Description: 设置当前时间为当天的最后一刻点时间（即23点59分59秒）
     * </p>
     * <p>
     *
     * </p>
     *
     * @param cal
     * @return
     * @author xwc1125
     * @date 2015-7-14下午2:08:42
     */
    public static Calendar setEndDay(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal;
    }

    /**
     * 获得自定义格式的当前时间
     *
     * @param format 格式化之后的样式
     * @return 返回格式化之后的日期
     */
    public static String dateToFormat(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String dateFormat = df.format(date);
        return dateFormat;
    }


    public static String dateToFormat(Date date, DateFormat format) {
        SimpleDateFormat df = new SimpleDateFormat(format.getValue());
        String dateFormat = df.format(date);
        return dateFormat;
    }

    /**
     * 当前时间加减几年之后的时间
     *
     * @param years 要加减多少年 格式:10,-10
     * @return 返回加减之后的时间
     */
    public static Date addYear(Integer years) {
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /**
     * 当前时间加减几个月之后的时间
     *
     * @param month 要加减的月份 格式如 10,-10 return 返回加减之后的日期
     */
    public static Date addMonth(Integer month) {
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    /**
     * 当前时间加减几天之后的时间
     *
     * @param days 要加减多少天 格式:100,-100
     * @return 返回加减之后的时间
     */
    public static Date addDay(Integer days) {
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    /**
     * 通过传的值得到这一天星期几
     *
     * @param date
     *            传入的日期格式的参数 格式 2014-11-18
     * @return 返回星期几
     */
    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeek(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取某一个月份的天数
     *
     * @param date 传入的年月时间 格式 2014-11
     * @return 返回固定月份的天数
     */
    public static Integer getDaysByMonth(String date) {
        String str[] = date.split("-");
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.MONTH, Integer.parseInt(str[1]));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(str[0]));
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取某一个年的天数
     *
     * @param year 传入的年份 格式 1980
     * @return 返回固定年份的天数
     */
    public static Integer getDaysByYear(Integer year) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取某两个时间段之间相差的年份
     *
     * @param date1 格式:1980
     * @param date2 格式:2014
     * @return 返回两个年份之间的年份差
     */
    public static int diffYears(String date1, String date2) {
        return Math.abs(Integer.parseInt(date2) - Integer.parseInt(date1));
    }

    /**
     * 获取某两个时间段之间相差的月份
     *
     * @param date1 格式：2014-10
     * @param date2 格式：1988-12
     * @return 返回两个时间段之间的月份差
     */
    public static Long diffMonth(String date1, String date2) {
        String str1[] = date1.split("-");
        String str2[] = date2.split("-");
        Calendar cal = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        cal.set(Calendar.YEAR, Integer.parseInt(str1[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(str1[1]));

        cal2.set(Calendar.YEAR, Integer.parseInt(str2[0]));
        cal2.set(Calendar.MONTH, Integer.parseInt(str2[1]));

        long time = Math.abs(cal.getTimeInMillis() - cal2.getTimeInMillis());

        return time / 1000 / 60 / 60 / 24 / 30;
    }

    /**
     * 获取某两个时间段之间相差的天数
     *
     * @param date1 格式：2014-10-15
     * @param date2 格式：1988-12-20
     * @return 返回两个时间段之间的天数差
     */
    public static Long diffDays(String date1, String date2) {
        String str1[] = date1.split("-");
        String str2[] = date2.split("-");
        Calendar cal = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        cal.set(Calendar.YEAR, Integer.parseInt(str1[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(str1[1]));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(str1[2]));

        cal2.set(Calendar.YEAR, Integer.parseInt(str2[0]));
        cal2.set(Calendar.MONTH, Integer.parseInt(str2[1]));
        cal2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(str2[2]));

        long time = Math.abs(cal.getTimeInMillis() - cal2.getTimeInMillis());

        return time / 1000 / 60 / 60 / 24;
    }

    /**
     * <p>
     * Title: beforeOneHourToNowDate
     * </p>
     * <p>
     * Description: 获取当前时间的一个小时前的时间
     * </p>
     * <p>
     *
     * </p>
     *
     * @return
     * @author xwc1125
     * @date 2015-7-15下午12:00:44
     */
    public static Date getDateBeforeOneHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,
                calendar.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("一个小时前的时间：" + df.format(calendar.getTime()));
        System.out.println("当前的时间：" + df.format(new Date()));
        return calendar.getTime();
    }

    /**
     * 增加天数
     *
     * @param d
     * @param day
     * @return
     * @throws ParseException
     */
    public static Date addDate(Date d, long day) throws ParseException {
        long time = d.getTime();
        day = day * 24 * 60 * 60 * 1000;
        time += day;
        return new Date(time);
    }

    /**
     * 增加秒数
     *
     * @param d
     * @param seconds
     * @return
     * @throws ParseException
     */
    public static Date addSeconds(Date d, long seconds) {
        long time = d.getTime();
        time += seconds * 1000;
        return new Date(time);
    }

    /**
     * 减少天数
     *
     * @param d
     * @param day
     * @return
     * @throws ParseException
     */
    public static Date reduceDate(Date d, long day) throws ParseException {
        long time = d.getTime();
        day = day * 24 * 60 * 60 * 1000;
        time -= day;
        return new Date(time);
    }

    /**
     * 剩余天数
     *
     * @param smallDate 小的时间
     * @param bigDate   大的时间
     * @return
     * @throws ParseException
     */
    public static int remainDays(Date smallDate, Date bigDate) throws ParseException {
        long time = smallDate.getTime();
        long time2 = bigDate.getTime();
        long dt = time2 - time;
        int days = (int) (dt / (24 * 60 * 60 * 1000));
        return days;
    }

    /**
     * 获取前月的最后一天
     *
     * @return
     * @throws ParseException
     */
    public static Date getLastMDay() throws ParseException {
        // 获取当前月最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH,
                ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("===============last:" + last);
        return ca.getTime();
    }

    /**
     * 获取某月的最后一天
     */
    public static String getLastDayOfMonth(Long datetime) {
        Date date = new Date(datetime);
        return getLastDayOfMonth(date);
    }

    /**
     * 获取某月的最后一天
     */
    public static String getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        return getLastDayOfMonth(year, month);
    }

    /**
     * 获取某月的最后一天
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
}
