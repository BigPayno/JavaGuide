package spring.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author payno
 * @date 2019/11/12 18:09
 * @description
 */
public class BeansUtilGuide {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Person{
        private String name;
        private String psw;
    }
    public static Person person;
    public static Person person1;
    public static Person person2;
    static {
        person=new Person();
        person1=Person.builder().name("payno").build();
        person2=Person.builder().name("payne").psw("2780").build();
    }
    /**
     * 完全拷贝
     */
    @Test
    public void copy(){
        BeanUtils.copyProperties(person2,person);
        System.out.println(person);
    }

    /**
     * 可以配合Example模式进行使用
     */
    @Test
    public void copyWithIgnore(){
        BeanUtils.copyProperties(person2,person1,"name");
        System.out.println(person1);
    }
}
