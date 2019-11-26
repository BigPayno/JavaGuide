package apache.common;

import com.google.common.io.ByteStreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author payno
 * @date 2019/11/26 09:31
 * @description
 */
public class CompressGuide {
    public static void main(String[] args) throws Exception{
        ZipOutputStream zipSink=new ZipOutputStream(new FileOutputStream("d:/random.txt.zip"));
        InputStream zipSource= Files.newInputStream(Paths.get("d:/random.txt"));
        zipSink.putNextEntry(new ZipEntry("random.txt"));
        ByteStreams.copy(zipSource,zipSink);
        zipSink.closeEntry();
        zipSink.putNextEntry(new ZipEntry("random.txt2"));
        zipSource=Files.newInputStream(Paths.get("d:/random.txt"));
        ByteStreams.copy(zipSource,zipSink);
        zipSink.closeEntry();
        zipSink.close();
    }
}
