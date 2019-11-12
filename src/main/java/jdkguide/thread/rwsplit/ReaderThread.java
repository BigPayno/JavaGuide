package jdkguide.thread.rwsplit;

import java.nio.ByteBuffer;

/**
 * @author payno
 * @date 2019/11/7 14:44
 * @description
 */
public class ReaderThread extends Thread{
    private ReadWriteSplitFile readWriteSplitFile;
    public ReaderThread(ReadWriteSplitFile readWriteSplitFile){
        this.readWriteSplitFile=readWriteSplitFile;
    }

    @Override
    public void run() {
        while (true){
            ByteBuffer byteBuffer=ByteBuffer.allocate(1<<5);
            readWriteSplitFile.read(byteBuffer,0);
            System.out.println(Thread.currentThread().getName()+" read "+new String(byteBuffer.array()));
        }
    }
}
