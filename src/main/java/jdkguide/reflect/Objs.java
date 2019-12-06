package jdkguide.reflect;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.List;

/**
 * @author payno
 * @date 2019/12/6 15:27
 * @description
 */
public final class Objs {
    private static final Splitter INNER_CLASS_SPLITTER=Splitter.on('$');
    private static final Splitter CLASS_NAME_SPLITTER=Splitter.on('.');
    public static void printC(Object object){
        Class<?> clazz=object.getClass();
        List<String> classPaths= CLASS_NAME_SPLITTER.splitToList(clazz.getName());
        List<String> classes=INNER_CLASS_SPLITTER.splitToList(classPaths.get(classPaths.size()-1));
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(
                String.format("%nPackage [%s]%n", clazz.getPackage().getName()));
        stringBuilder.append(
                String.format("Class [%s]%n", classes.get(0)));
        if(classes.size()>1){
            stringBuilder.append(
                    String.format("InnerClass [%s]%n",classes.get(1)));
        }
        stringBuilder.append(
                String.format("toString [%s]%n",object.toString()));
        System.out.print(stringBuilder.toString());
    }
}
