package utils.ftp.demo;


import lombok.extern.slf4j.Slf4j;
import utils.ftp.FtpClient;
import utils.ftp.FtpException;
import utils.ftp.FtpFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author payno
 * @date 2019/8/8 11:12
 * @description
 */
@Slf4j
public class FtpClientDemo {
    public static FtpClient getClient() {
        String ip = "127.0.0.1";
        int port = 21;
        String name = "payno";
        String pass = "9527";
        return FtpClient.getClient().connect(ip, port).login(name, pass);
    }

    public static void downloadFile() {
        try (FtpClient ftpClient = getClient();) {
            ftpClient.downloadIf("target.txt", "d://parser//download//", FtpFilter.checkFileFilter("ready.txt"));
        } catch (FtpException ftpException) {
            if (ftpException.checkType(FtpException.NOT_READY)) {
                log.info("ftp服务器尚未准备好数据，稍后重试！");
            }
        }
    }

    public static void downloadFiles() {
        try (FtpClient ftpClient = getClient()) {
            List<String> ftpFileNames = new ArrayList<>(2);
            ftpFileNames.add("target.txt");
            ftpFileNames.add("target2.txt");
            ftpClient.downloadIf(ftpFileNames, "d://parser//download//", FtpFilter.checkFileFilter("ready.txt"));
        } catch (FtpException ftpException) {
            if (ftpException.checkType(FtpException.NOT_READY)) {
                log.info("ftp服务器尚未准备好数据，稍后重试！");
            }
        }
    }

    public static void downloadFilesAfterCheck() {
        try (FtpClient ftpClient = getClient()) {
            List<String> ftpFiles = new ArrayList<>(2);
            ftpFiles.add("target.txt");
            ftpFiles.add("target2.txt");
            List<String> checkFiles = new ArrayList<>(2);
            checkFiles.add("ready.txt");
            checkFiles.add("ready3.txt");
            ftpClient.downloadIf(ftpFiles, "d://parser//download//", FtpFilter.checkFilesFilter(checkFiles));
        } catch (FtpException ftpException) {
            if (ftpException.checkType(FtpException.NOT_READY)) {
                log.info("ftp服务器尚未准备好数据，稍后重试！");
            }
        }
    }

    public static void main(String[] args) {

    }
}
