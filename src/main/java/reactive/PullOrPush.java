package reactive;

/**
 * @author payno
 * @date 2020/5/26 14:44
 * @description
 */
public class PullOrPush {
    /**
     *  Push or Pull（推拉模型）
     *
     *  Stream 和 Optional - 拉模型。
     *      调用不同的方法（.get(), .collect() 等）从 pipeline 拉取结果。
     *      拉模型通常和阻塞、同步关联，那也是公平的。当调用方法时，线程会一直阻塞，直到有数据到达。
     *
     *  CompletableFuture, Observable, Flowable, Flux - 推模型。
     *      当订阅一个 pipeline ，并且某些事件被执行后，你会得到通知。推模型通常和非阻塞、异步这些词关联在一起。
     *      当 pipeline 在某个线程上执行时，你可以做任何事情。
     *      你已经定义了一段待执行的代码，当通知到达的时候，这段代码就会在下个阶段被执行。
     */
}
