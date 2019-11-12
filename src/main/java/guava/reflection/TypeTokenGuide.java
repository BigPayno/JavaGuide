package guava.reflection;

import com.google.common.reflect.TypeToken;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author payno
 * @date 2019/9/17 16:08
 * @description
 */
public class TypeTokenGuide {
    public static void main(String[] args) {
        //原始类型
        TypeToken<String> stringTypeToken=TypeToken.of(String.class);
        System.out.println(stringTypeToken.getType());
        System.out.println(stringTypeToken.getClass());
        //泛型类型
        TypeToken<List<String>> listTypeToken=new TypeToken<List<String>>(){};
        System.out.println(listTypeToken.getType());
        //通配符类型
        TypeToken<Map<?,?>> mapTypeToken=new TypeToken<Map<?, ?>>() {};
        System.out.println(mapTypeToken.getRawType());
        System.out.println(mapTypeToken.getType());
    }
}
