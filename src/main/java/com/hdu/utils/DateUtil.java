package com.hdu.utils;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

public class DateUtil {

	private static Logger log = Logger.getLogger(DateUtil.class);

    public static String formatDate(Date date,String format){
        return DateFormatUtils.format(date,format);
    }

    public static String formatDate(Long time,String format){
        return DateFormatUtils.format(time,format);
    }

    public static Date parseDate(String date,String format){
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(date,format);
        } catch (ParseException e) {
            log.error("日期时间解析错误.",e);
            return null;
        }
    }

}
