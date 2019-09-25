package jdkguide.time;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

/**
 * @author payno
 * @date 2019/9/19 10:20
 * @description
 */
public class ChronoFieldGuide {
    public static void main(String[] args) {
        LocalDateTime now=LocalDateTime.now();
        System.out.println(now.get(ChronoField.YEAR));
    }
}
