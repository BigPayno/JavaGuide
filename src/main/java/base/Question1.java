package base;

import org.junit.Test;

/**
 * @author payno
 * @date 2019/12/24 15:43
 * @description
 *
 */
public class Question1 {
    public class A{
        public void display(){
            System.out.println("A");
        }
        public void displayTimes(long times){
            while(times>0){
                display();
                times--;
            }
        }
    }

    public class A2 extends A{
        @Override
        public void display() {
            System.out.println("A2");
        }
    }

    @Test
    public void test(){
        A2 a2=new A2();
        a2.displayTimes(3);
    }
}
