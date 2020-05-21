package pattern.strategy;

/**
 * @author payno
 * @date 2020/5/21 16:25
 * @description
 */
public interface IPriorityStore<T> extends IStore<T>{
    int getOrder();
}
