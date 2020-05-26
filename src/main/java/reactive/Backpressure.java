package reactive;

/**
 * @author payno
 * @date 2020/5/26 14:45
 * @description
 */
public class Backpressure {
    /**
     *  Stream & Optional - 不支持回压，因为它们是拉模型。
     *
     * CompletableFuture - 不存在这个问题，因为它只产生 0 个或者 1 个结果。
     *
     * Observable(RxJava 1), Flowable, Flux - 支持。常用策略如下：
     *     Buffering - 缓冲所有的 onNext 的值，直到下游消费它们。
     *     Drop Recent - 如果下游处理速率跟不上，丢弃最近的 onNext 值。
     *     Use Latest - 如果下游处理速率跟不上，只提供最近的 onNext 值，之前的值会被覆盖。
     *     None - onNext 事件直接被触发，不做缓冲和丢弃。
     *     Exception - 如果下游处理跟不上的话，抛出异常。
     *
     * Observable(RxJava 2) - 不支持。很多 RxJava 1 的使用者用 Observable 来处理不适用回压的事件，
     *      或者是使用 Observable 的时候没有配置任何策略，导致了不可预知的异常。
     *      所以，RxJava 2 明确地区分两种情况，提供支持回压的 Flowable 和不支持回压的 Observable。
     */
}
