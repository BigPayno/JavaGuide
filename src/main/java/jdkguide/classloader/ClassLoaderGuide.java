package jdkguide.classloader;

import com.google.common.io.ByteStreams;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author payno
 * @date 2019/11/18 15:04
 * @description
 *  ClassLoader(抽象类)   <-----     URLClassLoader(常用的实现类)
 *
 *     defineClass(byte[]，int，int) -- 将byte字节流解析成JVM能够识别的对象
 *     findClass(String) -- 实现类的加载规则，取得上述函数需要的字节码
 *     loadClass(String) -- 获取这个类的Class对象
 *     resolveClass(Class<?>) -- 类的链接(link)
 */
public class ClassLoaderGuide {
    public static class ResourceClassLoader extends ClassLoader {
        private DefaultResourceLoader resourceLoader=new DefaultResourceLoader();
        private Map<String,String> resourceMap=new ConcurrentHashMap<>(16);
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException{
            if(resourceMap.containsKey(name)){
                try(InputStream io=resourceLoader.getResource(resourceMap.get(name)).getInputStream()){
                    byte[] resource= ByteStreams.toByteArray(io);
                    return defineClass(name,resource,0,resource.length);
                }catch (IOException e){
                    throw new ClassNotFoundException("the resource io occurs exception!");
                }
            }else {
                return super.loadClass(name);
            }
        }

        public void resolve(String name,String resource){
            resourceMap.put(name,resource);
        }

        public void clear(){
            resourceMap.clear();
        }
    }

    public static void main(String[] args) throws Exception{
        ResourceClassLoader loader=new ResourceClassLoader();
        loader.resolve("jdkguide.print.PrintfGuide","file:D:\\test\\guide\\target\\classes\\jdkguide\\print\\PrintfGuide.class");
        Class<?> clazz=loader.loadClass("jdkguide.print.PrintfGuide");
        Class.forName("jdkguide.print.PrintfGuide");
    }
}
