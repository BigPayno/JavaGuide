package jdkguide.classloader;

import com.google.common.io.ByteStreams;
import jdkguide.print.PrintfGuide;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.OverridingClassLoader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.nio.file.FileSystem;

/**
 * @author payno
 * @date 2019/11/15 17:39
 * @description
 *  通过spring生成bean信息，利用loader动态加载class
 *  然后利用反射动态生成所需要的对象并注入容器
 */
public class Demo {
    public static class ResourceClassLoader extends OverridingClassLoader{
        public ResourceClassLoader(ClassLoader parent) {
            super(parent);
        }

        public Class<?> loadClass(String name, Resource resource) throws ClassNotFoundException, IOException {
            byte[] bytes=ByteStreams.toByteArray(resource.getInputStream());
            return this.defineClass(name,bytes,0,bytes.length);
        }
    }

    public static void main(String[] args) throws Exception{
        //Spring默认的从classPath加载
        ResourceClassLoader resourceClassLoader=new ResourceClassLoader(ClassUtils.getDefaultClassLoader());
        DefaultResourceLoader resourceLoader=new DefaultResourceLoader();
        Resource resource=resourceLoader
                .getResource("file:D:\\test\\guide\\target\\classes\\jdkguide\\print\\PrintfGuide.class");
        resourceClassLoader.loadClass("jdkguide.print.PrintfGuide",resource);
        Class<?> clazz=Class.forName("jdkguide.print.PrintfGuide");
    }
}
