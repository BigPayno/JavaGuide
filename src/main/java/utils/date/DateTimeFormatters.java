package utils.date;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.charset.Charset;
import java.time.format.DateTimeFormatter;

/**
 * @author payno
 * @date 2019/8/28 11:00
 * @description
 */
public final class DateTimeFormatters {
    private DateTimeFormatters(){}
    public static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy_MM_dd");
    public static DateTimeFormatter ofPattern(String pattern){
        return DateTimeFormatter.ofPattern(pattern);
    }
}
