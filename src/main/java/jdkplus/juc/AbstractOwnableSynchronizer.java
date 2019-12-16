package jdkplus.juc;

/**
 * @author payno
 * @date 2019/12/13 14:52
 * @description
 *      一种同步器，可能只属于一个线程。该类为创建可能涉及所有权概念的锁和相关同步器提供了
 * 基础。AbstractOwnableSynchronizer类本身不管理或使用此信息。但是，子类和工具可以使用适当
 * 维护的值来帮助控制和监视访问并提供诊断。
 *
 */
public abstract class AbstractOwnableSynchronizer
        implements java.io.Serializable {

    private static final long serialVersionUID = 3737899427754241961L;

    protected AbstractOwnableSynchronizer() { }

    private transient Thread exclusiveOwnerThread;

    protected final void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }
}
