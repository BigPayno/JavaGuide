package jdkguide.nio.files;


import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author payno
 * @date 2019/10/28 19:36
 * @description 通过path执行的是文件的原子操作，而流和文件则不是
 */
public class PathGuide {
    public static void main(String[] args) throws Exception{
        Path path= Paths.get("d:/test.txt");
        if (Files.deleteIfExists(path)){
            Files.createFile(path);
            try(BufferedWriter bufferedWriter=Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
                bufferedWriter.write("hello,java.nio.nio.Path");
            }
        }

        Files.createTempFile(Paths.get("d:/"),"test",".gz");

        System.out.println(Files.getFileStore(path).type());

        Files.readAllLines(path);

        Files.newDirectoryStream(Paths.get("d:/")).iterator().forEachRemaining(System.out::println);
    }
}
