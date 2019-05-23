package com.im.zemel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUntil {

    public static String getNowTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(currentTime);
        return time;
    }
    public static Date getNowData() {
        Date currentTime = new Date();
        return currentTime;
    }

    public static String getNowDataString() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static Date StringToDate(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date parse = null;
        try {
            parse = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static String DateToString(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(time);
        return dateString;
    }
}
