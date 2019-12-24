package spi;

import java.util.ServiceLoader;

/**
 * @author payno
 * @date 2019/12/24 15:51
 * @description
 *    通过SPI的方式，第三方服务模块实现接口后，在第三方的项目代码的META-INF/services目录下
 * 的配置文件指定实现类的全路径名，源码框架即可找到实现类
 *
 * 缺点：
 *     虽然ServiceLoader也算是使用的延迟加载，但是基本只能通过遍历全部获取，也就是接口的实现
 *     类全部加载并实例化一遍。如果你并不想用某些实现类，它也被加载并实例化了，这就造成了浪费
 *     获取某个实现类的方式不够灵活，只能通过Iterator形式获取，不能根据某个参数来获取对应
 *     的实现类。
 *     多个并发多线程使用ServiceLoader类的实例是不安全的。
 *
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<SPI> spiServiceLoader=ServiceLoader.load(SPI.class);
        for(SPI spi:spiServiceLoader){
            spi.display();
        }
    }
}
