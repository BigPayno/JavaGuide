package jdkguide.thread.juc.cas;

/**
 * @author payno
 * @date 2019/11/15 11:16
 * @description
 *      线程持有变量副本，每次改变都会通过cas进行检测母本与副本之间是否发生变化
 *      如果发生变化就更新为最新的数据再进行操作
 *      但是如果是对象，比较地址或者hash
 *      地址或者hash没有发生变化，但是内容改变，就不知道去更新了
 *      Thread1:A  Local:A
 *                 Local:A->B->A
 *      Thread1:A
 */
public class Test {
    public static void main(String[] args) throws Exception{
        Stack stack = new StampedStack();
        stack.push(new Node("B"));
        stack.push(new Node("A"));

        Thread thread1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread() + " 拿到数据：" + stack.pop(3));
                // 再继续拿，就会有问题了，理想情况stack出数据应该是 A->C->D->B，实际上ABA问题导致A-B->null
                System.out.println(Thread.currentThread() + " 拿到数据：" + stack.pop(0));
                System.out.println(Thread.currentThread() + " 拿到数据：" + stack.pop(0));
                System.out.println(Thread.currentThread() + " 拿到数据：" + stack.pop(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        Thread.sleep(300);

        Thread thread2 = new Thread(() -> {
            Node A = null;
            try {
                A = stack.pop(0);
                System.out.println(Thread.currentThread() + " 拿到数据：" + A);
                stack.push(new Node("D"));
                stack.push(new Node("C"));
                stack.push(A);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread2.start();
    }
}
