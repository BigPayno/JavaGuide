package effectivejava.object;

import java.util.function.Supplier;

/**
 * @author payno
 * @date 2019/8/17 19:11
 * @description R5:优先使用依赖注入来引入资源
 * 1.在构造器中传入资源，而不是StaticUtils或者Singleton
 * 2.使用Supplier来作为工厂方法
 */
public class R5DependencyInjection {
    public class Pen{
        public Pen(){ }
    }
    public class Writer{
        private Pen pen;
        public Writer(Pen pen){ }
        public Writer writer(Supplier<? extends Pen> penProvider){
            return new Writer(penProvider.get());
        }
    }
}
