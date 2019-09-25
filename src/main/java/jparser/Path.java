package jparser;

import lombok.Data;

/**
 * @author payno
 * @date 2019/9/6 08:34
 * @description
 */
public class Path {
    private Path(String path){this.path=path;}
    private String path;
    public static Path of(String path){ return new Path(path); }
}
