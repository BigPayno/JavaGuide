package guava.collection;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.List;
import java.util.Map;

/**
 * @author payno
 * @date 2019/10/29 10:07
 * @description
 */
public class MutiSetMapGuide {
    public static void main(String[] args) {
        List<Character> characters= Lists.charactersOf("abcefabcakshahsk");
        //MultiSet
        Multiset<Character> multiSet= HashMultiset.create(characters);
        System.out.println();
        multiSet.forEach(v-> System.out.println(v+":"+multiSet.count(v)));
        System.out.println();
        multiSet.elementSet().forEach(v-> System.out.println(v+":"+multiSet.count(v)));
        System.out.println();
        Multisets.copyHighestCountFirst(multiSet).forEach(v-> System.out.println(v+":"+multiSet.count(v)));
        System.out.println();

        //MultiMaps.index
        List<String> words=ImmutableList.copyOf(new String[]{"a","b","cd","efg"});
        Multimap<Integer,String> wordMap=Multimaps.index(words,v->v.length());
        wordMap.forEach((k,v)->{
            System.out.println(k+":"+wordMap.get(k).stream().reduce((a,b)->a+b).toString());
        });
        System.out.println( );
        wordMap.keySet().forEach(k->{
            System.out.println(k+":"+wordMap.get(k));
        });
        System.out.println();

        //MultiMaps.invertFrom
        ArrayListMultimap<String,Integer> multimap=ArrayListMultimap.create();
        multimap.putAll("a", Ints.asList(1,2,3));
        multimap.putAll("b",Ints.asList(2,5,6));
        Multimap<Integer,String> multimap1=Multimaps.invertFrom(multimap,ArrayListMultimap.create());
        multimap1.keySet().forEach(k->{
            System.out.println(k+":"+multimap1.get(k));
        });
        System.out.println();

        //MultiMaps.forMap
        Map<String,Integer> map=ImmutableMap.of("a",1,"b",2,"c",1);
        Multimap<Integer,String> multimap2=Multimaps.invertFrom(Multimaps.forMap(map),TreeMultimap.create());
        multimap2.keySet().forEach(k->{
            System.out.println(k+":"+multimap2.get(k));
        });
    }
}
