package com.github.fallblank.ganklast.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fallb on 2016/4/26.
 */
public class DateFormatUtils {
    private static final String format = "yyyy-MM-dd";

    public static String getFormatDateString(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        return formater.format(date);
    }

    public static String getTimestamp(){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm ss");
        return formater.format(new Date());
    }
}
