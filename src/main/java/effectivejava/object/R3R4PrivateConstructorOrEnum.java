package effectivejava.object;

import java.io.Serializable;

/**
 * @author payno
 * @date 2019/8/17 19:01
 * @description R3:私有构造器或者枚举强化Singleton属性
 *              R4:使用私有构造器来强化不可实例化的能力
 */
public class R3R4PrivateConstructorOrEnum {
    public static class Singleton1{
        public static final Singleton1 INSTANCE=new Singleton1();
        private Singleton1(){}
    }
    public static class Singleton2{
        private static final Singleton2 INSTANCE=new Singleton2();
        private Singleton2(){}
        public static Singleton2 getInstance(){
            return INSTANCE;
        }
    }
    public static enum Singleton3{
        INSTANCE;
    }
    public static class Singleton4 implements Serializable{
        private static final transient Singleton4 INSTANCE=new Singleton4();
        private Singleton4(){}
        //提供序列化
        private Object readResolve(){
            return INSTANCE;
        }
    }
}
