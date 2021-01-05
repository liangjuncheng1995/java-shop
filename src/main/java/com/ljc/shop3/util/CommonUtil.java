package com.ljc.shop3.util;

import com.ljc.shop3.bo.PageCounter;
import sun.rmi.runtime.Log;

import java.util.Calendar;
import java.util.Date;

public class CommonUtil {
    public static PageCounter coverToPageParameter(Integer start, Integer count) {
        int pageNum = start / count;

        PageCounter pageCounter = PageCounter.builder().page(pageNum).count(count).build();
        return pageCounter;
    }

    public static Calendar addSomeSeconds(Calendar calendar, int seconds) {
        calendar.add(Calendar.SECOND, seconds);
        return calendar;
    }

    public static Boolean isInTimeLine(Date date, Date start, Date end) {
        Long time = date.getTime();
        Long startTime = start.getTime();
        Long endTime = end.getTime();
        if(time > startTime && time < endTime) {
            return true;
        }
        return false;
    }
}
