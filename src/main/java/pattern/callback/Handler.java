package pattern.callback;

/**
 * @author payno
 * @date 2020/5/12 10:45
 * @description
 *      Handler/Hook 钩子
 *      和callback与consumer相比
 *      Caller是完全不知道做的具体操作逻辑和操作对象
 */
public interface Handler {
    void postHandle(Student student);
}
