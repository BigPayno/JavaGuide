package jdkguide.stream;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author payno
 * @date 2019/9/20 10:11
 * @description
 */
public class CollectorGuide {
    private static class Point{
        public Integer x;
        public Integer y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
        public Integer getX(){
            return x;
        }
    }
    private static Point of(int x,int y){
        return new Point(x,y);
    }
    private static final List<Point> points=ImmutableList.copyOf(new Point[]{of(1,2),of(1,3),of(2,3)});

    public void groupBy(){
        Map<Integer,List<Point>> map=points.stream()
                .collect(Collectors.groupingBy(Point::getX,Collectors.toList()));
    }

    public void collectingAndThen(){
        Map<Integer,Point> map=points.stream()
                .collect(Collectors.groupingBy(Point::getX,Collectors.collectingAndThen(Collectors.toList(),list->list.get(0))));
    }
}
