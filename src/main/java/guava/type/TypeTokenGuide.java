package guava.type;

import com.google.common.reflect.TypeToken;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author payno
 * @date 2019/9/17 16:08
 * @description
 */
public class TypeTokenGuide {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList=new ArrayList<>();
        System.out.println(stringArrayList.getClass());
        TypeToken<ArrayList<String>> stringToken=new TypeToken<ArrayList<String>>() {
            @Override
            public boolean equals(@Nullable Object o) {
                return super.equals(o);
            }
        };
        System.out.println(stringToken.getType());
    }
}
