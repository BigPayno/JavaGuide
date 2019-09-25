package utils.ftp;

/**
 * @author payno
 * @date 2019/8/29 17:34
 * @description
 */
public final class FtpClients {
    public static FtpClient getFtpClient(FtpTemplate ftpTemplate) {
        return FtpClient.getClient().connect(ftpTemplate.getFtpIp(), ftpTemplate.getFtpPort())
                .login(ftpTemplate.getFtpName(), ftpTemplate.getFtpPassword());
    }
}
