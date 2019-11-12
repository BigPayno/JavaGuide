package guava.reflection;

import com.google.common.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author payno
 * @date 2019/10/14 11:28
 * @description
 */
public class ReflectGuide {
    public static void main(String[] args) {
        Reflection.initialize(String.class);
        Reflection.newProxy(String.class, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        Reflection.getPackageName(String.class);
    }
}
