package jdkguide.thread.sync;

import lombok.Data;

/**
 * @author payno
 * @date 2019/11/4 15:41
 * @description
 */
@Data
public class Provider {
    private String name,address;
    private int counter;
    public synchronized void pass(String name,String address){
        this.name=name;this.address=address;
        counter++;
        check();
    }
    private void check(){
        if(name.charAt(0)!=address.charAt(0)){
            System.out.printf("Wrong!Name[%s]:Address[%s] %n",name,address);
        }
    }
}
