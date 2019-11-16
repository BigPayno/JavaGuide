package jdkguide.thread.juc.locks;

import com.sun.istack.internal.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author payno
 * @date 2019/11/9 10:51
 * @description
 *  Condition.await
 *   Object.wait
 *  Condition.signal
 *   Object.notify
 */
public class ConditionGuide {
    public static class Foods{
        List<String> food;
        int limit;
        ReentrantLock lock;
        Condition foodIsNull;
        Condition foodIsFull;
        public Foods(int limit){
            food=new LinkedList<String>();
            lock=new ReentrantLock();
            foodIsNull=lock.newCondition();
            foodIsFull=lock.newCondition();
            this.limit=limit;
        }

        public boolean offer(String string){
            if(lock.tryLock()){
                try{
                    if(food.size()==limit){
                        foodIsFull.await();
                    }
                    food.add(string);
                    System.out.println(Thread.currentThread().getName()+" add food "+string);
                    foodIsNull.signal();
                    return true;
                }catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    return false;
                }
            }else {
                return false;
            }
        }

        public String poll(){
            if(lock.tryLock()){
                try{
                    if(food.size()==0){
                        foodIsNull.await();
                    }
                    String applyFood=food.remove(food.size()-1);
                    System.out.println(Thread.currentThread().getName()+" use food "+applyFood);
                    foodIsFull.signal();
                    return applyFood;
                }catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    return null;
                }
            }else {
                System.out.println(Thread.currentThread().getName()+"waiting!");
                return null;
            }
        }
    }
    public static class Consumer extends Thread{
        Foods foods;
        public Consumer(Foods foods){
            this.foods=foods;
        }
        @Override
        public void run() {
            while(true){
                foods.poll();
            }
        }
    }
    public static class Producer extends Thread{
        Foods foods;
        public Producer(Foods foods){
            this.foods=foods;
        }
        @Override
        public void run() {
            String food=null;
            while(true){
                food=UUID.randomUUID().toString();
                while(foods.offer(food)){
                    break;
                }
            }
        }
    }

    public static class Sence1{
        public static void main(String[] args) throws Exception{
            Foods foods=new Foods(5);
            Producer producer=new Producer(foods);
            Producer producer1=new Producer(foods);
            producer.start();
            producer1.start();
            Thread.sleep(1000);
            Consumer consumer=new Consumer(foods);
            consumer.start();
        }
    }
}
