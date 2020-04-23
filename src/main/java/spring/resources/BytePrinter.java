package spring.resources;

import com.google.common.io.ByteProcessor;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

/**
 * @author payno
 * @date 2020/4/23 14:57
 * @description
 */
@AllArgsConstructor
public class BytePrinter implements ByteProcessor<Void> {
    public BytePrinter(Charset charset) {
        this.charset = charset;
        this.printStream = System.out;
    }

    Charset charset;
    PrintStream printStream;

    @Override
    public boolean processBytes(byte[] buf, int off, int len) throws IOException {
        printStream.println(new String(buf,charset));
        return false;
    }

    @Override
    public Void getResult() {
        return null;
    }
}
