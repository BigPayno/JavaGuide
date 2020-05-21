package pattern.strategy;

/**
 * @author payno
 * @date 2020/5/21 16:05
 * @description
 */
public interface IStore<T> {
    T get(String id);
    T post(T t);
    void delete(String id);
}
