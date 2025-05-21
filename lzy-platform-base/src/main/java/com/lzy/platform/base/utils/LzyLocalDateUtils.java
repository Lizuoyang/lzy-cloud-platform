package com.lzy.platform.base.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;

/**
 * java8 时间工具类
 * @author lizuoyang
 * @date 2025/03/05
 */
public class LzyLocalDateUtils {

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
     * String转换为LocalDate.
     *
     * @param date
     * @return java.time.LocalDateTime
     */
    public static LocalDate convertDateToLd(String date) {
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
        return LocalDate.parse(date, fmt);
    }

    /**
     * Integer转换为LocalDate.
     *
     * @param date
     * @return java.time.LocalDateTime
     */
    public static LocalDate convertDateToLd(Integer date) {
        return LocalDate.parse(date.toString(), BASIC_ISO_DATE);
    }

    /**
     * 获取指定时间的指定格式.
     *
     * @param time
     * @param pattern
     * @return java.lang.String
     */
    public static String formatTime(LocalDate time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
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

    /**
     * 生成指定开始日期和结束日期之间的日期列表，包括开始日期和结束日期。
     * 列表中的日期顺序从早到晚排列。
     *
     * @param startDate 开始日期，列表中的第一个日期。
     * @param endDate   结束日期，列表中的最后一个日期。
     * @return 一个包含所有日期的列表。如果开始日期在结束日期之后，返回一个空列表。
     */
    public static List<LocalDate> dateRange(LocalDate startDate, LocalDate endDate) {
        // 初始化一个空的日期列表
        List<LocalDate> localDate = new ArrayList<>();
        // 如果结束日期在开始日期之前，直接返回空列表
        if (endDate.isBefore(startDate)) {
            return localDate;
        }
        // 使用无限循环来填充日期列表，直到结束日期不再晚于开始日期
        while (true) {
            // 将结束日期添加到列表中
            localDate.add(endDate);
            // 如果结束日期不再晚于开始日期，退出循环
            if (!endDate.isAfter(startDate)) {
                break;
            }
            // 将结束日期向前移动一天，以准备下一次循环
            endDate = endDate.plusDays(-1);
        }
        // 返回填充好的日期列表
        return localDate;
    }

}
