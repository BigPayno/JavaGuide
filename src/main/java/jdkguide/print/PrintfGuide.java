package jdkguide.print;

import com.google.common.base.Joiner;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author payno
 * @date 2019/10/28 16:39
 * @description
 *  加Component是为了测试Spring的ClassLoader
 *
 *  Important: println printf都是会进入同步代码块的
 *
 *  % - 0 m.n l或h 格式字符
 *
 * 1.%：表示格式说明的起始符号，不可缺少；
 *
 * 2.-：有-表示左对齐输出，如省略表示右对齐输出
 *
 * 3.0：有0表示指定空位填0，如省略表示指定空位不填；
 *
 * 4.m.n：m指域宽，即对应的输出项在输出设备上所占的字符数。N指精度。用于说明输出的实型数的6.小数位数。为指定n时，隐含的精度为n=6位
 *
 * 5.l或h：l对整型指long型，对实型指double型。h用于将整型的格式字符修正为short型。
 */
@Data
public class PrintfGuide {
    static {
        System.out.println("天不生Java，万古如常夜");
    }

    @Autowired
    public Joiner joiner;

    public static void main(String[] args) {
        System.out.printf("Line %d:%s",1,"payno");
        System.out.println(String.format("Line %d:%s",1,"payno"));
    }

    public void say(){
        System.out.println("say");
    }
    public Joiner get(){
        return joiner;
    }

    @Test
    public void longP(){
        System.out.printf("%20d",111111111111111111L);
    }
}