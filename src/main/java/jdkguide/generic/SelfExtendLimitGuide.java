package jdkguide.generic;

/**
 * @author payno
 * @date 2020/1/2 10:07
 * @description
 */
public class SelfExtendLimitGuide {
    /**
     * 协变参数类型与协变返回类型
     */
    public interface Base{}
    public class A implements Base{}
    public class B implements Base{}
    /**
     * 只是认为参数是Base，返回是Base，并不能转换类型
     */
    public Base exe(Base base){
        if(base instanceof A){
            return new A();
        }else {
            return new B();
        }
    }
}
