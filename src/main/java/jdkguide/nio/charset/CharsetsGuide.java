package jdkguide.nio.charset;

import utils.charset.Charsets;

import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author payno
 * @date 2019/11/26 10:37
 * @description
 */
public class CharsetsGuide {
    public static void main(String[] args) throws Exception{
        CharsetDecoder decoder= StandardCharsets.UTF_8.newDecoder();
        CharsetEncoder encoder= Charsets.GBK.newEncoder();
        ByteBuffer byteBuffer=ByteBuffer.wrap(
                "你好,Payne".getBytes(StandardCharsets.UTF_8));
        /**
         * 将UTF-8的字节转换为字符流
         */
        System.out.println(decoder.decode(byteBuffer).toString());
        /**
         * 得到gbk的字节流
         */
        byteBuffer.flip();
        ByteBuffer byteBuffer1=encoder.encode(
                decoder.decode(byteBuffer)
        );
        byte[] bytes=byteBuffer1.array();
        System.out.println(new String(bytes,0,bytes.length,Charsets.GBK));
    }
}
