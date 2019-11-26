package jdkguide.reflect.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author payno
 * @date 2019/11/19 10:27
 * @description
 */
public class Enhancers {
    public static class MethodInterceptor<T> implements org.springframework.cglib.proxy.MethodInterceptor {
        private T target;
        private Class<T> targetClass;
        public MethodInterceptor<T> proxy(T target,Class<T> targetClass){
            this.target=target;
            this.targetClass=targetClass;
            return this;
        }
        public T getProxy(){
            return targetClass.cast(Enhancer.create(targetClass,this));
        }
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return methodProxy.invokeSuper(target,objects);
        }
    }
    public static void main(String[] args) {
        ProxyClass targetObj=new ProxyClass();
        ProxyClass proxyObj=new MethodInterceptor<ProxyClass>().proxy(targetObj,ProxyClass.class)
                .getProxy();
        proxyObj.print();
    }
}
