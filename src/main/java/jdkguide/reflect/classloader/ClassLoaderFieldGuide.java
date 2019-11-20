package jdkguide.reflect.classloader;

import com.google.common.collect.ImmutableList;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.util.Vector;

/**
 * @author payno
 * @date 2019/11/20 10:45
 * @description
 */
public class ClassLoaderFieldGuide {
    public static void display(Object [] array){
        ImmutableList.copyOf(array).forEach(System.out::println);
    }
    public static void findClassesLoadByClassLoader(ClassLoader classLoader) throws Exception{
        Field classes=ClassLoader.class.getDeclaredField("classes");
        classes.setAccessible(true);
        Vector<Class> vector=(Vector<Class>) classes.get(classLoader);
        vector.stream().map(Class::getName).forEach(System.out::println);
    }
    public static void main(String[] args) throws Exception{
        ClassLoader classLoader=ClassLoaderFieldGuide.class.getClassLoader();
        findClassesLoadByClassLoader(classLoader);
        ClassUtils.isPresent("jdkguide.reflect.classloader.ClassLoaderFieldGuide",classLoader);
    }
}
