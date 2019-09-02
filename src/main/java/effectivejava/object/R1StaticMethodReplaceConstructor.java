package effectivejava.object;

import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

import static java.util.Collections.emptyList;

/**
 * @author payno
 * @date 2019/8/17 17:51
 * @description R1:用静态工厂方法代替构造器
 * 优点：
 * 1.对象拥有名称
 * 2.不必在每次调用的时候都去创建新的对象，特别适用于不可变类
 * 3.可以返回类型的子类型
 * 比如在JDK1.8之前，每个集合类都拥有独自的实现，如ArrayList、WeakHashMap，
 * 而在1.8之后，提供了工厂方法,提供空集合、类型检查集合、同步集合、不可变集合等（代理模式）
 * 4.可以随参数值的不同，调用结果不同
 * 比如EnumSet会根据长度的不同，返回不同实现的Set
 * 5.静态工厂方法返回的类可以在该方法编写时不存在，服务站提供框架就是这样的思路
 * 缺点：
 * 1.不易子类化，复合多于继承
 * 2.程序员很难根据约定的方式发现这些方法
 */
public class R1StaticMethodReplaceConstructor {
    private static final Boolean TRUE=new Boolean(true);
    private static final Boolean FALSE=new Boolean(false);
    public static Boolean valueOf(boolean b){
        return b=true?TRUE:FALSE;
    }
    private static void collections(){
        //空集合
        List<String> list=Collections.emptyList();
        //类型检查的集合
        Collections.checkedList(list,String.class);
        //同步集合
        Collections.synchronizedList(list);
        //不可变集合
        Collections.unmodifiableList(list);
        //转Map为Set
        Collections.newSetFromMap(new WeakHashMap<>());
    }
    public static void main(String[] args) {
    }
}
