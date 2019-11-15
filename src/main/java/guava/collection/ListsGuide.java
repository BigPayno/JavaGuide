package guava.collection;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author payno
 * @date 2019/10/29 10:13
 * @description
 */
public class ListsGuide {
    public static void main(String[] args) {
        List<String> characters= Splitter.fixedLength(1).splitToList("hello,payno");
        //分割zxf
        Lists.partition(characters,4).forEach(list->{
            list.forEach(System.out::printf);
            System.out.println();
        });

        Lists.charactersOf("payno").forEach(System.out::println);

    }
}
