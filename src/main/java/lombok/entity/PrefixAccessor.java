package lombok.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author payno
 * @date 2020/4/28 20:41
 * @description
 *      忽略指定前缀
 */
@Data
@Accessors(prefix = "user")
public class PrefixAccessor {
    private Long userId;
    private String userName;
}
