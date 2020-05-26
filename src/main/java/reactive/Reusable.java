package reactive;

/**
 * @author payno
 * @date 2020/5/26 14:40
 * @description
 */
public class Reusable {
    /**
     *  CompletableFuture - 可以复用，它仅仅是一个实际值的包装类。但需要注意的是，这个包装是可更改的。.obtrude*() 方法会修改它的内容，如果你确定没有人会调用到这类方法，那么重用它还是安全的。
     *
     * Stream - 不能复用。Java Doc 注释道：
     *
     *     A stream should be operated on (invoking an intermediate or terminal stream operation) only once. A stream implementation may throw IllegalStateException if it detects that the stream is being reused. However, since some stream operations may return their receiver rather than a new stream object, it may not be possible to detect reuse in all cases.
     *
     * Optional - 完全可重用，因为它是不可变对象，而且所有操作都是立刻执行的。
     *
     * Observable, Flowable, Flux - 生而重用，专门设计成如此。当存在订阅者时，每一次执行都会从初始点开始完整地执行一边。
     */
}
