package jdkguide.io;

import com.google.common.io.CountingInputStream;
import com.sun.deploy.util.SyncFileAccess;
import jdkguide.thread.Threads;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;

import java.io.*;

/**
 * @author payno
 * @date 2019/12/16 09:52
 * @description
 *      FilterOutputStream只需要包装实现了write方法的流便能实现相关接口
 *      write是模板方法，其重载的其他方法基于模板
 *      同理便是InputStream
 *      比较常用的就是CountingStreams
 *      常用拓展类有:
 *      CountingStream,PushStream
 */
public class FilterStreamGuide {
    @Test
    public void count() {
        String str="payne";
        CountingInputStream cis=new CountingInputStream(new ByteArrayInputStream(str.getBytes()));
        new Thread(()->{
            while (true){
                Threads.sleep(500);
                try {
                    cis.read();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        },"readThread").start();
        new Thread(()->{
            while (cis.getCount()<=str.length()){
                Threads.sleep(200);
                System.out.printf("count: %d,all %d%n",cis.getCount(),str.length());
                if(cis.getCount()==str.length()){
                    break;
                }
            }
        },"listener").start();
        Threads.sleep(5000);
    }

    @Test
    public void push() throws Exception{
        String str="payne";
        ByteArrayInputStream bis=new ByteArrayInputStream(str.getBytes());
        PushbackInputStream pis=new PushbackInputStream(bis);
        int buffer=0;
        while((buffer=pis.read())!=-1){
            if(buffer==(int)'a'){
                pis.unread(buffer);
                pis.skip(1);
            }else {
                System.out.print((char)buffer);
            }
        }
    }
}
