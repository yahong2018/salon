package com.hy.salon.basic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateString {


    public  static Boolean contrastTime(Date date1,Date date2,int duration){
        long  miao=(date2.getTime()-date1.getTime())/1000;//除以1000是为了转换成秒
        long fen= miao/60  ; // 多少分
        if(fen>duration){//超过
            return true;
        }else{
            return false;
        }
    }


    public static String DateToString(Date date) {
        if(date==null){
            return  "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

    public static  Date getDateAddTime(Date date,float duration){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String durationS =duration+"";
        String strings[] = durationS.split("\\.");


        cal.add(Calendar.HOUR, Integer.parseInt(strings[0]));// 24小时制
        cal.add(Calendar.MINUTE,Integer.parseInt(strings[1])*10);

        return  cal.getTime();
    }


    public static  String getTime(Date time)  {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "HH:mm");
        // String attendanceTime = dateFormat.format(condition.getAttendanceTime());
        // System.out.println(attendanceTime);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
        Date data = new Date();
        int sxtype = 1;
        String newDate = dateFormat.format(data);
        return  newDate;
    }

    public static  String getTimeInfo(Date time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "HH:mm");
        String newDate = dateFormat.format(time);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String info = "";
        Date newD = null;
        try {
            newD = df.parse(newDate);

        String String12  = "12:00";
        String zDate = dateFormat.format(String12);
        Date znewD = df.parse(newDate);

        String String18 = "18:00";
        String wDate = dateFormat.format(String18);
        Date wnewD = df.parse(newDate);


        if(newD.before(znewD)){//上午
            info = "上午";
        }else if(newD.before(wnewD)&&newD.after(znewD)) {//下午
            info = "下午";
        }else{//晚上
            info = "晚上";
        }
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return info;
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
