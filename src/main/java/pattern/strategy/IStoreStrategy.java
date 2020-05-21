package pattern.strategy;

/**
 * @author payno
 * @date 2020/5/21 16:07
 * @description
 */
public interface IStoreStrategy<T> {
    String name();
    IStore<T> getStore();
}
