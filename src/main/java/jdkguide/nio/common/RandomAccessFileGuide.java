package jdkguide.nio.common;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author payno
 * @date 2019/10/31 16:03
 * @description
 */
public class RandomAccessFileGuide {
    public static void main(String[] args) throws Exception{
        try(FileChannel channel=new RandomAccessFile("d:/random.txt","rw").getChannel()){
            ByteBuffer byteBuffer1=ByteBuffer.allocate(1<<5);
            byteBuffer1.put("payno!!!".getBytes());
            byteBuffer1.flip();
            channel.write(byteBuffer1,channel.size());

            ByteBuffer byteBuffer=ByteBuffer.allocate(1<<5);
            int readByte=0;
            while ((readByte=channel.read(byteBuffer))!=-1){
                //反转缓冲区，Buffer有两种模式，写模式和读模式。在写模式下调用flip()之后，Buffer从写模式变成读模式
                ByteBuffers.print(byteBuffer);
            }
        }
    }
}
