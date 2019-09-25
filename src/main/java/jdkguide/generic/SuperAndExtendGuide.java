package jdkguide.generic;

import com.google.common.collect.Lists;
import javafx.collections.ObservableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author payno
 * @date 2019/9/18 17:58
 * @description
 */
public class SuperAndExtendGuide {
    @Test
    public void test1(){
        Number[] numbers=new Integer[6];
        /*List<Number> list=new ArrayList<Integer>();*/
        List<? extends Number> outList= new ArrayList<Integer>(Arrays.asList(Integer.valueOf(0)));
        /*list.add(Integer.valueOf(1));*/
        System.out.println(outList.get(0));
        List<? super Integer> inList=new ArrayList<Integer>(Arrays.asList(Integer.valueOf(0)));
        inList.add(new Integer(1));
        Object integer=inList.get(0);
    }
}
