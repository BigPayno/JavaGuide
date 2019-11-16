package jdkguide.classloader;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.nio.file.FileSystem;

/**
 * @author payno
 * @date 2019/11/15 17:39
 * @description
 *  通过spring生成bean信息，利用loader动态加载class
 *  然后利用反射动态生成所需要的对象并注入容器
 */
public class Demo {
    public static void main(String[] args) {
        FileSystemXmlApplicationContext context=new FileSystemXmlApplicationContext();
    }
}
