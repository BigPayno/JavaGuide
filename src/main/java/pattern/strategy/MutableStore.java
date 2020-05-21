package pattern.strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author payno
 * @date 2020/5/21 16:24
 * @description
 */
public class MutableStore<T> implements IStore<T>{

    List<IPriorityStore<T>> stores;

    /**
     *  be lazy to do sth!!!
     */
    public MutableStore(List<IPriorityStore<T>> stores) {
        super();
        this.stores=stores;
        Collections.sort(stores, Comparator.comparing(IPriorityStore::getOrder));
    }

    @Override
    public T get(String id) {
        for(IPriorityStore<T> store:stores){
            T t = store.get(id);
            if(t!=null){
                return t;
            }
        }
        return null;
    }

    @Override
    public T post(T t) {
        T t2 = null;
        for(IPriorityStore<T> store:stores){
            t2 = store.post(t);
        }
        return t2;
    }

    @Override
    public void delete(String id) {
        for(IPriorityStore<T> store:stores){
            store.delete(id);
        }
    }
}
