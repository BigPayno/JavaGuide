package guava.enums;

import com.google.common.base.Enums;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @author payno
 * @date 2019/9/20 10:30
 * @description
 */
public class EnumsGuide {
    public enum Day{
        Monday,
        Friday
    }
    public void enums(){
        Enums.stringConverter(Day.class);
        EnumSet.allOf(Day.class);
    }
}
