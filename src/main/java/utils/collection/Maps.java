package utils.collection;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author zhaolei
 * @Description list向map转换相关处理
 * @Date 2019/7/5 17:22
 */
public final class Maps {
    /**
     * @param keyExecutor 会进行distinct处理，优先保留靠前的key与value
     * @param list
     * @author: payno
     * @time: 2019/7/10 11:44
     * @description: 输入v的某个函数，与list得到map
     * @return: java.util.Map<K, V>
     */
    public static <K, V> Map<K, V> toMap(Function<V, K> keyExecutor, List<V> list) {
        Map<K, V> map = list.stream()
                .filter(CollectionFilters.distinctKeyFilter(keyExecutor, list.size()))
                .collect(Collectors.toMap(keyExecutor, v -> v));
        return map;
    }
}
