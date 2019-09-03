package jdkguide.time;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * @author payno
 * @date 2019/9/3 09:09
 * @description
 */
public class ClockGuide {
    public static void main(String[] args) {
        Clock clock=Clock.systemDefaultZone();
        long timestamp=clock.millis();
        Instant now=clock.instant();
        Date nowDate=Date.from(now);
    }
}
