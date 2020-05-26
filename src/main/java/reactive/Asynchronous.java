package reactive;

/**
 * @author payno
 * @date 2020/5/26 14:41
 * @description
 */
public class Asynchronous {
    /**
     *  4. Asynchronous（异步）
     *
     * CompletableFuture - 这个类的要点在于它异步地把多个操作连接了起来。
     *      CompletableFuture 代表一项操作，它会跟一个 Executor 关联起来。如果不明确指定一个 Executor，
     *      那么会默认使用公共的 ForkJoinPool 线程池来执行。这个线程池可以用 ForkJoinPool.commonPool()
     *      获取到。默认设置下它会创建系统硬件支持的线程数一样多的线程（通常和 CPU 的核心数相等，如果你
     *      的 CPU 支持超线程 (hyperthreading)，那么会设置成两倍的线程数）。不过你也可以使用 JVM 参数指
     *      定 ForkJoinPool 线程池的线程数，-Djava.util.concurrent.ForkJoinPool.common.parallelism=?
     *      或者在创建 CompletableFuture 时提供一个指定的 Executor。
     *
     * Stream - 不支持创建异步执行流程，但是可以使用 stream.parallel() 等方式创建并行流。
     *
     * Optional - 不支持，它只是一个容器。
     *
     * Observable, Flowable, Flux - 专门设计用以构建异步系统，但默认情况下是同步的。subscribeOn 和 observeOn 允许你来控制订阅以及接收（这个线程会调用 observer 的 onNext / onError / onCompleted 方法）。
     *      subscribeOn 方法使得你可以决定由哪个 Scheduler 来执行 Observable.create 方法。即便你没有调用创建方法，系统内部也会做同样的事情。
     */
}
