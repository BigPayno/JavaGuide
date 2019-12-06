package netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import jdkguide.reflect.Objs;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author payno
 * @date 2019/12/6 15:18
 * @description
 *      在Netty中，一个ByteBuf有如下区别:
 *      1.Direct、Heap 堆外，堆内 内存
 *      2.Pooled、Un pooled 池化、非池化
 */
public class ByteBufsGuide {
    /**
     * 类似于Guava中的Lists等类，Unpooled提供了一些操作非池化Buf的
     * 通用静态工厂方法,是对ByteBufAllocator的封装
     */
    @Test
    public void directOrHeap(){
        /**
         * Unpooled.buffer():
         * UnpooledByteBufAllocator
         *      $InstrumentedUnpooledUnsafeHeapByteBuf
         * (riIx:0,widIx:0,cap:256)
         */
        ByteBuf byteBuf=Unpooled.buffer();
        /**
         * Unpooled.Unpooled.directBuffer():
         * UnpooledByteBufAllocator
         *      $InstrumentedUnpooledUnsafeNoCleanerDirectByteBuf
         * (riIx:0,widIx:0,cap:256)
         */
        ByteBuf byteBuf1=Unpooled.directBuffer();
        Objs.printC(byteBuf);
        Objs.printC(byteBuf1);
    }

    /**
     * 提供了从其他内存拷贝成为Buf的方法
     * deep copy
     */
    @Test
    public void copyFrom(){
        /**
         * Package [io.netty.buffer]
         * Class [UnpooledHeapByteBuf]
         * toString [UnpooledHeapByteBuf(ridx: 0, widx: 2, cap: 2/2)]
         */
        ByteBuf copyFromByteArray=Unpooled.copiedBuffer(new byte[2]);
        /**
         * Package [io.netty.buffer]
         * Class [UnpooledByteBufAllocator]
         * InnerClass [InstrumentedUnpooledUnsafeHeapByteBuf]
         * toString [UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 2, cap: 2)]
         */
        ByteBuf copyFromByteBuf=Unpooled.copiedBuffer(copyFromByteArray);
        /**
         * Package [io.netty.buffer]
         * Class [UnpooledHeapByteBuf]
         * toString [UnpooledHeapByteBuf(ridx: 0, widx: 2, cap: 2/2)]
         */
        ByteBuf copyFromByteBuffer=Unpooled.copiedBuffer(ByteBuffer.wrap(new byte[2]));
        Objs.printC(copyFromByteArray);
        Objs.printC(copyFromByteBuf);
        Objs.printC(copyFromByteBuffer);

        /**
         * 是深拷贝？
         */
        boolean source=false;
        ByteBuf copyFromBoolean=Unpooled.copyBoolean(source);
        boolean read=copyFromBoolean.readBoolean();
        copyFromBoolean.writeBoolean(true);
        System.out.println(source);
    }

    /**
     * composite类似wrap是浅拷贝，只是拷贝引用
     * 操作会影响到真实的数据
     * 因为是浅拷贝，所以做到了零拷贝
     */
    @Test
    public void composite(){
        /**
         * Package [io.netty.buffer]
         * Class [CompositeByteBuf]
         * toString [CompositeByteBuf(ridx: 0, widx: 0, cap: 0, components=1)]
         */
        CompositeByteBuf compositeByteBuf=Unpooled.compositeBuffer(2);
        compositeByteBuf.addComponents(Unpooled.EMPTY_BUFFER);
        Objs.printC(compositeByteBuf);
    }

    @Test
    public void wrap(){
        /**
         * 浅拷贝，包装后的buf是可读不可写的
         * 对于buf的操作会影响原本的数据
         */
        byte[] bytes="hello".getBytes();
        byte[] bytes2=new byte[5];
        ByteBuf byteBuf=Unpooled.wrappedBuffer(bytes);
        Objs.printC(byteBuf);
        /**
         * 包装后的Buf将byteBuf的内容写入bytes2
         * 读取后，Buf变成不可读不可写的
         */
        System.out.println(byteBuf);
        byteBuf.readBytes(bytes2);
        System.out.println(byteBuf);
        /**
         * 清空后的Buf可以写了，但是对该Buf的写入会影响
         * 原本的数据，clear不会清除bytes
         */
        byteBuf.clear();
        System.out.println(new String(bytes));
        byteBuf.writeBytes("heool".getBytes());

        System.out.println(new String(bytes));
        System.out.println(new String(bytes2));
    }

    @Test
    public void other(){
        /**
         * 包装成不可变的Buf，无法再进行操作的
         */
        ByteBuf byteBuf1=Unpooled.wrappedUnmodifiableBuffer();
        /**
         * 不可释放，用于包装池化buffer，使之不会被释放调
         */
        ByteBuf byteBuf2=Unpooled.unreleasableBuffer(Unpooled.EMPTY_BUFFER);
        Objs.printC(byteBuf1);
        Objs.printC(byteBuf2);
    }

    /**
     * 和composite相反的操作，依旧是浅拷贝
     * 可以切割数据
     * 该方法是Buf的（非静态）方法
     */
    @Test
    public void slice(){
        /**
         * 深拷贝数组，得到源Buf
         * 浅拷贝Buf并重新组合，最终读取
         */
        ByteBuf source=Unpooled.copiedBuffer("hello".getBytes());
        System.out.println(source);
        ByteBuf part1=source.slice(0,2);
        ByteBuf part2=source.slice(2,3);
        CompositeByteBuf composite=Unpooled.compositeBuffer(2);
        /**
         * 默认addComponents不会设置写指针
         * 会是0，0，cap
         * 设置之后是cap，cap，cap
         */
        composite.addComponents(true,part2,part1);
        byte[] sink=new byte[5];
        composite.readBytes(sink);
        System.out.println(new String(sink));
    }

    /**
     * 对于池化的ByteBuf必须进行释放，否则会造成内存泄漏
     */
    @Test
    public void pool(){
        /**
         * 创建池化的优先堆内内存的分配器
         */
        PooledByteBufAllocator poolAllocator=
                new PooledByteBufAllocator(false);
        ByteBuf byteBuf=poolAllocator.buffer();
        ByteBuf byteBuf1=poolAllocator.compositeBuffer();
        ByteBuf byteBuf2=poolAllocator.heapBuffer();
        if (poolAllocator.isDirectBufferPooled()){
            System.out.println("...........");
            ByteBuf byteBuf3=poolAllocator.directBuffer();
            byteBuf3.release();
        }
        ByteBuf byteBuf4=poolAllocator.ioBuffer();
        byteBuf.release();
        byteBuf1.release();
        byteBuf2.release();
        byteBuf4.release();
    }
}
