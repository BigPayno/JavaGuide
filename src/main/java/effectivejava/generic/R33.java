package effectivejava.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author payno
 * @date 2019/8/28 21:45
 * @description 类型安全的异构容器
 */
public class R33 {
    public class Collection{
        //其原生类型为Map<Class,Object>
        private Map<Class<?>,Object> collection=new HashMap<>();
        public <T> void put(Class<T> type,T instance){
            collection.put(Objects.requireNonNull(type),instance);
        }
        public <T> T get(Class<T> type){
            return type.cast(collection.get(type));
        }
    }
}
