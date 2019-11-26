package jdkguide.nio.files;

import com.google.common.io.ByteStreams;
import guava.time.StopWatchRunnable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author payno
 * @date 2019/11/26 10:06
 * @description
 *      通过Files得到的流是通道流
 *      性能低于普通文件流
 */
public class FilesGuide {
    public static void main(String[] args) {
        Path path= Paths.get("d:/test.xlsx");
        StopWatchRunnable.time("nio Stream",()->{
            try(InputStream source= Files.newInputStream(path)){
                ByteStreams.copy(source,new ByteArrayOutputStream());
                System.out.println(source.getClass());
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        StopWatchRunnable.time("file Stream",()->{
            try(FileInputStream source= new FileInputStream(path.toFile())){
                ByteStreams.copy(source,new ByteArrayOutputStream());
                System.out.println(source.getClass());
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
