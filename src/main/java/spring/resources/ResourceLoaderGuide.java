package spring.resources;

import com.google.common.io.ByteStreams;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.stream.Stream;

/**
 * @author payno
 * @date 2019/9/20 10:53
 * @description
 */
public class ResourceLoaderGuide {

    @Test
    public void defaultLoader() throws Exception{
        ResourceLoader loader=new DefaultResourceLoader();
        //获取NIO通道
        ReadableByteChannel channel = loader.getResource("classpath:1.txt").readableChannel();
        //获取流
        String result = ByteStreams.readBytes(
                loader.getResource("classpath:1.txt").getInputStream(),
                ByteProcessors.toString(Charset.defaultCharset())
        );
        System.out.println(result);
    }

    @Test
    public void antPathLoader() throws Exception{
        PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        Stream.of(
                resourceLoader.getResources("classpath:?.txt")
        ).forEach(resource -> {
            try{
                ByteStreams.readBytes(
                        resource.getInputStream(),
                        ByteProcessors.print(Charset.defaultCharset(),System.err)
                );
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
