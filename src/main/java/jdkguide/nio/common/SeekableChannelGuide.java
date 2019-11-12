package jdkguide.nio.common;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

/**
 * @author payno
 * @date 2019/11/4 14:45
 * @description
 */
public class SeekableChannelGuide {
    public static void main(String[] args) throws Exception{
        SeekableByteChannel seekableByteChannel=new RandomAccessFile("d:/random.txt","r").getChannel();
        seekableByteChannel.position(5);
        ByteBuffer byteBuffer=ByteBuffer.allocate(1<<5);
        int readByte=0;
        while((readByte=seekableByteChannel.read(byteBuffer))!=-1){
            ByteBuffers.print(byteBuffer);
        }
    }
}
