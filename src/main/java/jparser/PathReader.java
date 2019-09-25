package jparser;

/**
 * @author payno
 * @date 2019/9/6 08:24
 * @description
 */
public interface PathReader {
    /**
     * read
     * @author: payno
     * @time: 2019/9/6 08:24
     * @description: 读取指定jsonpath路径的对象
     * @param text
     * @param jsonPath
     * @return: java.lang.Object
     */
    Object read(String text,String jsonPath);
}
