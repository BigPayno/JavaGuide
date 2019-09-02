package utils.charset;

import com.google.common.io.ByteSource;

import java.nio.charset.Charset;

/**
 * @author payno
 * @date 2019/8/27 11:22
 * @description
 */
public final class Charsets {
    public static final Charset US_ASCII = Charset.forName("US-ASCII");
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
    public static final Charset UTF_16LE = Charset.forName("UTF-16LE");
    public static final Charset UTF_16 = Charset.forName("UTF-16");
    public static final Charset GBK = Charset.forName("GBK");
    private Charsets() {
    }
    public static ByteSource encodeTo(ByteSource source,Charset from,Charset to){
        return source.asCharSource(from).asByteSource(to);
    }
}
