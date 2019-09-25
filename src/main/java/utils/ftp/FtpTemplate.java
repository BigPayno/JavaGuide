package utils.ftp;

import lombok.Data;

/**
 * @author payno
 * @date 2019/8/29 17:35
 * @description
 */
@Data
public class FtpTemplate {
    private String ftpIp;
    private int ftpPort;
    private String ftpName;
    private String ftpPassword;
}
