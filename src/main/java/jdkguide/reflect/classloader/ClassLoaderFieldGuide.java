package jdkguide.reflect.classloader;

import com.google.common.collect.ImmutableList;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.util.Vector;

/**
 * @author payno
 * @date 2019/11/20 10:45
 * @description
 *
 *         AppClassLoader  load classpath java.class
 *         ExtClassLoader load javax.class
 *         BootstrapClassLoader load jre/jdk.class
 *
 *         ExtClassLoader和AppClassLoader处于同级，均继承自URLClassLoader，
 *         URLClassLoader继承自SecureClassLoader，SecureClassLoader继承自ClassLoader，
 *         ClassLoader即为最终的顶级抽象类
 *
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
        System.out.println(classLoader.getParent().getClass());
    }
}
