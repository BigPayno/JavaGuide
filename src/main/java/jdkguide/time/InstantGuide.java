package jdkguide.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

/**
 * @author payno
 * @date 2019/8/19 16:16
 * @description
 */
public class InstantGuide {
    public static void main(String[] args) {
        long timestamp=System.currentTimeMillis();
        Instant now=Instant.ofEpochMilli(timestamp);
        Instant instant=Instant.now();
    }
}
