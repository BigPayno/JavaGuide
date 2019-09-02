package utils.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author payno
 * @date 2019/8/28 11:02
 * @description
 */
public class LocalDateTimes {
    public static long from(LocalDateTime localDateTime){
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
    public static LocalDateTime from(long timestamp){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    public static LocalDateTime parse(String text,String format){
        return LocalDateTime.parse(text,DateTimeFormatter.ofPattern(format));
    }
    public static String format(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter){
        return localDateTime.format(dateTimeFormatter);
    }
}
