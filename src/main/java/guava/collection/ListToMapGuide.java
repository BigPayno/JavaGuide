package guava.collection;


import com.google.common.collect.*;

import java.util.List;


/**
 * @author payno
 * @date 2019/11/13 10:13
 * @description
 */
public class ListToMapGuide {
    public static void main(String[] args) {
        List<Character> characterList= Lists.charactersOf("hello,payno,what are you doing?");
        //并不会去重
        Maps.uniqueIndex(characterList,Character::charValue).values().asList().forEach(System.out::print);
    }
}
