package reactive;

/**
 * @author payno
 * @date 2020/5/26 14:48
 * @description
 */
public class OperationsFusion {
    /**
     * Operator fusion（操作融合）
     *
     * 操作融合的内涵在于，它使得生命周期的不同点上的执行阶段得以改变，从而消除类库的架构因素所造成的系统开销。所有这些优化都在内部被处理完毕，从而让外部用户觉得这一切都是透明的。
     *
     * 只有 RxJava 2 和 Reactor 支持这个特性，但支持的方式不同。总的来说，有两种类型的优化：
     *
     * Macro-fusion - 用一个操作替换 2 个或更多的相继的操作
     *
     * macro-fusion_.pngmacro-fusion_.png
     *
     * Micro-fusion - 一个输出队列的结束操作，和在一个输入队列的开始操作，能够共享一个队列的实例。比如说，与其调用 request(1) 然后处理 onNext()`：
     *
     * micro-fusion-1_1.pngmicro-fusion-1_1.png
     *
     * 不然让订阅者直接从父 observable 拉取值。
     *
     * micro-fusion-2.pngmicro-fusion-2.png
     */
}
