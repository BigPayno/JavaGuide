package guava.collection;

import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

import java.util.HashMap;
import java.util.List;

/**
 * @author payno
 * @date 2019/10/29 14:32
 * @description
 */
public class Demo {
    public static void main(String[] args) {
        String txt = "hello i am payno , who are you , payno ,payno am";
        Multiset<String> multiset = Multisets.copyHighestCountFirst(
                HashMultiset.create(Splitter.on(" ").split(txt)));
        multiset.elementSet().forEach(e -> System.out.println(e + ":" + multiset.count(e)));

        List<String> words = Splitter.on(" ").splitToList(txt);
        HashMap<String, Integer> countMap = new HashMap<>(words.size());
        for (int i = 0; i < words.size(); i++) {
            String key = words.get(i);
            if (countMap.keySet().contains(key)) {
                countMap.put(key, countMap.get(key) + 1);
            } else {
                countMap.put(key, Integer.valueOf(1));
            }
        }
        countMap.forEach((k, v) -> System.out.println(k + ">>>>" + v));
    }
}
