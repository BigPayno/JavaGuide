package guava.string;

import com.google.common.base.CharMatcher;

/**
 * @author payno
 * @date 2019/8/13 19:58
 * @description
 */
public class CharMattcherGuide {
    /**
     * 匹配器
     */
    private void matcher(){
        CharMatcher.javaIsoControl();
        CharMatcher.javaDigit();
        CharMatcher.javaLetter();
        CharMatcher.javaLetterOrDigit();
    }
    private static void function(){
        /**
         * 恒成立、存在、不存在
         */
        CharMatcher.javaDigit().matchesAllOf("12a");
        CharMatcher.javaDigit().matchesAnyOf("aaa");
        CharMatcher.javaDigit().matchesNoneOf("1ab");
        /**
         * 保留、移除、替换、去空白、匹配到的多个连续字符替换为单个
         */
        String str="abc123ab1  22 2\n";
        String str1=CharMatcher.javaLetter().retainFrom(str);
        String str2=CharMatcher.javaDigit().removeFrom(str);
        String str3=CharMatcher.javaLetterOrDigit().replaceFrom(str,"99");
        String str4=CharMatcher.javaLetterOrDigit().trimFrom(str);
        String str5=CharMatcher.javaLetter().collapseFrom(str,'N');
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
        System.out.println(str5);
    }

    public static void main(String[] args) {
        function();
    }
}
