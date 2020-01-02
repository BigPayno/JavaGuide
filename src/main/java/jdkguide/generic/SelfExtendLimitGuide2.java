package jdkguide.generic;

import org.junit.Test;

/**
 * @author payno
 * @date 2020/1/2 10:18
 * @description
 */
public class SelfExtendLimitGuide2 {
    class Base{}
    class Extend extends Base{}
    class Printer{
        void print(Base base){
            System.out.println("Printer::Base");
        }
    }
    public class ExtendPrinter extends Printer{
        void print(Extend extend){
            System.out.println("ExtendPrinter::Extend");
        }
    }

    @Test
    public void test(){
        Base base=new Base();
        Extend extend=new Extend();
        ExtendPrinter extendPrinter=new ExtendPrinter();
        extendPrinter.print(base);
        extendPrinter.print(extend);
        /**
         * 实现了想要的参数协变，但代价是重载方法，每个类型都需要写一个
         * 返回协变无法实现，因为函数签名相同，编译报错
         */
    }

    /**
     * Still ???
     * 只有一点，如果要满足该类型。
     * 其继承类必定是这样的
     * 泛型是自身的的SuperBase的继承类
     * 这样的泛型指的就是自己本身的类型
     */


    /**
     * 这里的E指的是任意类型
     */
    class Type<E> implements Comparable<E>{
        @Override
        public int compareTo(E o) {
            return 0;
        }
    }
    /**
     * 这里的E指的是以E为上界的生产者容器，可以被消费
     * Type2的子类可以互相比较，只有继承Type2就可以比较
     */
    class Type2<E extends Type2> implements Comparable<E>{
        @Override
        public int compareTo(E o) {
            return 0;
        }
    }

    /**
     * 这里的E指的是自身
     * 如Type3Extend中的E只能是Type3Extend
     */
    class Type3<E extends Type3<E>> implements Comparable<E>{
        @Override
        public int compareTo(E o) {
            return 0;
        }
    }
}
