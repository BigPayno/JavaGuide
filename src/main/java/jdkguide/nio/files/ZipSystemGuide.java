package jdkguide.nio.files;

import com.alibaba.excel.constant.ExcelXmlConstants;
import com.sun.nio.zipfs.ZipFileSystem;
import com.sun.nio.zipfs.ZipPath;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author payno
 * @date 2019/11/26 11:08
 * @description
 *      ZipFileSystem获取方式通过FileSystems
 *      可以访问jar，zip,当路径是zip或者jar时
 */
public class ZipSystemGuide {
    public static void main(String[] args) throws Exception {
        FileSystem system=
            FileSystems.newFileSystem(Paths.get("d:/gz.zip"),null);
        if(system instanceof ZipFileSystem){
            ZipFileSystem zipSystem=(ZipFileSystem)system;
            /**
             * NIO的Files提供了诸多文件操作的函数，此处解压使用其copy、createDirectories、walkFileTree来实现。
             *
             *     static Path copy(Path from, Path to, CopyOption... options)
             *     static Path copy(Path from, Path to, CopyOption... options)
             *         将from复制或移动到给定位置，并返回to。如果目标路径已经存在，那么复制或者移动将失败。如果想要覆盖已有的目标路径可以使用REPLACE_EXISTING选项。如果想要复制所有的文件属性，可以使用COPY_ATTRIBUTES选项。Files.copy(fromPath,toPath,StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.COPY_ATTRIBUTES)
             *     static Path createFile(Path path, FileAttribute<?>... attrs)
             *     static Path createDirectory(Path path, FileAttribute<?>... attrs)
             *     static Path createDirectories(Path path, FileAttribute<?>... attrs)
             *         创建一个文件或目录，createDirectorIes方法还会创建路径中所有的中间目录。
             */
            ZipPath zipPath=zipSystem.getPath("\\gz");
            copyZip(zipPath);
            //Files.copy(zipPath,Paths.get("d:/compress/gz"));
        }
    }


    /**
     * 进行整个文件解压
     * static FileVisitResult visitFile(T path, BasicFileAttributes attrs)
     *     在访问文件或目录时被调用，返回CONTINUE、SKIP_SUBTREE、SKIP_SIBLINGS和TERMINATE之一
     *     ,默认实现是不做任何操作而继续访问。
     *
     * static FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs)
     * static FileVisitResult postVisitDirectory(T dir, BasicFileAttributes attrs)
     *     在访问目录之前和之后被调用，默认实现是不做任何操作而继续访问。
     *
     * static FileVisitResult visitFileFailed(T path, IOException exc)
     *     如果在试图获取给定文件的信息时抛出异常，则该方法被调用。默认实现是重新抛出异常，这
     *     回导致访问操作以这个异常而终止。如果你想自己访问，可以覆盖这个方法。
     */
    public static void copyZip(ZipPath path) throws Exception{
        Files.walkFileTree(path, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                /**
                 * 创建解压后的文件夹
                 */
                Files.createDirectory(Paths.get("d:\\compress",dir.toString()));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file,Paths.get("d:\\compress",file.toString()));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });

    }
}
