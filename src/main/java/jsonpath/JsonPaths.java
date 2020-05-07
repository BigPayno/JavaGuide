package jsonpath;

/**
 * @author payno
 * @date 2020/5/7 10:30
 * @description
 */
public class JsonPaths {

    static final char PARENT_PATH_LAST_CHAR = '[';
    public static String parent(String path){
        return path.substring(0,path.lastIndexOf(PARENT_PATH_LAST_CHAR));
    }

    public static void main(String[] args) {
        System.out.println(parent("[][]"));
    }
}
