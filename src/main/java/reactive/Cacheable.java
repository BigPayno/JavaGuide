package reactive;

/**
 * @author payno
 * @date 2020/5/26 14:43
 * @description
 */
public class Cacheable {
    /**
     *  Cacheable（可缓存）
     *      可缓存和可复用之间的区别是什么？假如我们有 pipeline A，重复使用它两次，
     *      来创建两个新的 pipeline B = A + X 以及 C = A + Y
     *      如果 B 和 C 都能成功执行，那么这个 A 就是是可重用的。
     *      如果 B 和 C 都能成功执行，并且 A 在这个过程中，整个 pipeline 只执行了一次，
     *      那么我们便称 A 是可缓存的。这意味着，可缓存一定代表可重用。
     *
     * CompletableFuture - 跟可重用的答案一样。
     *
     * Stream - 不能缓存中间操作的结果，除非调用了终止操作。
     *
     * Optional - 可缓存，所有操作立刻执行，并且进行了缓存。
     *
     * Observable, Flowable, Flux - 默认不可缓存的，但是可以调用 .cache() 把这些类变成可缓存的。
     */
}
