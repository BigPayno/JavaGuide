package jdkguide.thread.happens;

/**
 * @author payno
 * @date 2019/12/12 10:41
 * @description
 *     在多线程中创建final属性Immutable对象对于其他线程能够保证
 *     不要让其他线程在其他地方能够看见一个构造期间的对象引用。
 */
public class FinalVisibleRuleGuide {
    public class BadExample{
        final int x;
        int y;
        BadExample bad;
        public BadExample(){
            x=1;
            y=2;
            /**
             * 这里是不合法的，如果其他线程在构造期间拿到这个对象的引用
             * 不保证x的值为1
             */
            bad=this;
        }
    }
}
