package jdkguide.time;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @author payno
 * @date 2020/1/16 15:15
 * @description
 */
public class CalendarGuide {
    public static void main(String[] args) {
        LocalDate localDate=LocalDate.now();
        Calendar calendar=Calendar.getInstance();
        calendar.set(localDate.getYear(),localDate.getMonthValue()-1,localDate.getDayOfMonth());
        Date date=calendar.getTime();
    }
}
