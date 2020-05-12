package pattern.callback;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author payno
 * @date 2020/5/12 10:54
 * @description
 */
@ToString
@AllArgsConstructor
public class StudentVo implements Vo<Student>{
    int score;
    public static Vo<Student> of(Student student) {
        return new StudentVo(student.score);
    }
}
