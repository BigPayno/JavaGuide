package guava.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author payno
 * @date 2019/10/29 10:34
 * @description
 */
public class MapsGuide {
    public static void main(String[] args) {
        //Iterable转Map,不会去重
        List<Character> characters= Lists.charactersOf("helo,payn!");
        Map<Integer,Character> map= Maps.uniqueIndex(characters,character->characters.indexOf(character));
        map.forEach((k,v)->{
            System.out.println(k+":"+v);
        });

        //MapDifference
        List<Character> chars1= Lists.charactersOf("abcdef");
        Map<Integer,Character> map1= Maps.uniqueIndex(chars1,character->chars1.indexOf(character));
        List<Character> chars2= Lists.charactersOf("abghef");
        Map<Integer,Character> map2= Maps.uniqueIndex(chars2,character->chars2.indexOf(character));
        MapDifference<Integer,Character> difference= Maps.difference(map1,map2);
        if(!difference.areEqual()){
            difference.entriesInCommon();
            difference.entriesOnlyOnLeft();
            difference.entriesOnlyOnRight();
            difference.entriesDiffering().forEach((k,v)->{
                System.out.println("difference in "+k);
                System.out.println(v.leftValue()+"&&"+v.rightValue());
            });
        }
    }
}
