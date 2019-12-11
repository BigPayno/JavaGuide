package spring.reflect.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2019/12/11 11:17
 * @description
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 代表子类拥有父亲注解的能力
 */
@Inherited
@Ser
public @interface EsbSer {
    @AliasFor(value = "name",annotation = Com.class)
    String name() default "esbSer";
}
