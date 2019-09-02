package jdkguide.collections.util;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author payno
 * @date 2019/8/12 20:56
 * @description
 */
public class RandomAccessGuide {
    public static <T> void randomAccess(List<T> list, Consumer<? super T> action){
        Objects.requireNonNull(list);
        Objects.requireNonNull(action);
        if(list instanceof RandomAccess){
            for(int i=0;i<list.size();i++){
                action.accept(list.get(i));
            }
        }else{
            for(ListIterator<T> iterator = list.listIterator(); iterator.hasNext();){
                action.accept(iterator.next());
            }
        }
    }
}
