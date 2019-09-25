package utils.ftp;

import org.apache.commons.net.ftp.FTPFile;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author payno
 * @date 2019/8/13 11:46
 * @description
 */
public class FtpFilter {
    /**
     * checkFileFilter
     *
     * @param checkFileName
     * @author: payno
     * @time: 2019/8/13 11:50
     * @description: 判断ftp目录是否具有某个文件
     * @return: java.util.function.Predicate<java.util.List < org.apache.commons.net.ftp.FTPFile>>
     */
    public static Predicate<List<FTPFile>> checkFileFilter(String checkFileName) {
        return ftpFiles -> {
            long fileExist = 0;
            try {
                String fileName = new String(checkFileName.getBytes("utf-8"));
                fileExist = ftpFiles.stream().filter(ftpFile -> {
                    return ftpFile.getName().equals(fileName);
                }).count();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return fileExist == 0 ? false : true;
        };
    }

    /**
     * checkFilesFilter
     *
     * @param checkFiles
     * @author: payno
     * @time: 2019/8/13 14:57
     * @description: 判断是否包含list中列举的文件名，全部包含则返回true
     * @return: java.util.function.Predicate<java.util.List < org.apache.commons.net.ftp.FTPFile>>
     */
    public static Predicate<List<FTPFile>> checkFilesFilter(List<String> checkFiles) {
        return ftpFiles -> {
            long filesExist = 0;
            List<String> fileNames = checkFiles.stream().map(fileName -> {
                String str = "";
                try {
                    str = new String(fileName.getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return str;
            }).collect(Collectors.toList());
            filesExist = ftpFiles.stream().filter(ftpFile -> {
                boolean fileExist = false;
                for (Iterator<String> iterator = fileNames.iterator(); iterator.hasNext(); ) {
                    if (ftpFile.getName().equals(iterator.next())) {
                        fileExist = true;
                    }
                }
                return fileExist;
            }).count();
            return filesExist == checkFiles.size() ? true : false;
        };
    }
}
