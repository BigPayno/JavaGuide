package guava.string;

import com.google.common.base.CaseFormat;

/**
 * @author payno
 * @date 2019/8/13 20:22
 * @description
 */
public class CaseFormatGuide {
    public static void main(String[] args) {
        /**
         * lowerCamel
         * UpperCamel
         * lower_underscore
         * UPPER_UNDERSCORE
         * lower-hypthen
         */
        String str=CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL).convert("userName");
        System.out.println(str);
        String str2=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN,"USER_NAME");
        System.out.println(str2);
    }
}
