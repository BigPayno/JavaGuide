package spring.reflect.annotation;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author payno
 * @date 2019/12/11 11:44
 * @description
 */
@EsbSer
public class JdkGuide {
    /**
     * 看，jdk的注解根本不支持类似集成的机制。。。
     * 所以Spring引入了AnnotationUtils和AnnotatedElementUtils
     */
    public static void main(String[] args) {
       Com com=JdkGuide.class.getAnnotation(Com.class);
       System.out.println(com);
       Com com2= AnnotatedElementUtils.getMergedAnnotation(JdkGuide.class,Com.class);
       System.out.println(com2);
       /**
        * 重点来了，需要清除缓存的
        */
       AnnotationUtils.clearCache();
    }
}
