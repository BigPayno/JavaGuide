package jdkguide.time;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author payno
 * @date 2019/8/19 16:33
 * @description
 */
public class  LocalDateTimeGuide {
    private static void staticFactory(){
        LocalDate localDate=LocalDate.now();
        LocalTime localTime=LocalTime.now();
        LocalDateTime localDateTime=LocalDateTime.now();
        LocalDateTime.now(ZoneOffset.of("+8"));
    }

    private static void format(){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter dateTimeFormatter1=DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL);
        System.out.println(dateTimeFormatter.format(LocalDateTime.now()));
    }

    private static void addOrMinus(){
        LocalDateTime.now().minusYears(1);
        LocalDateTime.now().plusDays(2);
        System.out.println(LocalDateTime.now().minusMonths(-100));
    }

    private static void from(){
        String time="2019-09-10 08:18:20";
        String format="yyyy-MM-dd HH:mm:ss";
        LocalDateTime localDateTime=LocalDateTime.parse(time,DateTimeFormatter.ofPattern(format));
        System.out.println(localDateTime);
    }

    private static void secondHourDayMouthYear(){
        LocalDate start=LocalDate.of(2015,5,10);
        LocalDate now=LocalDate.now();
        System.out.println(now.toEpochDay()-start.toEpochDay());
        Instant.now().toEpochMilli();
        LocalTime.now().toNanoOfDay();
        LocalDate.now().toEpochDay();
    }
    public static void main(String[] args) {
        LocalDateTime start=LocalDateTime.of(2019,1,12,3,30,12);
        LocalDateTime end=LocalDateTime.now();
        long seconds=end.toLocalTime().toSecondOfDay()-start.toLocalTime().toSecondOfDay();
        String time=LocalTime.ofSecondOfDay(seconds).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(time);

        String time2=end.toLocalTime()
                .minusSeconds(start.toLocalTime().toSecondOfDay())
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(time2);

        Instant distance=end.atZone(ZoneId.systemDefault()).toInstant().minusMillis(
                start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        );
        String time3=LocalDateTime.ofInstant(distance,ZoneId.of("Etc/GMT+0"))
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(time3);
    }
}
