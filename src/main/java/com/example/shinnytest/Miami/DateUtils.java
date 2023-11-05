package com.example.shinnytest.Miami;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    Calendar calendar;

    public DateUtils(Date date) {
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(date);
    }

    public static DateUtils of(Date date) {
        return new DateUtils(date);
    }

    public static DateUtils of(Long time) {
        return new DateUtils(new Date(time));
    }

    public DateUtils addDate(int amount) {
        this.calendar.add(Calendar.DAY_OF_WEEK, amount);
        return this;
    }

    public DateUtils addMonth(int amount) {
        this.calendar.add(Calendar.MONTH, amount);
        return this;
    }

    public DateUtils truncDate() {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return this;
    }

    public DateUtils tomorrow() {
        return truncDate().addDate(1);
    }

    public String format(String format) {
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    public Date toDate() {
        return this.calendar.getTime();
    }

    public static Date setEndOfDayTime(Long timestamp) {
        Date endOfDay = new Date(timestamp);
        // Đặt giờ, phút, giây và mili giây thành 23:59:59
        endOfDay.setHours(23);
        endOfDay.setMinutes(59);
        endOfDay.setSeconds(59);
        endOfDay.setTime(endOfDay.getTime() + 999);
        return endOfDay;
    }
}

