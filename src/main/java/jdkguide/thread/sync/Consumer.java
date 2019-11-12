package jdkguide.thread.sync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author payno
 * @date 2019/11/4 15:46
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consumer implements Runnable{
    private String name,address;
    private Provider provider;

    @Override
    public void run() {
        System.out.printf("I am [%s],My address is [%s]%n",name,address);
        while(true){
            provider.pass(name,address);
        }
    }
}
