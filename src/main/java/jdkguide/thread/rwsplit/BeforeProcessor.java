package jdkguide.thread.rwsplit;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * @author payno
 * @date 2019/11/7 11:59
 * @description
 */
public class BeforeProcessor {
    public static void main(String[] args) throws Exception{
       ReadWriteSplitFile readWriteSplitFile=new ReadWriteSplitFile(new RandomAccessFile("d://random.txt","rw"));
       ByteBuffer byteBuffer=ByteBuffer.allocate(1<<5);
       readWriteSplitFile.read(byteBuffer,0);
       System.out.println(new String(byteBuffer.array()));
       byteBuffer.put("abcde".getBytes());
       byteBuffer.flip();
       readWriteSplitFile.write(byteBuffer,1);
    }
}
