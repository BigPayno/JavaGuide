package pattern.strategy;

import jdkguide.lang.T;
import lombok.Data;

import java.util.List;

/**
 * @author payno
 * @date 2020/5/21 16:12
 * @description
 */
@Data
public class MutableStoreStrategy<T> extends BaseStoreStrategy<T> implements IStoreStrategy<T>{
    MutableStrategy strategy;
    List<IPriorityStoreStrategy<T>> strategies;

    @Override
    public IStore<T> getStore() {
        if(strategy==null){
            this.strategy = MutableStrategy.PRIORITY;
        }
        if(strategy==MutableStrategy.PRIORITY){
            return strategies.stream().sorted().findFirst().get().getStore();
        }
        if(strategy==MutableStrategy.INDEX){
            return strategies.get(0).getStore();
        }
        throw new IllegalStateException("never happened!");
    }

    public static enum MutableStrategy{
        INDEX,PRIORITY
    }
}
