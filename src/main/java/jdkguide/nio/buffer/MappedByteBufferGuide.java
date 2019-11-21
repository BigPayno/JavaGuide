package jdkguide.nio.buffer;

import guava.time.StopWatchRunnable;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.channels.FileChannel.*;

/**
 * @author payno
 * @date 2019/11/21 17:51
 * @description
 *      之前已经试过基于FileChannel，是基于sendFile的零拷贝
 *      mmap是另一种零拷贝
 *
 * MQ:
 *      kafka：record 的读写都是基于 FileChannel。index 读写基于 MMAP（厮大提示）。
 *      RocketMQ：读盘基于 MMAP，写盘默认使用 MMAP，可通过修改配置，配置成 FileChannel，原因是作者想避免 PageCache 的锁竞争，通过两层架构实现读写分离。
 *      QMQ： 去哪儿 MQ，读盘使用 MMAP，写盘使用 FileChannel。
 *      ActiveMQ 5.15： 读写全部都是基于 RandomAccessFile，这也是我们抛弃 ActiveMQ 的原因。
 *
 *      mmap（Memory Mapped Files），简单描述其作用就是：将磁盘文件映射到内存, 用户通过修改内存就能修改磁盘文件。
 *      它的工作原理是直接利用操作系统的Page来实现文件到物理内存的直接映射。完成映射之后你对物理内存的操作会被同步到硬盘上（操作系统在适当的时候）。
 *      通过mmap，进程像读写硬盘一样读写内存（当然是虚拟机内存），也不必关心内存的大小有虚拟内存为我们兜底。使用这种方式可以获取很大的I/O提升，省去了用户空间到内核空间复制的开销。
 *      mmap也有一个很明显的缺陷——不可靠，写到mmap中的数据并没有被真正的写到硬盘，操作系统会在程序主动调用flush的时候才把数据真正的写到硬盘。
 */
public class MappedByteBufferGuide {

    /**
     * 基于网上一个测试，得到如下选择
     *
     * 大小       读           写           force写
     * 小于4kb    mmap
     * 大于4kb    fileChannel
     * 大于64b                fileChannel    fileChannel
     * 小于64b                mmap           fileChannel
     */
    public static class PathRead{
        public static void readFileByHeapBuffer(Path path) throws Exception{
            try(FileChannel channel=FileChannel.open(path)){
                ByteBuffer buffer=ByteBuffer.allocate(1<<10);
                while(channel.read(buffer)!=-1){
                    buffer.flip();
                    buffer.clear();
                }
            }
        }
        public static void readFileByMappedBuffer(Path path) throws Exception{
            MappedByteBuffer buffer;
            byte[] b = new byte[1<<10];
            long offset2=0;
            try(FileChannel channel=FileChannel.open(path)){
                for(long offset=0;offset<channel.size();offset+=1024){
                    if(channel.size()-offset>1024){
                        buffer=channel.map(MapMode.READ_ONLY,offset,offset+1<<10);
                        buffer.get(b);
                        offset2=offset;
                    }else {
                        buffer=channel.map(MapMode.READ_ONLY,offset,channel.size());
                        buffer.get(new byte[(int)(channel.size() - offset)]);
                    }
                }
            }catch (Exception e){
                System.out.println(offset2);
            }
        }
    }

    @Test
    public void test(){
        Path path=Paths.get("d:/HP LoadRunner 11.00安装+破解+汉化.rar");
        StopWatchRunnable.time("HeapBufferRead",()->{
            try{
                PathRead.readFileByHeapBuffer(path);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        StopWatchRunnable.time("MappedBufferRead",()->{
            try{
                PathRead.readFileByMappedBuffer(path);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
