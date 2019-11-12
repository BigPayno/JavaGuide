package jdkguide.thread.rwsplit;

import org.junit.Test;

import java.io.RandomAccessFile;

/**
 * @author payno
 * @date 2019/11/7 14:50
 * @description
 */
public class Sence {
    @Test
    public void read() throws Exception{
        ReadWriteSplitFile readWriteSplitFile=new ReadWriteSplitFile(new RandomAccessFile("d://random.txt","rw"));
        new ReaderThread(readWriteSplitFile).start();
        new ReaderThread(readWriteSplitFile).start();
        new ReaderThread(readWriteSplitFile).start();
    }

    @Test
    public void readAndWrite() throws Exception{
        ReadWriteSplitFile readWriteSplitFile=new ReadWriteSplitFile(new RandomAccessFile("d://random.txt","rw"));
        new ReaderThread(readWriteSplitFile).start();
        new WriterThread(readWriteSplitFile).start();
        new ReaderThread(readWriteSplitFile).start();
        new ReaderThread(readWriteSplitFile).start();
    }

    public static void main(String[] args) throws Exception{
        ReadWriteSplitFile readWriteSplitFile=new ReadWriteSplitFile(new RandomAccessFile("d://random.txt","rw"));
        new ReaderThread(readWriteSplitFile).start();
        new WriterThread(readWriteSplitFile).start();
        new ReaderThread(readWriteSplitFile).start();
        new ReaderThread(readWriteSplitFile).start();
    }
}
