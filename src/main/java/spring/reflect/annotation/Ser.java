package spring.reflect.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2019/12/11 10:40
 * @description
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 代表子类拥有父亲注解的能力
 */
@Inherited
@Com
public @interface Ser {
    @AliasFor(value = "name",annotation = Com.class)
    String serName() default "ser";
}
