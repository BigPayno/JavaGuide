package jdkguide.nio.files;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author payno
 * @date 2019/10/28 19:29
 * @description
 */
public class PathsGuide {
    public static void main(String[] args) {
        Path path= Paths.get("d:/","test.txt");
        Path path1=Paths.get("d:/test.txt");
        Path path2=Paths.get(URI.create("nio:///d:/test.txt"));
        Path path3= FileSystems.getDefault().getPath("d:/test.txt");
        path.toFile();
        path.toUri();
    }
}
