package guava.string;

import com.google.common.base.Strings;

/**
 * @author payno
 * @date 2019/8/29 21:04
 * @description
 */
public class StringsGuide {
    public static void main(String[] args) {
        Strings.isNullOrEmpty("");
        //返回true
        Strings.nullToEmpty(null);
        //""
        Strings.nullToEmpty("chen");
        //返回"chen"
        Strings.emptyToNull("");
        //返回null
        Strings.emptyToNull("chen");
        //返回"chen"

        Strings.commonPrefix("aaab", "aac");
        //"aa"否则返回""
        Strings.commonSuffix("aaac", "aac");
        //"aac"否则返回""
    }
}
