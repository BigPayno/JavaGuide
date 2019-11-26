package jdkguide.nio.common;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author payno
 * @date 2019/11/26 11:48
 * @description
 *      pipe是单向的管道
 *      消费者，生产者模式
 */
public class PipeGuide {
    public static void main(String[] args) throws Exception{
        Pipe pipe=Pipe.open();
        Pipe.SourceChannel sourceChannel=pipe.source();
        Pipe.SinkChannel sinkChannel=pipe.sink();
        ByteBuffer byteBuffer=ByteBuffer.wrap("hello".getBytes());
        /**
         * 管道是sink
         */
        sinkChannel.write(byteBuffer);
        ByteBuffer byteBuffer1=ByteBuffer.allocate(1<<6);
        sourceChannel.read(byteBuffer1);
        byteBuffer1.flip();
        System.out.println(new String(byteBuffer1.array()));
    }
}
