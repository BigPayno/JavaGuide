package netty.other.file;

import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author payno
 * @date 2019/12/6 16:43
 * @description
 *      当有了 FileRegion 后, 我们就可以直接通过它将文件的内容直接写入 Channel 中,
 *      而不需要像传统的做法: 拷贝文件内容到临时 buffer, 然后再将 buffer 写入 Channel.
 *      通过这样的零拷贝操作, 无疑对传输大文件很有帮助.
 */
public class FileRegionGuide {
    public static void main(String[] args) throws Exception{
        /**
         *  if (ctx.pipeline().get(SslHandler.class) == null) {
         *         // SSL not enabled - can use zero-copy file transfer.
         *         // 2. 调用 raf.getChannel() 获取一个 FileChannel.
         *         // 3. 将 FileChannel 封装成一个 DefaultFileRegion
         *         ctx.write(new DefaultFileRegion(raf.getChannel(), 0, length));
         *     } else {
         *         // SSL enabled - cannot use zero-copy file transfer.
         *         ctx.write(new ChunkedFile(raf));
         *     }
         */
        RandomAccessFile randomAccessFile=new RandomAccessFile("d:\\aum.csv","rw");
        FileChannel channel=randomAccessFile.getChannel();
        FileRegion fileRegion=new DefaultFileRegion(channel,0,channel.size());
    }
}
