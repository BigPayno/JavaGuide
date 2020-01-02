package jdkguide.generic;

import java.util.List;

/**
 * @author payno
 * @date 2020/1/2 10:57
 * @description
 */
public class SelfExtendLimitGuide3 {
    abstract class Fun{
        abstract  <T> T fun(T t);
        abstract <T extends Number> T fun(T t);
        abstract <T extends List<T>> T fun(T t);
    }
}
