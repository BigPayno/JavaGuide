package jdkguide.io;

import com.google.common.io.ByteStreams;
import com.google.common.io.CountingInputStream;
import com.google.common.io.CountingOutputStream;
import jdkguide.thread.Threads;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author payno
 * @date 2019/12/19 16:06
 * @description
 */
public class CountingStreamGuide {
    /**
     * 唤醒long的真实值
     */
    public static synchronized void sync(){ }
    public static void main(String[] args) throws Exception{
        File file=new File("d://test.rar2");
        File file1=new File("d://test.rar3");
        if(!file1.exists()) file1.createNewFile();
        try(final CountingOutputStream cos =
                    new CountingOutputStream(new FileOutputStream(file1));
            FileInputStream fis=
                    new FileInputStream(file)
        ){
            Runnable count=()->{
                while(cos!=null){
                    sync();
                    Threads.sleep(500);
                    System.out.printf("File try read:[%15d/%15d]%n",cos.getCount(),file.length());
                    if(cos.getCount()>100000000L){
                        try{
                            cos.close();
                            sync();
                            System.out.println(cos.getCount()/1024);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            };
            new Thread(count).start();
            try{
                ByteStreams.copy(fis,cos);
            }catch (Exception e){
                System.out.println("something wrong when copy file");
                System.out.printf("copy %15d/%15d%n",cos.getCount(),file.length());
            }
        }
    }
}
