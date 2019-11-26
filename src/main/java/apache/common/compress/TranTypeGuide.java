package apache.common.compress;

import com.google.common.io.ByteStreams;
import guava.time.StopWatchRunnable;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author payno
 * @date 2019/11/26 09:50
 * @description
 *      压缩
 *      设计到流的操作编码解码还是aio吧
 */
public class TranTypeGuide {
    private static final String TEST_SOURCE="d:/test.xlsx";
    private static final String TEST_SINK_DIR="d:/compress";
    @Before
    public void buildDir(){
        Path dir=Paths.get(TEST_SINK_DIR);
        if(!dir.toFile().exists()){
            dir.toFile().mkdir();
        }
    }

    @Test
    /**
     * 40m 1.957s
     */
    public void fileStream(){
        StopWatchRunnable.time("common stream",()->{
            try(
                    FileInputStream source= new FileInputStream(Paths.get(TEST_SOURCE).toFile());
                    ZipOutputStream sink=new ZipOutputStream(
                            new FileOutputStream(Paths.get(TEST_SINK_DIR,"common.zip").toFile()))){
                sink.putNextEntry(new ZipEntry("test.xlsx"));
                ByteStreams.copy(source,sink);
                sink.closeEntry();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    @Test
    /**
     * 40m 1.901 稍微快点
     */
    public void fileBufferedStream(){
        StopWatchRunnable.time("bufferedStream",()->{
            try(
                    FileInputStream source= new FileInputStream(Paths.get(TEST_SOURCE).toFile());
                    ZipOutputStream sink=new ZipOutputStream(
                            new FileOutputStream(Paths.get(TEST_SINK_DIR,"buffered.zip").toFile()))){
                BufferedInputStream bufferedSource=new BufferedInputStream(source);
                BufferedOutputStream bufferedSink=new BufferedOutputStream(sink);
                sink.putNextEntry(new ZipEntry("test.xlsx"));
                ByteStreams.copy(bufferedSource,bufferedSink);
                sink.closeEntry();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    @Test
    /**
     * 40m 1.985s/1.919 设计到操作还是流好点
     */
    public void fileChannel(){
        StopWatchRunnable.time("common channel",()->{
            try(
                    FileChannel source= new RandomAccessFile(TEST_SOURCE,"r").getChannel();
                    ZipOutputStream sink=new ZipOutputStream(
                            new FileOutputStream(Paths.get(TEST_SINK_DIR,"common.zip").toFile()))){
                sink.putNextEntry(new ZipEntry("test.xlsx"));
                source.transferTo(0,source.size(), Channels.newChannel(sink));
                //ByteStreams.copy(source, Channels.newChannel(sink));
                sink.closeEntry();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    @Test
    /**
     * 40m 1.983s
     */
    public void fileMap(){
        StopWatchRunnable.time("mmap channel",()->{
            try(
                    FileChannel source= new RandomAccessFile(TEST_SOURCE,"r").getChannel();
                    ZipOutputStream sink=new ZipOutputStream(
                            new FileOutputStream(Paths.get(TEST_SINK_DIR,"common.zip").toFile()))){
                sink.putNextEntry(new ZipEntry("test.xlsx"));
                MappedByteBuffer mappedByteBuffer = source
                        .map(FileChannel.MapMode.READ_ONLY, 0, source.size());
                Channels.newChannel(sink).write(mappedByteBuffer);
                sink.closeEntry();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
