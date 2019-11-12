package guava.collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

/**
 * @author payno
 * @date 2019/10/29 10:10
 * @description
 */
public class IterablesGuide {
    public static void main(String[] args) {
        Iterables.getLast(ImmutableList.copyOf(new String[]{"a","b"}));
    }
}
