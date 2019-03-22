package com.hy.salon.basic.util;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class TimeBeginAndEndOFaMonth {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public static Date[] getDates(String year, String month) {
        int maxDate = 0;
        Date first = null;
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            first = sdf.parse(year + month);
            cal.setTime(first);
            maxDate = cal.getMaximum(Calendar.DAY_OF_MONTH);
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


    public static void main(String[] args) {
        getDates("2019","03");
/*        int year = 2019, month = 03;
        Date beginTime = getBeginTime(year, month);
        System.out.println(sdf.format(beginTime));

        Date endTime = getEndTime(year, month);
        System.out.println(sdf.format(endTime));*/
    }

    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }

    public static Date getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }
}
