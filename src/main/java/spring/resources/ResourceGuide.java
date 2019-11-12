package spring.resources;

import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.web.context.support.ServletContextResource;
import utils.charset.Charsets;

import java.nio.charset.Charset;

/**
 * @author payno
 * @date 2019/9/19 11:19
 * @description
 */
public class ResourceGuide {
    public static void main(String[] args) throws Exception{
        FileSystemResource absoluteResource=new FileSystemResource("d://parser//01.json");
        String result=ByteSource.wrap(ByteStreams.toByteArray(absoluteResource.getInputStream()))
                .asCharSource(Charsets.UTF_8).read();
        FileSystemResource relativeResource=new FileSystemResource("\\1.txt");
        System.out.println(relativeResource.getFile().getAbsolutePath());
        ClassPathResource classPathResource=new ClassPathResource("/1.txt");
        System.out.println(classPathResource.exists());
        ServletContextResource warResource;
        UrlResource urlResource=new UrlResource("ftp://payno:9527@127.0.0.1/01.json");

        System.out.println(urlResource.exists());
        /*String result2=ByteSource.wrap(ByteStreams.toByteArray(urlResource.getInputStream()))
                .asCharSource(Charsets.UTF_8).read();
        System.out.println(result2);*/
    }
}
