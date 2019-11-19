package jdkguide.print;

import com.google.common.base.Joiner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author payno
 * @date 2019/11/18 20:05
 * @description
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestConfig {
    public Joiner joiner;
}
