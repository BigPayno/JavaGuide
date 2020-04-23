package guava.io;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import utils.charset.Charsets;

import java.net.URL;

/**
 * @author payno
 * @date 2019/8/21 19:50
 * @description
 */
public class ResourcesGuide {
    public static void main(String[] args) throws Exception{
        //ByteSource resources=Resources.asByteSource(new URL("www"));
        String text = Resources.asCharSource(
                Resources.getResource("1.txt"), Charsets.UTF_8).read();
        System.out.println(text);
    }
}
