package lombok.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author payno
 * @date 2020/4/28 20:44
 * @description
 */
@Data
@Accessors(prefix = "user",fluent = true)
public class FluentAccessor {
    private Long userId;
    private String userName;
}
