package netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * @author payno
 * @date 2019/12/6 16:49
 * @description
 *      ByteBuf存在两种读写
 *      1.指针读写
 *      2.随机读写
 */
public class ByteBufAccessGuide {
    @Test
    public void indexAccess(){
        ByteBuf byteBuf= Unpooled.wrappedBuffer("hello".getBytes());
        long index=byteBuf.readerIndex();
        byteBuf.readBytes(new byte[2]);
        long indexAfterRead=byteBuf.readerIndex();
        System.out.printf("ReadIndex:%n    %d AfterRead 2Byte %d",index,indexAfterRead);
    }

    @Test
    public void randomAccess(){
        ByteBuf byteBuf= Unpooled.wrappedBuffer("hello".getBytes());
        long index=byteBuf.readerIndex();
        byteBuf.getByte(0);
        byteBuf.getByte(1);
        long indexAfterRead=byteBuf.readerIndex();
        System.out.printf("ReadIndex:%n    %d AfterRead 2Byte %d",index,indexAfterRead);
    }
}
