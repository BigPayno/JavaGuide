package guava.string;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.regex.Pattern;

/**
 * @author payno
 * @date 2019/8/13 19:33
 * @description
 */
public class SplitterGuide {
    private static final Pattern CHAR_PATTERN=Pattern.compile("\\w*");
    /**
     * 拆分器的静态工厂方法，参数支持char、String、Pattern、CharMatcher
     */
    private static void splitterStaticFactory(){
        Splitter.on(";");
        Splitter.on("yyyyMMdd");
        Splitter.on(CHAR_PATTERN);
        Splitter.onPattern("\\w*");
        Splitter.on(new CharMatcher() {
            @Override
            public boolean matches(char c) {
                return false;
            }
        });
        Splitter.fixedLength(5);
        Splitter.on(CharMatcher.javaIsoControl().negate());
    }
    /**
     * 修饰器:去除结果中的Null字符串
     */
    private static void omitEmptyStrings(){
        System.out.println("omitEmptyStrings : 去除结果中的空字符串");
        Splitter.on(";").omitEmptyStrings().splitToList("a;b;; ;d").stream().forEach(System.out::println);
    }
    /**
     * 也可以以CharMatcher作为参数
     */
    private static void trimResults(){
        System.out.println("trimResults : 修剪结果中的Blank");
        Splitter.on(";").trimResults().omitEmptyStrings().splitToList("a;b; ;d").stream().forEach(System.out::println);
    }

    /**
     * 注意最后一个的结果
     */
    private static void limit(){
        System.out.println("limit : 限制结果个数");
        Splitter.fixedLength(2).limit(3).splitToList("abcdefghi").forEach(System.out::println);
    }
    public static void main(String[] args) {
        omitEmptyStrings();
        trimResults();
        limit();
    }
}
