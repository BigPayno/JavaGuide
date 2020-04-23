package spring.resources;

import com.google.common.io.ByteProcessor;

import java.io.PrintStream;
import java.nio.charset.Charset;

/**
 * @author payno
 * @date 2020/4/23 14:48
 * @description
 */
public final class ByteProcessors {
    public static ByteProcessor<String> toString(Charset charset){
        return new ByteToString(charset);
    }

    public static ByteProcessor<Void> print(Charset charset, PrintStream printStream){
        return new BytePrinter(charset,printStream);
    }

    public static ByteProcessor<Void> print(Charset charset){
        return new BytePrinter(charset);
    }
}
