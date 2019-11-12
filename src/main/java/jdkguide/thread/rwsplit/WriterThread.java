package jdkguide.thread.rwsplit;

import java.nio.ByteBuffer;

/**
 * @author payno
 * @date 2019/11/7 14:47
 * @description
 */
public class WriterThread extends Thread{
    private ReadWriteSplitFile readWriteSplitFile;
    private static Integer id=0;
    public WriterThread(ReadWriteSplitFile readWriteSplitFile){
        this.readWriteSplitFile=readWriteSplitFile;
    }

    @Override
    public void run() {
        while (true){
            ByteBuffer byteBuffer=ByteBuffer.wrap(getNo().toString().getBytes());
            readWriteSplitFile.write(byteBuffer,0);
            System.out.println(Thread.currentThread().getName()+" write "+new String(byteBuffer.array()));
        }
    }

    public static synchronized Integer getNo(){
        id++;
        return id;
    }
}
