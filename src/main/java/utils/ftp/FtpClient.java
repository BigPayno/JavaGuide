package utils.ftp;


import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author payno
 * @date 2019/8/8 10:47
 * @description
 */
@Slf4j
public class FtpClient implements AutoCloseable {
	private static final String DIR_SEPARATOR="/";
	private static final Splitter DIR_SPLITTER= Splitter.on(DIR_SEPARATOR);
	private static final Joiner DIR_JOINER= Joiner.on(DIR_SEPARATOR);
    private String ip;
    private int port;
    private String name;
    private String pass;
    private FTPClient ftpClient = null;

    public static FtpClient getClient() {
        return new FtpClient();
    }

    public FtpClient connect(String ip, int port) {
        ftpClient = new FTPClient();
        this.ip = ip;
        this.port = port;
        try {
            ftpClient.connect(ip, port);
            log.info("ftpServer连接成功，{}:{}", ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public FtpClient login(String name, String pass) {
        try {
            boolean loginFlag = ftpClient.login(name, pass);
            if (!loginFlag) {
                throw new FtpException(ip, port, name, pass, FtpException.LOGIN_FAIL);
            }
            log.info("ftpServer登陆成功，{}", name);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                throw new FtpException(ip, port, name, pass, FtpException.ACCESS_DENIED);
            }
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FtpException(ip, port, name, pass, FtpException.OTHER_EXCEPTION);
        }
        this.name = name;
        this.pass = pass;
        return this;
    }

    private void dirPreHandle(String ftpDirName) {
        if (StringUtils.isBlank(ftpDirName)) {
            ftpDirName = "/";
        }
    }

    public FtpClient accessDir(String ftpDirName) {
        dirPreHandle(ftpDirName);
        try {
            if (!ftpClient.changeWorkingDirectory(ftpDirName)) {
                log.info("切换ftp下载目录[{}]失败", ftpDirName);
                List<String> dirPreElements=DIR_SPLITTER.splitToList(ftpDirName);
                for(int i=2;i<dirPreElements.size();i++) {
                	String ftpDir=DIR_JOINER.join(dirPreElements.subList(0, i)).concat(DIR_SEPARATOR);
                	if(!ftpClient.changeWorkingDirectory(ftpDir)) {
                		ftpClient.makeDirectory(ftpDir);
                		log.info("创建ftp目录[{}]", ftpDir);
                	}
                	ftpClient.changeWorkingDirectory(ftpDirName);
                }
            }
            log.info("成功进入ftp目录[{}]", ftpDirName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * downloadIf
     *
     * @param ftpFileName     目标文件
     * @param localFilePath   保存本地文件夹地址
     * @param directoryFilter 对文件目录进行判断//@todo 其实改成对ftpClient的判断更好
     * @author: payno
     * @time: 2019/8/13 15:13
     * @description:
     * @return: com.gz.parser.core.utils.ftp.FtpClient
     */
    public FtpClient downloadIf(String ftpFileName, String localFilePath, Predicate<List<FTPFile>> directoryFilter) throws FtpException {
        try {
            List<FTPFile> ftpFileList = Arrays.asList(ftpClient.listFiles());
            if (directoryFilter.test(ftpFileList)) {
                downloadFile(ftpFileName, localFilePath);
            } else {
                throw new FtpException(ip, port, name, pass, FtpException.NOT_READY);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * downloadIf
     *
     * @param ftpFileNames    待下载的文件列表
     * @param localFilePath   下载后保存的本地路径
     * @param directoryFilter 对目标文件夹的过滤操作
     * @author: payno
     * @time: 2019/8/13 15:30
     * @description:
     * @return: com.gz.parser.core.utils.ftp.FtpClient
     */
    public FtpClient downloadIf(List<String> ftpFileNames, String localFilePath, Predicate<List<FTPFile>> directoryFilter) throws FtpException {
        try {
            List<FTPFile> ftpFileList = Arrays.asList(ftpClient.listFiles());
            if (directoryFilter.test(ftpFileList)) {
                ftpFileNames.forEach(ftpFileName -> {
                    String fileName = new String(ftpFileName.getBytes(Charsets.UTF_8));
                    downloadFile(fileName, localFilePath);
                });
            } else {
                throw new FtpException(ip, port, name, pass, FtpException.NOT_READY);
            }
        } catch (UnsupportedOperationException | IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * downloadFile
     *
     * @param ftpFileName   ftp文件名
     * @param localFilePath 生成的本地文件路径
     * @author: payno
     * @time: 2019/8/13 11:00
     * @description: 会检测文件是否存在
     * @return: com.gz.parser.core.utils.ftp.FtpClient
     */
    public FtpClient downloadFile(String ftpFileName, String localFilePath) {
        try {
            List<FTPFile> ftpFileList = Arrays.asList(ftpClient.listFiles());
            ftpFileList.stream().filter(file -> file.getName().equals(ftpFileName)).forEach(file -> {
                try (FileOutputStream is = new FileOutputStream(new File(localFilePath + ftpFileName))) {
                    ftpClient.retrieveFile(file.getName(), is);
                } catch (Exception e) {
                    log.warn("下载ftp文件:{}失败!!!", ftpFileName);
                    e.printStackTrace();
                }
                log.info("下载ftp文件地址{}！！！", localFilePath + ftpFileName);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public FtpClient uploadFile(String localDir, String fileName) {
        String localFilePath = localDir + fileName;
        File file = new File(localFilePath);
        if (file.exists()) {
            try (InputStream is = Files.asByteSource(file).openStream()) {
                ftpClient.storeFile(file.getName(), is);
            } catch (IOException e) {
                throw new FtpException(ip, port, name, pass, FtpException.UPLOAD_FAIL);
            }
            log.info("文件上传成功！！！", localFilePath);
        } else {
            log.error("待上传文件{}不存在！！！", localFilePath);
        }
        return this;
    }

    public FtpClient uploadFiles(String localDir, List<String> fileNames) {
        fileNames.forEach(fileName -> {
            uploadFile(localDir, fileName);
        });
        return this;
    }

    public List<FTPFile> listDirs(){
        FTPFile[] ftpDirs=null;
        try{
            ftpDirs=ftpClient.listDirectories();
        }catch (IOException e){
            throw new FtpException(ip,port,name,pass,FtpException.OTHER_EXCEPTION);
        }
        return ImmutableList.copyOf(ftpDirs);
    }

    public List<FTPFile> listFiles(){
        FTPFile[] ftpFiles=null;
        try{
            ftpFiles=ftpClient.listFiles();
        }catch (IOException e){
            throw new FtpException(ip,port,name,pass,FtpException.OTHER_EXCEPTION);
        }
        return ImmutableList.copyOf(ftpFiles);
    }

    @Override
    public void close() {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
