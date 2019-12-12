package jvm.jmm;

/**
 * @author payno
 * @date 2019/12/12 10:13
 * @description
 * Happens before;
 *      1. 如果一个操作happens-before另一个操作，那么第一个操作的执行结果将对第二个操作可见，
 * 而且第一个操作的执行顺序排在第二个操作之前。
 *      2. 两个操作之间存在happens-before关系，并不意味着一定要按照happens-before原则制定的
 * 顺序来执行。如果重排序之后的执行结果与按照happens-before关系来执行的结果一致，那么这种重
 * 排序并不非法。
 */
public class HappensBefore {
    /**
     * 程序次序规则：
     *      一段代码在单线程中执行的结果是有序的。注意是执行结果，因为虚拟机、处理器会对指令
     * 进行重排序（重排序后面会详细介绍）。虽然重排序了，但是并不会影响程序的执行结果，所以程
     * 序最终执行的结果与顺序执行的结果是一致的。故而这个规则只对单线程有效，在多线程环境下
     * 无法保证正确性。
     *
     * 锁定规则：@原子性
     *      这个规则比较好理解，无论是在单线程环境还是多线程环境，一个锁处于被锁定状态，那么必
     * 须先执行unlock操作后面才能进行lock操作。(互斥性)
     *
     * volatile变量规则：@可见性+顺序性
     *      这是一条比较重要的规则，它标志着volatile保证了线程可见性。通俗点讲就是如果一个线程
     * 先去写一个volatile变量，然后一个线程去读这个变量，那么这个写操作一定是happens-before读操
     * 作的。【对volatile的变量的读写会同步主存】
     *
     * 传递规则：
     *      提现了happens-before原则具有传递性，即A happens-before B , B happens-before C，那么
     * A happens-before C
     *
     * 线程启动规则：@可见性+顺序性
     *      假定线程A在执行过程中，通过执行ThreadB.start()来启动线程B，那么线程A对共享变量的修改
     * 在接下来线程B开始执行后确保对线程B可见。【启动时刷新当前线程内容到主存】
     *
     * 线程终结规则：@可见性+顺序性
     *      假定线程A在执行的过程中，通过制定ThreadB.join()等待线程B终止，那么线程B在终止之前对
     * 共享变量的修改在线程A等待返回后可见。【线程join后，刷新内存到主存并更新当前线程工作内存】
     *
     * 推导:
     * 将一个元素放入一个线程安全的队列的操作Happens-Before从队列中取出这个元素的操作
     * 将一个元素放入一个线程安全容器的操作Happens-Before从容器中取出这个元素的操作
     * 在CountDownLatch上的倒数操作Happens-Before CountDownLatch#await()操作
     * 释放Semaphore许可的操作Happens-Before获得许可操作
     * Future表示的任务的所有操作Happens-Before Future#get()操作
     * 向Executor提交一个Runnable或Callable的操作Happens-Before任务开始执行操作
     *
     */
}
