package effectivejava.generic.util;

import com.google.common.io.Files;

import java.io.File;

/**
 * @author payno
 * @date 2019/8/28 21:59
 * @description
 */
public class Demo {
    public static void main(String[] args) {
        NodeMap nodeMap=NodeMap.instance();
        nodeMap.put("String",String.class,"abc");
        nodeMap.put("File", File.class,new File("d://"));
        File file=nodeMap.get("File",File.class);
        String string=nodeMap.get("String",String.class);
        Files.fileTreeTraverser().children(file).forEach(child->System.out.println(child));
        System.out.println(string);
    }
}
