package jdkguide.thread.future;

/**
 * @author payno
 * @date 2019/11/8 10:52
 * @description
 */
public interface Future<V> {
    boolean isReady();
    V get();
    void setV(V v);
}
