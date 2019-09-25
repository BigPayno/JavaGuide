package utils.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author payno
 * @date 2019/7/16 17:38
 * @description
 */
public class CollectionFilters {
    /**
     * @param keyExecutor    map的key
     * @param collectionSize
     * @author: payno
     * @time: 2019/7/10 11:43
     * @description: 利用过滤器通过某个key去重
     * @return: java.util.function.Predicate<T>
     */
    public static <T, R> Predicate<T> distinctKeyFilter(Function<T, R> keyExecutor, int collectionSize) {
        Map<R, Boolean> keyIsExistMap = new ConcurrentHashMap<>(collectionSize);
        return v -> keyIsExistMap.putIfAbsent(keyExecutor.apply(v), Boolean.TRUE) == null;
    }
}
