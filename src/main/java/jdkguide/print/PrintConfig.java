package jdkguide.print;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author payno
 * @date 2019/11/18 16:56
 * @description
 */
@Configuration
public class PrintConfig {
    @Bean
    public PrintfGuide printfGuide(){
        return new PrintfGuide();
    }

    public static void main(String[] args) {
        //Import
    }
}
