package spring.reflect.annotation;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;

/**
 * @author payno
 * @date 2019/12/11 10:24
 * @description
 */
public class AlicesForGuide {
    private void print(Annotation annotation){
        System.out.println(annotation.getClass());
        AnnotationUtils.getAnnotationAttributes(annotation).entrySet()
                .forEach(e-> System.out.printf("%s : %s %n",e.getKey(),e.getValue()));
    }
    private void printM(Class<?> clazz,Class<? extends Annotation> annotation){
        System.out.println(annotation);
        AnnotatedElementUtils.getMergedAnnotationAttributes(clazz,annotation).entrySet()
                .forEach(e-> System.out.printf("%s : %s %n",e.getKey(),e.getValue()));
    }
    @Com(name = "d1")
    public static class D1{
    }
    @Com(value = "d2")
    public static class D2{
    }
    @Test
    public void test1(){
        ImmutableList.of(D1.class,D2.class).forEach(
                clazz->{
                    Com com=clazz.getAnnotation(Com.class);
                    print(com);
                }
        );
    }
    @Ser(serName = "d3")
    public static class D3{
    }
    /**
     * get语义之后的方法将遵循Java的@Inherited批注的约定，
     * 除了本地声明的注解（包括自定义组合注解）将优于继承注解。
     * 相反，查找语义之后的方法将完全忽略@Inherited的存在，
     * 因为查找搜索算法手动遍历类型和方法层次结构，从而隐式支持注解继承而不需要@Inherited。
     */
    @Test
    public void test2(){
        Ser ser=D3.class.getAnnotation(Ser.class);
        Com com=AnnotationUtils.getAnnotation(ser,Com.class);
        print(com);
        /**
         * 直接拿到了注解的元注解,实际上上拿到的Com
         * 是利用Alias拿到注解的属性，然后通过代理对象得到一个
         * 元注解对象
         */
        Com com1=AnnotationUtils.findAnnotation(D3.class,Com.class);
        print(com1);
        /**
         * 判断一个注解与另一个注解是否存在元注解与子注解关系
         * true
         */
        System.out.println(
                AnnotationUtils.isAnnotationMetaPresent(Ser.class,Com.class)
        );
        /**
         * 判断注解是否通过@Inherited得到的注解
         * false，false
         */
        System.out.println(
                AnnotationUtils.isAnnotationInherited(Com.class,D3.class)
        );
        System.out.println(
                AnnotationUtils.isAnnotationInherited(Ser.class,D3.class)
        );
    }

    @Test
    public void test3(){
        /**
         * isAnnotated
         * 确定在提供的AnnotatedElement上或指定元素上方的注解层次结构中是否存在指定annotationType的注解。
         * 如果此方法返回true，则getMergedAnnotationAttributes方法将返回非null值。
         * hasAnnotation
         * 确定指定的annotationType的注解是否在提供的AnnotatedElement上或在指定元素上方的注解层次结构中可用。
         * 如果此方法返回true，则findMergedAnnotationAttributes方法将返回非null值。
         */
        System.out.println(
                AnnotatedElementUtils.isAnnotated(D3.class,Com.class)
        );
        System.out.println(
                AnnotatedElementUtils.isAnnotated(D3.class,Ser.class)
        );
        Com com=AnnotatedElementUtils.getMergedAnnotation(D3.class,Com.class);
        print(com);
        Ser ser=AnnotatedElementUtils.getMergedAnnotation(D3.class,Ser.class);
        print(ser);
        System.out.println(
                AnnotatedElementUtils.hasAnnotation(D3.class,Com.class)
        );
        System.out.println(
                AnnotatedElementUtils.hasAnnotation(D3.class,Ser.class)
        );
        Com com2=AnnotatedElementUtils.findMergedAnnotation(D3.class,Com.class);
        print(com2);
        Ser ser2=AnnotatedElementUtils.findMergedAnnotation(D3.class,Ser.class);
        print(ser2);
    }
    @EsbSer
    public static class D4{
    }

    /**
     * AlicesFor可以重写父注解的内容
     * Com com,com
     * Ser$com ser,ser
     * EsbSer$com esbSer,esbSer
     */
    @Test
    public void test4(){
        printM(D4.class,Com.class);
        printM(D4.class,Ser.class);
        printM(D4.class,EsbSer.class);
    }
}
