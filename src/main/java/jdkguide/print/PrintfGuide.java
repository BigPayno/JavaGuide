package jdkguide.print;

/**
 * @author payno
 * @date 2019/10/28 16:39
 * @description
 */
public class PrintfGuide {
    static {
        System.out.println("天不生Java，万古如常夜");
    }
    public static void main(String[] args) {
        System.out.printf("Line %d:%s",1,"payno");
        System.out.println(String.format("Line %d:%s",1,"payno"));
    }

    public void say(){
        System.out.println("say");
    }
}