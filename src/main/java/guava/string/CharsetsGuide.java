package guava.string;

import com.google.common.base.Charsets;

/**
 * @author payno
 * @date 2019/8/13 20:17
 * @description 避免UnsupportedEncodeException
 */
public class CharsetsGuide {
    public static void main(String[] args) {
        String str="abcdef";
        byte[] bytes=str.getBytes(Charsets.UTF_8);
        String str2=new String(str.getBytes(Charsets.UTF_8));
    }
}
