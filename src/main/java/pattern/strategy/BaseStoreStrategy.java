package pattern.strategy;

import jdkguide.lang.T;
import lombok.Data;
import lombok.Setter;

/**
 * @author payno
 * @date 2020/5/21 16:12
 * @description
 */
@Setter
public class BaseStoreStrategy<T> implements IPriorityStoreStrategy<T>{
    int order;
    String name;
    IStore<T> store;

    @Override
    public String name() {
        return name;
    }

    @Override
    public IStore<T> getStore() {
        return store;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
