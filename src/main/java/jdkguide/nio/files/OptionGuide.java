package jdkguide.nio.files;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;
import static java.nio.file.StandardOpenOption.*;

/**
 * @author payno
 * @date 2019/10/28 19:53
 * @description
 */
public class OptionGuide {
    public static void main(String[] args) throws Exception{
        Files.write(
                Paths.get("d:/test.txt"), "payno".getBytes(StandardCharsets.UTF_8), APPEND);
        Files.copy(
                Paths.get("d:/test.txt"), Paths.get("d:/test2.txt"),
                REPLACE_EXISTING, COPY_ATTRIBUTES);
        Files.move(
                Paths.get("d:/test2.txt"), Paths.get("d:/test3.txt"),
                REPLACE_EXISTING);
    }
}
