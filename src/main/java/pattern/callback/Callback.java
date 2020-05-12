package pattern.callback;


import java.util.List;
import java.util.function.Consumer;

/**
 * @author payno
 * @date 2020/5/12 10:32
 * @description
 *      无论是Consumer还是Callback
 *      Caller可以获得处理的逻辑，但无法得到具体的处理对象
 *      和Consumer相比，Caller可以拿到Callback返回的结果
 */
public interface Callback {
    void doIt(Student student);
    List<Vo<Student>> callback();
}
