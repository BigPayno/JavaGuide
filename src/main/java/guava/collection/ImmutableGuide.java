package guava.collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author payno
 * @date 2019/9/2 16:56
 * @description
 */
public class ImmutableGuide {
    public static void main(String[] args) {
        Collection<String> collection=new ArrayList<>();
        ImmutableList.copyOf(collection);
        ImmutableSet.copyOf(collection);
    }
}
