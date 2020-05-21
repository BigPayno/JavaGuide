package pattern.strategy;

/**
 * @author payno
 * @date 2020/5/21 16:07
 * @description
 *      关于SpringSecurity中SecurityContextHolder的策略模式的使用
 */
public class Manager<T> implements IStore<T> {
    IStoreStrategy<T> IStoreStrategy;

    @Override
    public T post(T t) {
        return IStoreStrategy.getStore().post(t);
    }

    @Override
    public T get(String id) {
        return IStoreStrategy.getStore().get(id);
    }

    @Override
    public void delete(String id) {
        IStoreStrategy.getStore().delete(id);
    }
}
