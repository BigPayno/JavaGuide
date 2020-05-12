package pattern.callback;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author payno
 * @date 2020/5/12 10:29
 * @description
 */
@Data
@RequiredArgsConstructor
public class Student {
    @NonNull
    String name;
    int score;
}
