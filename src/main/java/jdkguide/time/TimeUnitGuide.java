package jdkguide.time;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2019/9/3 10:32
 * @description
 */
public class TimeUnitGuide {
    public static void main(String[] args) {
        long num= TimeUnit.DAYS.toMillis(1);
        long num2=Duration.ofDays(1).toMillis();
        System.out.println(num);
        System.out.println(num2);
    }
}
