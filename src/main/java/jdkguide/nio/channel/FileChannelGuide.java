package jdkguide.nio.channel;

import guava.time.StopWatch;
import guava.time.StopWatchRunnable;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author payno
 * @date 2019/11/4 15:02
 * @description
 */
public class FileChannelGuide {
    public static void main(String[] args){
        StopWatchRunnable.time("Channel 基于 ByteBuffer 复制文件",()->{
            try(FileChannel sourceChannel=new RandomAccessFile("d:/HP LoadRunner 11.00安装+破解+汉化.rar","r").getChannel();
                FileChannel sinkChannel=new RandomAccessFile("d:/HP LoadRunner 11.00安装+破解+汉化.rar2","rw").getChannel()
            ){
                ByteBuffer byteBuffer=ByteBuffer.allocate(1<<12);
                int readFlag=0;
                while((readFlag=sourceChannel.read(byteBuffer))!=-1){
                    byteBuffer.flip();
                    sinkChannel.write(byteBuffer);
                    byteBuffer.clear();
                }
            }catch (IOException io){
                io.printStackTrace();
            }
        });
        StopWatchRunnable.time("Channel 基于 FileChannel 复制文件",()->{
            try(FileChannel sourceChannel=new RandomAccessFile("d:/HP LoadRunner 11.00安装+破解+汉化.rar","r").getChannel();
                FileChannel sinkChannel=new RandomAccessFile("d:/HP LoadRunner 11.00安装+破解+汉化.rar2","rw").getChannel()
            ){
                sourceChannel.transferTo(0,sourceChannel.size(),sinkChannel);
            }catch (IOException io){
                io.printStackTrace();
            }
        });
    }
}
