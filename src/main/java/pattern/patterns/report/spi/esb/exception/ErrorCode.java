package pattern.patterns.report.spi.esb.exception;

/**
 * 错误码
 */
public class ErrorCode {

    private final String errorCode;

    private final String errMessage;


    public ErrorCode(String errorCode, String errMessage) {
        super();
        this.errorCode = errorCode;
        this.errMessage = errMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }


    public String getErrMessage() {
        return errMessage;
    }


    /**
     * (non-Javadoc)
     */
    @Override
    public String toString() {
        return errorCode + "-" + errMessage;
    }
}
