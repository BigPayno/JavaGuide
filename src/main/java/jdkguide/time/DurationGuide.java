package jdkguide.time;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2019/9/3 11:54
 * @description
 */
public class DurationGuide {
    public static void main(String[] args) {
        long num= TimeUnit.DAYS.toMillis(1);
        long num2= Duration.ofDays(1).toMillis();
        System.out.println(num);
        System.out.println(num2);
    }
}
