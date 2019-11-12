package jdkguide.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * @author payno
 * @date 2019/9/2 17:15
 * @description
 */
public class ChronoUnitGuide {
    public static void main(String[] args) {
        long days=ChronoUnit.DAYS.between(LocalDate.now(),LocalDate.of(2017,5,9));
        System.out.println(days);
        long hours=ChronoUnit.HOURS.between(Instant.now(), Instant.now().minusSeconds(100000));
        System.out.println(hours);
        LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    }
}
