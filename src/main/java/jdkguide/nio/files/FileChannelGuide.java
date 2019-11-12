package jdkguide.nio.files;


import com.google.common.base.Stopwatch;
import com.google.common.io.Files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.Files.*;

/**
 * @author payno
 * @date 2019/10/30 15:23
 * @description  largeFile : FileChannel>>Guava.BufferedStream=Nio.Filesx
 */
public class FileChannelGuide {
    private static Path srcPath=Paths.get("d:/HP LoadRunner 11.00安装+破解+汉化.rar");
    private static Path destPath=Paths.get("d:/HP LoadRunner 11.00安装+破解+汉化.rar2");
    public static void init() throws Exception{
        deleteIfExists(destPath);
        createFile(destPath);
    }
    public static void main(String[] args) throws Exception{
        init();
        //FileChannel是阻塞的，无法切换到非阻塞状态
        try(FileChannel src= new FileInputStream(srcPath.toFile()).getChannel();
            FileChannel dest=new FileOutputStream(destPath.toFile()).getChannel()
        ){
            Stopwatch stopwatch=Stopwatch.createStarted();
            //只有文件通道有Transfer方法
            src.transferTo(0,src.size(),dest);
            System.out.printf("基于FileChannel,共计耗时%s%n",stopwatch.stop());
        }
        init();
        try(InputStream is= Files.asByteSource(srcPath.toFile()).openBufferedStream()){
            Stopwatch stopwatch=Stopwatch.createStarted();
            Files.asByteSink(destPath.toFile()).writeFrom(is);
            System.out.printf("基于Guava.BufferedStream,共计耗时%s%n",stopwatch.stop());
        }
        deleteIfExists(destPath);
        Stopwatch stopwatch=Stopwatch.createStarted();
        copy(srcPath,destPath);
        System.out.printf("基于nio.files,共计耗时%s%n",stopwatch.stop());
    }
}
