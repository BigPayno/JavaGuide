package jdkguide.collections.concurrent;

import com.google.common.collect.Ordering;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author payno
 * @date 2019/11/15 14:57
 * @description
 *  1>PriorityQueue是一种无界的，线程不安全的队列
 *  2>PriorityQueue是一种通过数组实现的，并拥有优先级的队列
 *  3>PriorityQueue存储的元素要求必须是可比较的对象， 如果不是就必须明确指定比较器
 *  offer 插入按照优先级 poll 队首
 */
public class PriorityQueueGuide {
    @Data
    @AllArgsConstructor
    public static class Student{
        private String name;
        private Integer score;
    }
    public static void main(String[] args) {
        //基于Ordering的排序器 按成绩由大到小，按姓名正序
        Comparator<Student> studentComparator= Ordering.from(Comparator.comparing(Student::getScore).reversed())
               .thenComparing(Student::getName);
        PriorityQueue<Student> studentPriorityQueue=new PriorityQueue<>(studentComparator);
        studentPriorityQueue.offer(new Student("payne",35));
        studentPriorityQueue.offer(new Student("zedd",35));
        studentPriorityQueue.offer(new Student("payno",50));
        System.out.println(studentPriorityQueue.poll());
        System.out.println(studentPriorityQueue.poll());
        System.out.println(studentPriorityQueue.poll());
    }
}
