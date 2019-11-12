package pattern.callBack;

import lombok.AllArgsConstructor;

/**
 * @author payno
 * @date 2019/10/21 17:18
 * @description
 */
public class Executor<T> {
    T t;
    public Executor(T t){
        this.t=t;
    }
    public Executor doIt(CallBack<T> callBack){
        System.out.println("init");
        callBack.doIt(t);
        System.out.println("finish");
        return this;
    }
}
