package com.hy.salon.basic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateString {

    public static String DateToString(Date date) {
        if(date==null){
            return  "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }


    public static String DateToString2(Date date) {
        if(date==null){
            return  "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
    public static Date StringToDateAddNum(String string,int num ){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            System.out.println(sdf.parse(string));
            Calendar ca=Calendar.getInstance();
            Date date = sdf.parse(string);
            ca.setTime(date);
            ca.add(Calendar.HOUR_OF_DAY, num);
            return ca.getTime() ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Date getZhongJianTime(String eTimeEnd ,String sTimeStart) throws ParseException {


        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "HH:mm");
        // String attendanceTime = dateFormat.format(condition.getAttendanceTime());
        // System.out.println(attendanceTime);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd

        Date dt1 = null;//传进来的时间
        Date dt2 = null;//排班结束时间
        Date dt4 = null;//排班开始时间
        try {
            //   dt1 = df.parse(attendanceTime);//将字符串转换为date类型
            dt2 = df.parse(eTimeEnd);
            dt4 = df.parse(sTimeStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long shiftTime = dt2.getTime();
        long temp = (dt4.getTime()+ shiftTime)/2;
        Date itemDate = new Date(temp);

        String itemDateTime = dateFormat.format(itemDate);
        Date dt3 = df.parse(itemDateTime);//中间时间

        return  dt3;
    }

}
