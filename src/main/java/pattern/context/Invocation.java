package pattern.context;

/**
 * @author payno
 * @date 2020/5/22 09:47
 * @description
 *      和Context类似，参见SpringSecurity FilterInvocation
 *          只是把Context的操作逻辑抽离出来了
 */
public interface Invocation<C extends Context<?>> {
    void init(String id);
    void config(String name);
}
