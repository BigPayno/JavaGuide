package jdkguide.lang;

import org.junit.Test;

/**
 * @author payno
 * @date 2019/12/6 22:16
 * @description
 *    ""->pool
 *    ""+""+->pool
 *    ""+引用->heap
 *    new->heap
 */
public class StringGuide {
    @Test
    public void guide(){
        String a="a";
        String b="a"+"b";
        String c=a+"b";
        System.out.println(b==c);
    }
}
