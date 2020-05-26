package reactive;

/**
 * @author payno
 * @date 2020/5/26 14:39
 * @description
 */
public class Lazy {
    /**
     *  CompletableFuture - 不具备惰性执行的特性，它本质上只是一个异步结果的容器。这些对象的创建是用来表示对应的工作，CompletableFuture 创建时，对应的工作已经开始执行了。但它并不知道任何工作细节，只关心结果。所以，没有办法从上至下执行整个 pipeline。当结果被设置给 CompletableFuture 时，下一个阶段才开始执行。
     *
     *  Stream - 所有的中间操作都是延迟执行的。所有的终止操作 (terminal operations)，会触发真正的计算 (译者注：如 collect() 就是一个终止操作 )。
     *
     *  Optional - 不具备惰性执行的特性，所有的操作会立刻执行。
     *
     *  Observable, Flowable, Flux - 惰性执行，只有当订阅者出现时才会执行，否则不执行。
     */
}
