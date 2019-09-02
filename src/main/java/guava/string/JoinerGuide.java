package guava.string;

import com.google.common.base.Joiner;

/**
 * @author payno
 * @date 2019/8/13 19:26
 * @description
 */
public class JoinerGuide {
    private static void skipNulls(){
        String result=Joiner.on(";")
                .skipNulls()
                .join("a","b","c",null,"e")
                .concat(";");
        System.out.println(result);
    }
    private static void useForNull(){
        String result=Joiner.on("|")
                .useForNull("null")
                .join("a","b",null,"d")
                .concat(";");
        System.out.println(result);
    }
    public static void main(String[] args) {
        skipNulls();
        useForNull();
    }
}
