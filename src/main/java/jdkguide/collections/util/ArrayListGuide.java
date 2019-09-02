package jdkguide.collections.util;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author payno
 * @date 2019/8/12 20:30
 * @description  Object[]
 */
public class ArrayListGuide {
    private static final int LENGTH=Integer.MAX_VALUE/1024;
    /**
     * 得到一个比较大的ArrayList
     */
    private static ArrayList<Integer> getArrayList(){
        ArrayList<Integer> arrayList=new ArrayList<>(LENGTH);
        for(int i = 0; i< LENGTH; i++){
            arrayList.add(Integer.valueOf(i));
        }
        return arrayList;
    }

    /**
     * RandomAccess接口是一个标志性接口，判断是否具有快速访问功能
     * 即for i循环是否比for iterator循环快
     * ArrayList具有快速访问，而LinkedList不具有
     */
    private static void randomAccess(){
        ArrayList<Integer> arrayList=getArrayList();
        Stopwatch stopwatch=Stopwatch.createStarted();
        for(int i=0;i<LENGTH;i++){
            arrayList.get(i);
        }
        System.out.println("for(int i;i<length;i++):"+stopwatch.stop());
        Stopwatch stopwatch2=Stopwatch.createStarted();
        for(Iterator<Integer> iterator=arrayList.iterator();iterator.hasNext();){
            iterator.next();
        }
        System.out.println("for(iterator;iterator.hasNext):"+stopwatch2.stop());
    }

    static class PArrayList<E> extends ArrayList<E>{
        public int getModCount(){
            return modCount;
        }
        PArrayList(int initialCapacity){
            super(initialCapacity);
        }
    }

    private static PArrayList<Integer> getPArrayList(){
        PArrayList<Integer> arrayList=new PArrayList<>(8);
        return arrayList;
    }

    /**
     * 测试在添加删除时modCount的变化；增删时各加1
     */
    private static void modCountTest1(){
        PArrayList<Integer> arrayList=getPArrayList();
        System.out.println("before add :"+arrayList.getModCount());
        arrayList.add(Integer.valueOf(1));
        System.out.println("add :"+arrayList.getModCount());
        System.out.println("before remove :"+arrayList.getModCount());
        arrayList.remove(Integer.valueOf(1));
        System.out.println("remove :"+arrayList.getModCount());
    }

    /**
     * 在forEach或者增强for循环中不能修改集合，否则在遍历结束时会抛出
     * ConcurrentModificationException
     */
    private static void modCountTest2(){
        PArrayList<Integer> arrayList=getPArrayList();
        for (int i = 0; i < 16; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        System.out.println("init mod:"+arrayList.getModCount());
        for(Iterator<Integer> iterator=arrayList.iterator();iterator.hasNext();){
            if(iterator.next().intValue()==3){
                iterator.remove();
                System.out.println("iterator remove:"+arrayList.getModCount());
            }
        }
        System.out.println("init mod:"+arrayList.getModCount());
        arrayList.forEach(v->{
            arrayList.removeIf(var->{return var.intValue()==2;});
            System.out.println("for each :"+arrayList.getModCount());
        });
    }

    private static void asList(){
        int[] ints={1,2,3};
        List integerList= Arrays.asList(ints);
        System.out.println(integerList.get(0));
        System.out.println("Array.asList不能处理基本类型，其保存的是引用类型地址！");
        String[] strs={"a","b","c"};
        List<String> stringList= Arrays.asList(strs);
        try{
            stringList.add("d");
        }catch (UnsupportedOperationException e){
            System.out.println("Array.asList并不是返回ArrayList，而是一个数组的简单封装，是个内部类，并且没有实现很多方法！");
        }
        /**
         * 利用jdk方式
         */
        List<String> jdkList=new ArrayList<>(Arrays.asList(strs));
        jdkList.add("d");
        /**
         * 利用流
         */
        List<String> stringStreamList=Arrays.stream(strs).collect(Collectors.toList());
        List<Integer> integerStreamList=Arrays.stream(ints).boxed().collect(Collectors.toList());
        /**
         * 利用Guava不可变集合和Lists的工厂方法
         */
        List<String> guavaList= ImmutableList.of("a","b");
        List<String> guavaList2=ImmutableList.copyOf(strs);
        List<String> guavaList3= Lists.newArrayList(strs);
    }

    private static void toArray(){
        String[] strs={"a","b","c"};
        List<String> list=Arrays.asList(strs);
        Collections.reverse(list);
        /**
         * 不设置new String[0]，返回的是Object[],设置0是为了作为模板
         */
        //String[] strs2=list.toArray(new String[0]);
        String[] str2=new String[3];
        list.toArray(str2);
    }

    public static void main(String[] args) {
        asList();
        toArray();
    }
}
