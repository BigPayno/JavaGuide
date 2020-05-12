package pattern.callback;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author payno
 * @date 2020/5/12 10:29
 * @description
 */
@AllArgsConstructor
public class Teacher {
    List<Student> students;
    Handler postHandler;
    void doIt(Consumer<Student> consumer){
        if(students!=null){
            students.forEach(consumer::accept);
            students.forEach(postHandler::postHandle);
        }
    }

    void doItAndCallback(Callback callback){
        if(students!=null){
            students.forEach(callback::doIt);
            students.forEach(postHandler::postHandle);
        }
    }

    void doItWithStu(Student student){
        postHandler.postHandle(student);
    }
}
