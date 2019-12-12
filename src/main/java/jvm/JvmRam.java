package jvm;

/**
 * @author payno
 * @date 2019/12/12 09:48
 * @description
 */
public class JvmRam {
    /**
     * Jvm内存结构
     * [---------------------------------------------------------------------------]
     * \ (JVM Heap)\(JVM Stack) \(Method Area)\(Pc Register)\(Native Method Stack) \
     * \-----------\------------\-------------\-------------\----------------------\
     * \Object     \ThreadStack \Method       \             \  只是概念，没有强行  \
     * \           \[Ref]       \Class        \             \  规定，Jvm自己实现   \
     * \           \[Basic type]\Static       \             \                      \
     * [---------------------------------------------------------------------------]
     *Jvm内存模型
     * [-------------]
     * \ Thread      \       线程内存
     * \-------------\<>----[-------]
     * \Ref          \      \Obj副本\---------->Jvm Heap Obj
     * \             \      [-------]
     * [-------------]
     *Jvm对象模型
     * Stack-->Heap-->Method Area
     */
}
