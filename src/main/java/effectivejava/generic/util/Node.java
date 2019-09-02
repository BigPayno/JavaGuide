package effectivejava.generic.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author payno
 * @date 2019/8/28 21:51
 * @description
 */
@Data
@AllArgsConstructor
public class Node {
    private Class<?> type;
    private Object instance;
    public <T> T get(Class<T> type){
        return type.cast(instance);
    }
    public static Node of(Class<?> type,Object instance){
        return new Node(type,instance);
    }
}
