package guava.collection;


import com.google.common.collect.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author payno
 * @date 2019/11/13 10:13
 * @description
 */
public class ListToMapGuide {
    public static void main(String[] args) {
        List<Character> characterList= Lists.charactersOf("hello,payno,what are you doing?");
        //并不会去重
        //Maps.uniqueIndex(characterList,Character::charValue).values().asList().forEach(System.out::print);
        Lists.charactersOf("hello,payno,what are you doing?").stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(Character::charValue),
                        list->list.keySet().stream().collect(
                                Collectors.toMap(character->character,character->list.get(character).size()))));

        Lists.charactersOf("hello,payno,what are you doing?").stream()
                .collect(Collectors.groupingBy(Character::charValue,Collectors.counting()))
                .forEach((key,value)->{
                    System.out.printf("[%s]:[%d] %n",key.toString(),value.intValue());
                });

        Multimaps.index(Lists.charactersOf("hello,payno,what are you doing?"),Character::charValue);

        Multiset<Character> characters=HashMultiset.create(
                Lists.charactersOf("hello,payno,what are you doing?"));
        characters.elementSet().forEach(character -> {


            System.out.printf("[%s]:[%d] %n",character.toString(),characters.count(character));
        });
    }
}
