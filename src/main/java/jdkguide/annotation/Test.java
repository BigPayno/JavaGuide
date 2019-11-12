package jdkguide.annotation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author payno
 * @date 2019/11/6 16:11
 * @description
 */
public class Test {
    public void test(String s,Integer i){

    }

    public static void main(String[] args) throws Exception{
        Method method=Test.class.getMethod("test");
        Parameter[] parameters=method.getParameters();
        method.invoke(new Test(),"b","s");
    }
}
