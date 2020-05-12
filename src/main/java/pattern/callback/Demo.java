package pattern.callback;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author payno
 * @date 2020/5/12 10:50
 * @description
 */
public class Demo {

    private static Teacher getTeacher(){
        Student stu1=new Student("chad");
        Student stu2=new Student("payno");
        Handler handler=new LogHandler();
        Teacher tea=new Teacher(Lists.newArrayList(stu1,stu2),handler);
        return tea;
    }

    public static void main(String[] args) {
        getTeacher().doIt(stu->stu.score=1);
        Callback callback= new Callback() {
            List<Vo<Student>> vos=new ArrayList<>();
            @Override
            public void doIt(Student student) {
                student.score=2;
                vos.add(StudentVo.of(student));
            }
            @Override
            public List<Vo<Student>> callback() {
                return vos;
            }
        };
        getTeacher().doItAndCallback(callback);
        callback.callback().forEach(System.err::println);
    }
}
