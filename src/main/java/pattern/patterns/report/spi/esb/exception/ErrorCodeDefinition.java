package pattern.patterns.report.spi.esb.exception;

/**
 * 错误码接口
 * 1800001~1809999 为公共错误信息
 */
public enum ErrorCodeDefinition {
    // ESB异常汇总
	PRODUCT_NOT_EXIST(new ErrorCode("P00001", "产品编码不正确，取不到该产品信息!")),
	PRODUCT_CLOSE(new ErrorCode("P00002", "产品已关闭，暂停使用!")),
	PRODUCT_EXPIRES(new ErrorCode("P00003", "产品已过期，无法使用!")),
	PRODUCT_ERROR(new ErrorCode("P00004", "产品信息配置不正确!")),
	PRODUCT_SCENE_ERROR(new ErrorCode("P00005", "产品流程策略不存在!")),
	ERROR_REDIS_GET(new ErrorCode("B00001", "授信审批任务失败")),
	INVALID_PARAMETER(new ErrorCode("B00002", "参数不合法")),
	GJJ_ORDERSN_ISNULL(new ErrorCode("B00003", "公积金流水不能为空")),
	ADD_LIMIT_APPLY_TASK_FAIL(new ErrorCode("B00003", "授信审批任务新增失败")),
	ESB_HANDLE_FAIL(new ErrorCode("B00004", "处理ESB请求失败")),
	QUERY_HISTORY_CREDIT_REPORT(new ErrorCode("B00005", "查询历史征信报告失败")),
	LIMIT_APPLY_FAIL(new ErrorCode("B00007", "授信审批失败")),
	LIMIT_APPLY_REPRTITION(new ErrorCode("B00008", "授信已审批,无法重复申请")),
	LIMIT_APPLY_NOT_EXIST(new ErrorCode("B00009", "业务流水无对应授信审批记录")),
	FTP_LOGIN_FAIL(new ErrorCode("B00010", "登陆FTP服务器失败")),
	CREATE_CREDIT_REPORT_LOCAL_FAIL(new ErrorCode("B00011", "生成本地征信文件失败")),
	QUERY_CREDIT_REPORT_FAIL(new ErrorCode("B00012", "查询征信报告失败")),
	FORMAT_CREDIT_REPORT_FAIL(new ErrorCode("B00013", "征信XML转JSON异常")),
	GET_CREDIT_REPORT_FAIL(new ErrorCode("B00014", "获取数据平台解析后征信报告数据失败")),
	NO_MATCH_SERVICE(new ErrorCode("B00015", "交易码无对应交易")),
	CREDIT_APPLY_REPRTITION(new ErrorCode("B00016", "用信已审批,无法重复申请")),
	CREDIT_APPLY_NOT_EXIST(new ErrorCode("B00017", "业务流水无对应用信审批记录")),
	LIMIT_APPLY_NOT_AUTHORIZATION(new ErrorCode("B00018", "用户未授权,拒绝交易")),
	GLB_SEQ_NO_ERROR(new ErrorCode("B00019", "ESB交易流水号获取失败")),
	GET_GJJ_BYORDERSN_FALI(new ErrorCode("B00020", "根据公积金流水号获取公积金数据失败")),
	GET_GJJ_BYIDENTNO_FALI(new ErrorCode("B00021", "非申请人公积金信息")),
    ESB_QUERY_ERR(new ErrorCode("B00099", "ESB请求失败")),
    REPORT_ESB_APPLY_FAIL(new ErrorCode("BR0001", "ESB征信报文申请请求失败")),
    REPORT_ESB_QUERY_FAIL(new ErrorCode("BR0002", "ESB征信报文查询请求失败")),
    REPORT_RESOLVE_APP_SYNC_FAIL(new ErrorCode("BR0003", "征信报文同步解析平台失败")),
    REPORT_ESB_RESULT_BUILDING(new ErrorCode("BR0004", "ESB征信报文正在生成"));

    /**
     * 错误码实例
     */
    private ErrorCode errorCode;

    ErrorCodeDefinition(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 设置静态异常错误描述
     *
     * @param errMessage 错误描述字符串：系统异常
     * @return 异常码错误对象
     */
    public ErrorCode setMessage(String errMessage) {
        return new ErrorCode(errorCode.getErrorCode(), errMessage);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

