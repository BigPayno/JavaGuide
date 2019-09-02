package guava.io;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;

/**
 * @author payno
 * @date 2019/8/16 09:59
 * @description
 */
public class SourceAndSinkGuide {
    public static void main(String[] args) throws Exception{
        File from=new File("d://parser//baffle//tongDun//320723199611193816.json");
        File to=new File("d://1.json");
        Files.asCharSource(from, Charsets.UTF_8)
                .copyTo(Files.asCharSink(to,Charsets.UTF_8));
    }
}
