package jdkguide.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author payno
 * @date 2019/10/31 14:59
 * @description
 */
public class BufferGuide {
    public static void main(String[] args) {
        //以前看过大概，就这样了
        ByteBuffer byteBuffer=ByteBuffer.allocate(1<<10);
        ByteBuffer byteBuffer1=ByteBuffer.wrap(new byte[1<<10],0,1<<10);
    }
}
