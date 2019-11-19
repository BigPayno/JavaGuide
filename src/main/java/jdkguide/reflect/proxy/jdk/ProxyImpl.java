package jdkguide.reflect.proxy.jdk;

/**
 * @author payno
 * @date 2019/11/19 09:17
 * @description
 */
public class ProxyImpl implements ProxyInterface{
    @Override
    public String print() {
        System.out.println("print");
        return "print";
    }
    public void print2(){
        System.out.println("print2");
    }
}
