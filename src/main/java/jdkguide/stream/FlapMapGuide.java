package jdkguide.stream;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author payno
 * @date 2019/9/20 09:06
 * @description
 */
public class FlapMapGuide {
    private static final List<String> WORDS= ImmutableList.copyOf(new String[]{"hello","world"});
    @Test
    public void map(){
        WORDS.stream()
                .map(word->word.split(""))
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        // h e l l o,w o r l d
    }

    @Test
    public void map2(){
        List<List<String>> chars=WORDS.stream()
                .map(word-> Splitter.fixedLength(1).splitToList(word))
                .distinct()
                .collect(Collectors.toList());
        System.out.println();
    }

    @Test
    public void flapMap(){
        /*
        *     List<String>->List<List<String>>->List<String>
        *
        * */
        WORDS.stream().
                map(word->Splitter.fixedLength(1).splitToList(word)).
                flatMap(chars->chars.stream()).
                distinct().
                collect(Collectors.toList())
                .forEach(System.out::println);

    }

    @Test
    public void flapMap2(){
        WORDS.stream()
                .flatMap(word->Splitter.fixedLength(1).splitToList(word).stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Test
    public void flapMap3(){
        WORDS.stream()
                .flatMap(word->Splitter.fixedLength(1).splitToList(word).stream())
                .distinct()
                .skip(3)
                .limit(2)
                .forEach(System.out::println);
    }
}
