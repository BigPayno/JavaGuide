package guava.reflection;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import java.util.List;

/**
 * @author payno
 * @date 2019/10/14 14:40
 * @description
 */
public class InvokableGuide {
    public static void main(String[] args) throws Exception{
        TypeToken<List<String>> listToken=new TypeToken<List<String>>(){};
        Invokable<List<String>,?> size=listToken.method(List.class.getMethod("size"));
        System.out.println(size.getReturnType());
        System.out.println(size.getParameters());
    }
}
