package jdkguide.thread.rwsplit;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author payno
 * @date 2019/11/7 11:49
 * @description
 */
public class ReadWriteSplitFile {
    private final FileChannel fileChannel;
    private final ReadWriteLock readWriteLock;
    public ReadWriteSplitFile(RandomAccessFile randomAccessFile){
        this.fileChannel=randomAccessFile.getChannel();
        this.readWriteLock=new ReentrantReadWriteLock();
    }

    public void write(ByteBuffer byteBuffer,long position){
        readWriteLock.writeLock().lock();
        try{
            Thread.sleep(2000);
            fileChannel.write(byteBuffer,position);
            byteBuffer.clear();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void read(ByteBuffer byteBuffer,long position){
        readWriteLock.readLock().lock();
        try{
            fileChannel.read(byteBuffer,position);
        }catch (IOException io){
            io.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}
