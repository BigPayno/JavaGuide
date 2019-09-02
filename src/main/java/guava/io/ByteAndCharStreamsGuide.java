package guava.io;

import com.google.common.base.Charsets;
import com.google.common.io.*;

import java.io.*;

/**
 * @author payno
 * @date 2019/8/21 19:49
 * @description
 */
public class ByteAndCharStreamsGuide {
    public static void main(String[] args) throws Exception{
        InputStream form= Files.asByteSource(new File("d://2.txt")).openStream();
        OutputStream to = Files.asByteSink(new File("d://1.txt"), FileWriteMode.APPEND).openStream();
        ByteStreams.copy(form,to);
        Reader reader=Files.asCharSource(new File("d://2.txt"), Charsets.UTF_8).openStream();
        Writer writer=Files.asCharSink(new File("d://1.txt"),Charsets.UTF_8).openStream();
        CharStreams.copy(reader,writer);
    }
}
