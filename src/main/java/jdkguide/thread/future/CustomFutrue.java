package jdkguide.thread.future;


/**
 * @author payno
 * @date 2019/11/8 10:50
 * @description
 *  通过异步方法执行产生T，并在get方法处阻塞，直到拿到结果
 */
public class CustomFutrue<V> implements Future<V> {
    private V v;
    /**
     * ready暴露的操作，读属于Immutable的，修改在同步方法中
     */
    private boolean ready=false;
    @Override
    public boolean isReady() {
        return ready;
    }

    @Override
    public synchronized V get(){
        while(!ready){
            try{
                wait();
            }catch (InterruptedException i){
            }
        }
        return v;
    }

    @Override
    public synchronized void setV(V v) {
        if(!ready){
            this.v=v;
            this.ready=true;
        }
    }
}
