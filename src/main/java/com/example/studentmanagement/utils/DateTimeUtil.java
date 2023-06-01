package com.example.studentmanagement.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTimeUtil {
    public static Date parseDateCommon(String dateStr) {
        Date date = null;
        SimpleDateFormat formatter = null;
        if (dateStr.contains("/")) {
            if (dateStr.split("/")[0].length() > 2) {
                formatter = new SimpleDateFormat("yyyy/MM/dd");
            } else {
                formatter = new SimpleDateFormat("dd/MM/yyyy");
            }
        } else if (dateStr.contains("-")) {
            formatter = new SimpleDateFormat("dd-MM-yyyy");
        }
        try {
            if (formatter != null) {
                date = formatter.parse(dateStr);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    // convert dateStr -> Date format: yyyy-MM-dd to save database
    public static Date stringToDate(String dateStr) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public static String dateToString(Date date, String format) {
        if(date == null){
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

}
