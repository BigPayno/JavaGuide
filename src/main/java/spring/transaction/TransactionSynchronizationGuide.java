package spring.transaction;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author payno
 * @date 2019/11/19 08:49
 * @description
 */
public class TransactionSynchronizationGuide {
    public static class AfterCommit extends TransactionSynchronizationAdapter{
        private Runnable runnable;
        public AfterCommit(Runnable runnable){
            this.runnable=runnable;
        }
        @Override
        public void afterCommit() {
            runnable.run();
            System.out.println("事务已经提交！");
        }
    }

    public static void main(String[] args) {
        TransactionSynchronizationManager.registerSynchronization(new AfterCommit(()->{
            System.out.println("DoSomeThing!!!");
        }));
    }
}
