package jdkguide.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @author payno
 * @date 2019/11/19 14:27
 * @description
 */
public class FinalTest {
    @Getter
    @Setter
    public static class Person{
        String name;
        String pass;
    }
    public static void main(String[] args) {
        final Person person=new Person();
        System.out.println(person);
        person.setName("payno");
        System.out.println(person.name);
    }

    public static class Test{
        public void test(){
            final Person person=new Person();
            System.out.println(person);
        }
    }

    public static class TestFinal{
        public static void main(String[] args) {
            Test test=new Test();
            for (int i=0;i<10;i++){
                test.test();
            }
        }
    }
}
