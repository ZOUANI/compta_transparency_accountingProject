package com.zsmart.accountingProject.service.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.YearMonth;
import java.util.*;

public class DateUtil {

    public static String formateDate(Date date) {
        return formateDate("yyyy-MM-dd hh:mm:ss.SSS", date);
    }

    public static String formateDate(String pattern, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (date != null) {
            return simpleDateFormat.format(date);
        } else {
            return "";
        }
    }

    public static Date parse(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        } else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return simpleDateFormat.parse(date);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static Date parseTimestamp(String date) {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(date);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (Exception e) {
            return null;
        }
    }

    public static java.sql.Date convertFormUtilToSql(java.util.Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        } else {
            return null;
        }
    }

    public static java.sql.Timestamp convertFormUtilToTimestamp(java.util.Date date) {
        if (date != null) {
            return new java.sql.Timestamp(date.getTime());
        } else {
            return null;
        }
    }

    public static List<List<YearMonth>> splitDate(YearMonth from, YearMonth to, int interval) {
        if (from.compareTo(to) > 0) {
            return null;
        } else {
            Period period = Period.between(from.atDay(1), to.atEndOfMonth());
            int numberOfMonths = period.getMonths() + 1 + (period.getYears() * 12);
            int monthInterval = (int) Math.ceil((double) numberOfMonths / interval);
            List<List<YearMonth>> test = new ArrayList<>();
            while (from.compareTo(to) < 0) {
                List<YearMonth> date = new ArrayList<>();
                date.add(from);
                from = from.plusMonths(monthInterval);
                date.add(from.compareTo(to) >= 0 ? to : from.minusMonths(1));
                test.add(date);
            }
            return test;
        }
    }

    public static int getMonth(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static Date fromYearMonth(YearMonth yearMonth, boolean endOfMonth) {
        if (endOfMonth) {
            return parse(yearMonth.getYear() + "-" + yearMonth.getMonthValue() + "-" + yearMonth.atEndOfMonth().getDayOfMonth());
        } else {
            return parse(yearMonth.getYear() + "-" + yearMonth.getMonthValue() + "-01");
        }
    }

}
