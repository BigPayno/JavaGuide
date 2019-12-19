package guava.primitives;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Chars;
import com.google.common.primitives.Ints;

/**
 * @author payno
 * @date 2019/12/19 08:53
 * @description
 */
public class Primitives {
    public static void main(String[] args) {
        Bytes.concat(new byte[1],new byte[2]);
        Chars.fromByteArray(new byte[3]);
        Ints.asList(1,2);
    }
}
