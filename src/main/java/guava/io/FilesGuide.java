package guava.io;

import com.google.common.base.Charsets;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author payno
 * @date 2019/8/16 09:59
 * @description
 */
public class FilesGuide {
    private static void buildDir(File file) throws Exception{
        Files.createTempDir();
        Files.createParentDirs(file);
    }
    private static void fileTree(File file) throws Exception{
        //子文件 Iterable<File>
        Files.fileTreeTraverser().children(file);
        //广度优先遍历 FluentIterable<File>
        Files.fileTreeTraverser().breadthFirstTraversal(file).forEach(System.out::println);
        //后序遍历 FluentIterable<File>
        Files.fileTreeTraverser().postOrderTraversal(file);
        //前序遍历 FluentIterable<File>
        Files.fileTreeTraverser().preOrderTraversal(file);
    }

    private static void readerOrWriter(File file, Charset charset) throws Exception{
        Files.newReader(file, charset);
        Files.newWriter(file,charset);
    }

    private static void sourceOrSink(File file) throws Exception{
        Files.asByteSource(file);
        Files.asCharSource(file, Charsets.UTF_8);
        Files.asByteSink(file);
        Files.asByteSink(file, FileWriteMode.APPEND);
        Files.asCharSink(file,Charsets.UTF_8);
        Files.asCharSink(file,Charsets.UTF_8,FileWriteMode.APPEND);
    }

    private static void actions(File from,File to) throws Exception{
        byte[] bytes=Files.toByteArray(from);
        List<String> lines=Files.readLines(from,Charsets.UTF_8);
        Files.move(from,to);
        Files.copy(from,to);
        Files.write(Files.toByteArray(from),to);
        Files.equal(from,to);
    }

    private static void filters() throws Exception{
        Files.isDirectory();
        Files.isFile();
    }

    //c文件得到一些buffer
    private static void nio(File file) throws Exception{
        Files.map(file);
    }
    public static void main(String[] args) throws Exception{

    }
}
