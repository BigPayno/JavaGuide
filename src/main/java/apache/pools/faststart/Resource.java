package apache.pools.faststart;

import lombok.Data;

/**
 * @author payno
 * @date 2019/10/11 09:46
 * @description
 */
@Data
public class Resource {
    private static int id;
    private int rid;
    public Resource(){
        synchronized (this){
            this.rid=id++;
        }
    }
}
