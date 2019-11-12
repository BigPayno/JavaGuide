package guava.reflection;

import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.Reflection;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author payno
 * @date 2019/10/14 16:18
 * @description
 */
public class ProxyGuide {
    public interface IPrint{
        void print();
    }

    @Slf4j
    public static class PrintInvocationHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("proxy:[{}],methof:[{}],args:[{}]",proxy,method,args);
            System.out.println("proxy println");
            return null;
        }
    }

    public static class GuavaInvocationHandler<T> extends AbstractInvocationHandler{
        private Class<T> targetClass;
        public GuavaInvocationHandler(Class<T> clazz) {
            super();
        }

        @Override
        protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }
    public static void main(String[] args) {
        IPrint proxy= Reflection.newProxy(IPrint.class,new PrintInvocationHandler());
        proxy.print();
    }
}
