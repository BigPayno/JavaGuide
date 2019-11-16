package jdkguide.print;

/**
 * @author payno
 * @date 2019/10/28 16:39
 * @description
 */
public class PrintfGuide {
    public static void main(String[] args) {
        System.out.printf("Line %d:%s",1,"payno");
        System.out.println(String.format("Line %d:%s",1,"payno"));
    }
}