package jdkguide.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.ScatteringByteChannel;

/**
 * @author payno
 * @date 2019/11/26 10:57
 * @description
 *       ScatteringByteChannel：具有分散能力的通道，即从通道中读取数据至多个Buffer缓冲区的能力
 *       file:
 *       FileChannel
 *       net:
 *       SocketChannel
 *       DatagramChannel
 *       pipe:
 *       SourceChannel
 *
 *       ZipFileSystem
 */
public class ScatterChannelGuide {
    public static void main(String[] args) throws Exception{
        ScatteringByteChannel channel=new RandomAccessFile("d:/jsonpath.json","rw").getChannel();
        ByteBuffer byteBuffer1=ByteBuffer.allocate(1<<10);
        ByteBuffer byteBuffer2=ByteBuffer.allocate(1<<10);
        ByteBuffer[] byteBuffers={byteBuffer1,byteBuffer2};
        channel.read(byteBuffers);
        System.out.println(byteBuffer1.toString());
        System.out.println(byteBuffer2.toString());
        byteBuffer1.flip();
        byteBuffer2.flip();
    }
}
