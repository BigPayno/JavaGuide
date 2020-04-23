package spring.resources;

import com.google.common.io.ByteProcessor;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author payno
 * @date 2020/4/23 14:45
 * @description
 */
public class ByteToString implements ByteProcessor<String> {

    public ByteToString(Charset charset){
        this.charset =charset;
    }

    String result = "";
    Charset charset;

    @Override
    public boolean processBytes(byte[] buf, int off, int len) throws IOException {
        result = new String(buf,charset);
        return false;
    }

    @Override
    public String getResult() {
        return result;
    }
}
