package utils.ftp;

import lombok.Data;

import java.util.List;

/**
 * @author payno
 * @date 2019/8/29 17:45
 * @description
 */
@Data
public class FtpFileTemplate {
    private String localPath;
    private String localDirTemplate;
    private String ftpDirTemplate;
    private List<String> readyFiles;
    private List<String> targetFiles;
}
