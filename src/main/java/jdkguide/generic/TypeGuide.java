package jdkguide.generic;

import org.junit.Test;
import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author payno
 * @date 2019/11/7 09:17
 * @description
 */
public class TypeGuide {
    public class Service<T>{
    }
    public class Student extends Service<String>{
    }
    public interface Repo<T>{
    }
    public class StrRepo implements Repo<String>{

    }
    public List<Integer> list;
    @Test
    public void test1() throws Exception{
        if(Student.class.getGenericSuperclass() instanceof ParameterizedType){
            ParameterizedType parameterizedType=(ParameterizedType) Student.class.getGenericSuperclass();
            Type type=parameterizedType.getActualTypeArguments()[0];
            System.out.println(type);
        }

        if(StrRepo.class.getGenericInterfaces()[0] instanceof ParameterizedType){
            ParameterizedType parameterizedType=(ParameterizedType) StrRepo.class.getGenericInterfaces()[0];
            System.out.println(parameterizedType.getActualTypeArguments()[0]);
        }

        ResolvableType resolvableType=ResolvableType.forClass(Student.class).getSuperType().getGeneric(0);
        System.out.println(resolvableType.getType());

        ResolvableType resolvableType1=ResolvableType.forClass(StrRepo.class).getInterfaces()[0].getGeneric(0);
        System.out.println(resolvableType1.getType());

        List<Integer> list=new ArrayList<>();
        System.out.println(
                ResolvableType.forField(TypeGuide.class.getField("list"))
                        .getGeneric(0).getType()
        );
    }
}
