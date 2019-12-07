package jdkguide.print;

import com.google.common.base.Joiner;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author payno
 * @date 2019/10/28 16:39
 * @description
 *  加Component是为了测试Spring的ClassLoader
 *
 *  Important: println printf都是会进入同步代码块的
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
}