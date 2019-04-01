package com.hy.salon.basic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class TimeBeginAndEndOFaMonth {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public static Date[] getDates(String year, String month) {
        int maxDate = 0;
        Date first = null;
        Calendar calendar2 = Calendar.getInstance();
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = sdfs.parse(year+"-"+month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            first = sdf.parse(year + month);
            cal.setTime(first);
           // maxDate = cal.getMaximum(Calendar.DAY_OF_MONTH-1);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(year) - 1);
            calendar.set(Calendar.MONTH,Integer.parseInt( month));
            calendar2.setTime(date);
            maxDate = calendar2.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date[] dates = new Date[maxDate];
        for (int i = 1; i <= maxDate; i++) {
            dates[i - 1] = new Date(first.getTime());
            first.setDate(first.getDate() + 1);
        }
        return dates;
    }

    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

/*    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        System.out.println(getDaysOfMonth(sdf.parse("2019-04")));
    }*/
/*    public static void main(String[] args) {

        //getDates("2019","04");
        Calendar calendar = Calendar.getInstance();
        int temp = Calendar.YEAR;
        int tmep2 = Calendar.MONTH;
        calendar.set(calendar.get(temp),calendar.get(tmep2),1);
        calendar.roll(Calendar.DATE, false);
        System.out.println(calendar.get(Calendar.DATE));
    }*/
    public static Date getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }
}
