package utils.io.streams;

import com.google.common.io.Files;
import utils.charset.Charsets;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author payno
 * @date 2019/9/25 18:17
 * @description
 */
public class Test {
    public static void main(String[] args) throws Exception{
        InputStream source= Files.asByteSource(new File("d://parser//01.json")).openStream();
        InputStreamReader reader=new InputStreamReader(source, Charsets.UTF_8);
    }
}
