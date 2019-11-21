package jdkguide.nio.buffer;

import org.junit.Test;
import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

/**
 * @author payno
 * @date 2019/11/21 16:31
 * @description
 *      了解大概的Type
 *
 */
public class HeapAndDirectBufferGuide {
    @Test
    /**
     * 了解Buffer的两个子类，Jvm堆内存与直接内存(后者更快)
     * （full gc时会触发堆外空闲内存的回收。）
     *
     * 1.对垃圾回收停顿的改善。因为full gc时，垃圾收集器会对所有分配的堆内内存进行扫描，
     * 垃圾收集对Java应用造成的影响，跟堆的大小是成正比的。过大的堆会影响Java应用的性能
     * 。如果使用堆外内存的话，堆外内存是直接受操作系统管理。这样做的结果就是能保持一个
     * 较小的JVM堆内存，以减少垃圾收集对应用的影响。（full gc时会触发堆外空闲内存的回收。
     *
     * 2.减少了数据从JVM拷贝到native堆的次数，在某些场景下可以提升程序I/O的性能。
     *
     * 3.可以突破JVM内存限制，操作更多的物理内存。
     *
     * 1.堆外内存难以控制，如果内存泄漏，那么很难排查（VisualVM可以通过安装插件来监控堆外内存）。
     * 2.堆外内存只能通过序列化和反序列化来存储，保存对象速度比堆内存慢，不适合存储很复杂的对象。
     * 一般简单的对象或者扁平化的比较适合。
     * 3.直接内存的访问速度（读写方面）会快于堆内存。在申请内存空间时，堆内存速度高于直接内存。
     *
     * >>直接内存适合申请次数少，访问频繁的场合。如果内存空间需要频繁申请，则不适合直接内存。
     */
    public void test(){
        ByteBuffer byteBuffer=ByteBuffer.allocate(1<<10);
        /**
         * if (capacity < 0)
         *             throw new IllegalArgumentException();
         *         return new HeapByteBuffer(capacity, capacity);
         */
        System.out.println(byteBuffer.getClass());

        DirectBuffer byteBuffer1=(DirectBuffer) ByteBuffer.allocateDirect(1<<10);
        /**
         * DirectByteBuffer extends MappedByteBufferGuide
         * java nio引入的文件内存映射方案，读写性能极高
         *
         * 它是MappedByteBuffer类的子类，同时它实现了DirectBuffer接口，
         * 维护一个Cleaner对象来完成内存回收。因此它既可以通过Full GC
         * 来回收内存，也可以调用clean()方法来进行回收
         *
         * return new DirectByteBuffer(capacity);
         */
        System.out.println(byteBuffer1.getClass());
       //DirectBuffer可以得到Cleaner来管理对象
        byteBuffer1.cleaner().clean();
    }
}
