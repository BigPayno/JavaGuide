package lombok;

import lombok.entity.ChainAccessor;
import lombok.entity.FluentAccessor;
import lombok.entity.PrefixAccessor;
import org.junit.Test;

/**
 * @author payno
 * @date 2020/4/28 20:41
 * @description
 */
public class AccessorsGuide {
    void prefixAccessor(){
        PrefixAccessor accessor=new PrefixAccessor();
        accessor.setId(Long.valueOf(1));
        accessor.setName("payno");
    }

    void fluentAccessor(){
        FluentAccessor accessor=new FluentAccessor();
        System.out.println(accessor.id(Long.valueOf(1)).name("chad").id());
    }

    void chainAccessor(){
        ChainAccessor accessor=new ChainAccessor();
        System.out.println(accessor.setId(Long.valueOf(1)).setName("chad").getName());
    }
}
