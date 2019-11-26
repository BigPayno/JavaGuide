package jdkguide.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author payno
 * @date 2019/11/4 14:59
 * @description
 */
public final class ByteBuffers {
    public static void print(ByteBuffer byteBuffer){
        //反转ByteBuffer，转换读写模式
        byteBuffer.flip();
        //移动指针
        while (byteBuffer.hasRemaining()){
            System.out.print((char)byteBuffer.get());
        }
        //清除ByteBuffer
        byteBuffer.clear();
    }
}
