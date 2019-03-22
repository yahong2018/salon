package com.hy.salon.basic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateString {

    public static String DateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

    public static Date StringToDate(String string ){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            System.out.println(sdf.parse(string));
            return  sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
