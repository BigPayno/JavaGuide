package pattern.patterns.report.spi.esb.exception;

/**
 * 
 * @Title: EsbException.java
 * @author: yangjianwu
 * @date: 2019-07-19
 */
public class EsbException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3742877737503087829L;

	private static final String sysId = "RMSS";

    //错误码
    private String errorCode;
    //返回错误消息
    private String retMsg;

    public EsbException(String sysid, String errorCode, String retMsg) {
        super("[" + errorCode + "] " + retMsg);
        this.retMsg = retMsg;
        this.errorCode = sysid + errorCode;
    }

    public EsbException(String errorCode, String retMsg) {
        super("[" + errorCode + "] " + retMsg);
        this.retMsg = retMsg;
        this.errorCode = sysId + errorCode;
    }
    
    //为了区分行内异常信息
    public EsbException(String errorCode, String retMsg,boolean flag) {
        super("[" + errorCode + "] " + retMsg);
        this.retMsg = retMsg;
        this.errorCode = errorCode;
    }

    public EsbException(ErrorCode errorCode) {
        this(errorCode.getErrorCode(), errorCode.getErrMessage());
    }

    public EsbException(ErrorCode errorCode, String retMsg) {
        this(errorCode.getErrorCode(), retMsg);
    }

    public EsbException(ErrorCodeDefinition errorCodeDefinition) {
        super("[" + errorCodeDefinition.getErrorCode().getErrorCode() + "] " + errorCodeDefinition.getErrorCode().getErrMessage());
        this.retMsg = errorCodeDefinition.getErrorCode().getErrMessage();
        this.errorCode = sysId + errorCodeDefinition.getErrorCode().getErrorCode();
    }

    public EsbException(ErrorCodeDefinition errorCodeDefinition, String retMsg) {
        this(errorCodeDefinition.getErrorCode().getErrorCode(), retMsg);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = sysId + errorCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    @Override
    public String toString() {
        return "业务异常{" +
                "errorCode=" + errorCode +
                ", retMsg='" + retMsg + '\'' +
                '}';
    }
}
