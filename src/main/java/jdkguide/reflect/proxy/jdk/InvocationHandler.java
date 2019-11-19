package jdkguide.reflect.proxy.jdk;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author payno
 * @date 2019/11/19 09:03
 * @description
 *      利用类加载器生成这样的对象，其方法的执行是通过InvocationHandler执行
 *      Jdk是通过接口来实现动态代理
 */
public class InvocationHandler<T> implements java.lang.reflect.InvocationHandler {
    Class<T> targetClass;
    T target;
    public InvocationHandler<T> initProxyHandler(Class<T> clazz,T target){
        this.target = target;
        this.targetClass = clazz;
        return this;
    }
    public T get(){
        Object proxyObject =Proxy.newProxyInstance(
                targetClass.getClassLoader(),
                new Class[]{ProxyInterface.class},
                this
        );
        return targetClass.cast(proxyObject);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target,args);
    }

    public static void main(String[] args) {
        final ProxyInterface targetInterface=new ProxyImpl();
        ProxyInterface proxyInterface=new InvocationHandler<ProxyInterface>()
                .initProxyHandler(ProxyInterface.class,targetInterface).get();
        proxyInterface.print();
    }
}
