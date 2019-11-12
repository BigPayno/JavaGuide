package jdkguide.nio.common;

import com.google.common.collect.ImmutableList;

import java.nio.channels.*;

/**
 * @author payno
 * @date 2019/10/31 15:01
 * @description
 * 1.第一层
 *
 *     Channel：作为NIO中通道的最顶层接口，抽象的定义描述了通道的概念，通道可以打开可以关闭的行为
 *
 * 2.第二层
 *
 *     ReadableByteChannel：代表具有读能力的通道，描述了通道的可读行为，只要具有可读能力的通道都应该实现它
 *     WriteableByteChannel：代表具有写能力的通道，描述了通道的可写行为，只要具有可写能力的通道都应该实现它
 *     NetworkChannel：代表网络类型的通道，只要连接到Socket套接字，都需要实现它
 *     AsynchronousChannel：代表具有异步I/O操作能力的通道，只是定义了通道异步关闭的行为，具有异步读写能力的通道都应该实现它
 *     InterruptibleChannel：代表可中断式通道，即一个线程阻塞在该通道的I/O上，另一个线程可以关闭通道中断阻塞在该通道上的线程
 *
 * 3.第三层
 *
 *     SeekableByteChannel：可查找的通道，内部维护位置position，通过位置的切换，实现自由读写
 *     ScatteringByteChannel：具有分散能力的通道，即从通道中读取数据至多个Buffer缓冲区的能力
 *     GatheringByteChannel：具有聚集能力的通道，即从多个顺序的Buffer缓冲区中写数据至通道的能力
 */
public class ChannelGuide {
    public static void type(){
        ImmutableList.copyOf(new Class[]{
                FileChannel.class, SocketChannel.class, DatagramChannel.class, ServerSocketChannel.class
        });
    }
}
