package jdkguide.thread.atomic;

import lombok.AllArgsConstructor;

import java.util.function.Consumer;

/**
 * @author payno
 * @date 2019/11/5 09:06
 * @description
 */
@AllArgsConstructor
public class Consumer0 extends Thread{
    private Provider provider;
    private Consumer<Provider> action;
    @Override
    public void run() {
        action.accept(provider);
    }
}
