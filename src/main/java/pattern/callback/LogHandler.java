package pattern.callback;

import lombok.extern.slf4j.Slf4j;

/**
 * @author payno
 * @date 2020/5/12 10:50
 * @description
 */
@Slf4j
public class LogHandler implements Handler{
    @Override
    public void postHandle(Student student) {
        log.info("student[{}],score[{}]",student.name,student.score);
    }
}
