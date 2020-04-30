package lombok.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author payno
 * @date 2020/4/28 20:46
 * @description
 */
@Data
@Accessors(prefix = "user",chain = true)
public class ChainAccessor {
    private Long userId;
    private String userName;
}
