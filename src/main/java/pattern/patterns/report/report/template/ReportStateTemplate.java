package pattern.patterns.report.report.template;

import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;
import pattern.patterns.report.spi.esb.HeadRetArray;

@Slf4j
public final class ReportStateTemplate {
	static final String SUCCESS_ESB_RET_CODE="00000000";
	static final String REPORT_SYS_UNUSUAL_STARTFIX="ECU";
	static final String REPORT_SYS_UNUSUAL_FLAG="用户";
	static final String REPORT_CUSTOM_UNUSUAL_FLAG="证件";
	static final String REPORT_NOT_EXIST="E701";
	
	public static boolean isSuccess(HeadRetArray retArray) {
		String retCode=retArray.getRetCode();
		return SUCCESS_ESB_RET_CODE.equals(retCode);
	}
	
	//	查无此人
	public static boolean customIsNotExist(HeadRetArray retArray) {
		String retCode=retArray.getRetCode();
		log.debug("征信返回查无此人:{}",REPORT_NOT_EXIST.equals(retCode));
		return REPORT_NOT_EXIST.equals(retCode);
	}
	
	//	证件相关信息错误 不需要进行重试
	public static boolean customInfoWrong(HeadRetArray retArray) {
		String retMsg=retArray.getRetMsg();
		log.debug("客户证件信息错误:{}",(!Strings.isNullOrEmpty(retMsg)&&retMsg.contains(REPORT_CUSTOM_UNUSUAL_FLAG)));
		return (!Strings.isNullOrEmpty(retMsg)&&retMsg.contains(REPORT_CUSTOM_UNUSUAL_FLAG));
	}
	
	//	征信相关信息错误 需要通知征信系统修改参数 检查错误 可以考虑延长等待时间或者发送短信至运维进行修复
	public static boolean reportSysUserWrong(HeadRetArray retArray) {
		String retCode=retArray.getRetCode();
		String retMsg=retArray.getRetMsg();
		log.debug("征信系统用户异常:{}",retCode.startsWith(REPORT_SYS_UNUSUAL_STARTFIX)||
				(!Strings.isNullOrEmpty(retMsg)&&retMsg.contains(REPORT_SYS_UNUSUAL_FLAG)));
		return retCode.startsWith(REPORT_SYS_UNUSUAL_STARTFIX)||
				(!Strings.isNullOrEmpty(retMsg)&&retMsg.contains(REPORT_SYS_UNUSUAL_FLAG));
	}
}
