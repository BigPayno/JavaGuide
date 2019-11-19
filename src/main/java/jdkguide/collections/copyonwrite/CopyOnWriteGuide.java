package jdkguide.collections.copyonwrite;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author payno
 * @date 2019/8/13 21:14
 * @description 读写分离
 *  CopyOnWrite  写时复制， 在往集合中添加数据的时候，先拷贝存储的数组，
 *  然后添加元素到拷贝好的数组中，然后用现在的数组去替换成员变量的数组
 *  （就是get等读取操作读取的数组）。这个机制和读写锁是一样的，但是比
 *  读写锁有改进的地方，那就是读取的时候可以写入的 ，这样省去了读写之
 *  间的竞争，看了这个过程，你也发现了问题，同时写入的时候怎么办呢，当
 *  然果断还是加锁。 这体现了读写分离的思想：在写操作的线程，会将数组复
 *  制出来一份进行操作。而原本的数组不会做改变。读线程则不会受到影响，
 *  但是可能读到的是一个过期的数据。
 *
 *  一是内存占用问题，毕竟每次执行写操作都要将原容器拷贝一份，数据量大时，
 *  对内存压力较大，可能会引起频繁GC；二是无法保证实时性，Vector对于读写
 *  操作均加锁同步，可以保证读和写的强一致性。而CopyOnWriteArrayList由于
 *  其实现策略的原因，写和读分别作用在新老不同容器上，在写操作执行过程中，
 *  读不会阻塞但读取到的却是老容器的数据。三是大量写操作性能极差。
 *
 *  很难去开发一个通用并且没有并发瓶颈的线程安全的List。
 *
 * 像ConcurrentHashMap这样的类的真正价值（The real point / value of classes）
 * 并不是它们保证了线程安全。而在于它们在保证线程安全的同时不存在并发瓶颈。
 *
 * 弱一致性
 */
public class CopyOnWriteGuide {
    public static void main(String[] args) {

    }
}
