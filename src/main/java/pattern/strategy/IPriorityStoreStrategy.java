package pattern.strategy;

/**
 * @author payno
 * @date 2020/5/21 16:15
 * @description
 */
public interface IPriorityStoreStrategy<T> extends IStoreStrategy<T>{
    int getOrder();
}
