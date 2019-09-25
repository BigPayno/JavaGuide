package utils.ftp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * @author payno
 * @date 2019/8/8 19:03
 * @description
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class FtpException extends RuntimeException {
    private String ip;
    private int port;
    private String name;
    private String pass;
    private String type;
    public static final String OTHER_EXCEPTION = "其他异常！";
    public static final String LOGIN_FAIL = "登陆失败！";
    public static final String ACCESS_DENIED = "服务器拒绝访问！";
    public static final String NOT_READY = "下载文件未准备完毕";
    public static final String NO_DIRECTORY = "文件目录不存在";
    public static final String UPLOAD_FAIL = "上传失败！";

    public FtpException(String ip, int port, String name, String pass, String type) {
        super(
                "FtpClient:".concat(ip).concat(":").concat(String.valueOf(port)).concat(":").concat(name).concat(":").concat(pass)
                        .concat("ErrorMessage:").concat(type)
        );
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.pass = pass;
        this.type = type;
    }

    public boolean checkType(String type) {
        Objects.requireNonNull(type);
        return type.equals(this.type);
    }
}
