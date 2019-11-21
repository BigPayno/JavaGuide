package jdkguide.thread.demos;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author payno
 * @date 2019/11/21 08:51
 * @description
 */
public class UnSafes {
    public static Unsafe get() {
        try {
            Class<?> clazz = Unsafe.class;
            Field f = clazz.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(clazz);
            return unsafe;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
