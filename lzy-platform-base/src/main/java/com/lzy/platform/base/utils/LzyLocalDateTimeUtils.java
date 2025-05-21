package com.lzy.platform.base.utils;

import cn.hutool.core.util.StrUtil;
import com.lzy.platform.base.exception.SystemException;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Date;


/**
 * java8 时间工具类
 * @author lizuoyang
 * @date 2025/03/05
 */
public class LzyLocalDateTimeUtils {

    /**
     * 默认时区 Asia/Shanghai
     */
    public static String DEFAULT_TIME_ZONE = "Asia/Shanghai";

    /**
     * 时间格式 时分秒 如：23:15:06
     */
    public static String FORMAT_TIME = "HH:mm:ss";
    
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    
    /**
     * 英文全称 如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 英文全称无空格时间戳 如：20101201231506
     */
    public static String FORMAT_LONG_NO = "yyyyMMddHHmmss";

    /**
     * 英文全称无空格时间戳带毫秒 如：20101201231506000
     */
    public static String FORMAT_LONG_SSS = "yyyyMMddHHmmssSSS";
    
    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    
    /**
     * 中文简写 如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    
    /**
     * 中文全称 如：2010年12月01日 23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";
    
    /**
     * 毫秒转换为LocalDateTime.
     *
     * @param milliTime
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime convertMilliSecondToLdt(Long milliTime) {
        Instant instant = Instant.ofEpochMilli(milliTime);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime datetime = LocalDateTime.ofInstant(instant, zone);
        return datetime;
    }
    
    /**
     * 秒转换为LocalDateTime.
     *
     * @param secondTime
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime convertSecondToLdt(Long secondTime) {
        Instant instant = Instant.ofEpochSecond(secondTime);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime datetime = LocalDateTime.ofInstant(instant, zone);
        return datetime;
    }

    /**
     * Date转换为LocalDateTime.
     *
     * @param date
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime convertDateToLdt(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }


    /**
     * LocalDateTime转换为Date.
     *
     * @param time
     * @return java.util.Date
     */
    public static Date convertLdtToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取指定日期的毫秒.
     *
     * @param time
     * @return java.lang.Long
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒.
     *
     * @param time
     * @return java.lang.Long
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式.
     *
     * @param time
     * @param pattern
     * @return java.lang.String
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式.
     *
     * @param pattern
     * @return java.lang.String
     */
    public static String formatNow(String pattern) {
        return  formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*.
     *
     * @param time
     * @param number
     * @param field
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*.
     *
     * @param time
     * @param number
     * @param field
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*.
     *
     * @param startTime
     * @param endTime
     * @param field
     * @return long
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12L + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 获取一天的开始时间，2017,7,22 00:00.
     *
     * @param time
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999.
     *
     * @param time
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }

    /**
     * 获取某月第一天的00:00:00
     * @return
     */
    public static LocalDateTime getFirstDayOfMonth(LocalDateTime localDateTime){
        LocalDateTime firstOfDay = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        return firstOfDay;
    }

    /**
     * 获取某月最后一天的23:59:59
     * @return
     */
    public static LocalDateTime getLastDayOfMonth(LocalDateTime localDateTime){
        LocalDateTime lastOfDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        return lastOfDay;
    }

    /**
     * 格式化Excel时间 默认 yyyy-MM-dd
     * @param day
     * @return yyyy-MM-dd
     */
    public static String formatExcelDate(int day) {
        String formatExcelDate = LzyLocalDateTimeUtils.formatExcelDate(day, LzyLocalDateTimeUtils.FORMAT_FULL);
        return formatExcelDate;
    }

    /**
     * 格式化Excel时间 自定义格式
     * @param day
     * @return yyyy-MM-dd
     */
    public static String formatExcelDate(int day, String timeFormat) {
        LocalDateTime localDateTime = LocalDateTime.of(1900,0,-1, 0, 0, 0);
        String formatExcelDate = LzyLocalDateTimeUtils.formatTime(localDateTime.plusDays(day), timeFormat);
        return formatExcelDate;
    }

    /**
     * 判断是否是日期格式的字符串
     * @param dateValue
     * @param dateFormat
     * @return
     */
    public static boolean isDateString(String dateValue, String dateFormat) {
        if (StrUtil.isEmpty(dateValue)) {
            return false;
        }
        try {
            SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
            Date dd = fmt.parse(dateValue);
            if (dateValue.equals(fmt.format(dd))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * @describe LocalDateTime转String
     * @Param date
     * @date 2024年03月12日 10:37:42
     * @author Tang
     */
    public static String localDateTimeString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return dateTime.format(dtf);
    }

    /**
     * 将字符串转换为LocalDateTime对象
     *
     * @param str     待转换的字符串表示的时间
     * @param pattern 字符串时间的格式
     * @return 转换后的LocalDateTime对象
     * @throws SystemException 如果字符串为空或格式不正确，则抛出系统异常
     */
    public static LocalDateTime stringToLocalDatetime(String str, String pattern) {
        // 验证输入字符串是否为空或空白，如果是，则抛出异常
        SystemException.Assert(StringUtils.isBlank(str), "时间转换异常,参数为null");
        try {
            // 尝试按照指定格式解析字符串为LocalDateTime对象
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(pattern));
        } catch (Exception e) {
            // 如果解析失败，抛出系统异常，说明时间格式不正确
            throw new SystemException("时间转换异常,时间格式不正确");
        }
    }

    /**
     * 获取指定月份第一天
     * @param localDate
     * @return {@link LocalDate}
     */
    public static LocalDate getCurrentMonthFirstDayToLocalDate(LocalDate localDate) {
        if (localDate == null) {
            localDate = LocalDate.now();
        }
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }


    /**
     * 获取指定月份最后一天
     * @param localDate
     * @return {@link LocalDate}
     */
    public static LocalDate getCurrentMonthLastDayToLocalDate(LocalDate localDate) {
        if (localDate == null) {
            localDate = LocalDate.now();
        }
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

}
