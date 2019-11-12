package guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author payno
 * @date 2019/10/21 20:45
 * @description
 */
public class EventBusGuide {
    public static class User{
        public User(String name){
            this.name=name;
        }
        String name;
        @Subscribe
        public void onUpdatingEBook(String bookName){
            System.out.println("user ["+name+"] know the book ["+bookName+"] update");
        }
        @Subscribe
        public void wake(String bookName){
            System.out.println("wake up");
        }
    }

    public static void main(String[] args) {
        EventBus ebookUpdateEventBus=new EventBus();
        User payno=new User("payno");
        ebookUpdateEventBus.register(payno);
        ebookUpdateEventBus.post("Fight");
    }
}
