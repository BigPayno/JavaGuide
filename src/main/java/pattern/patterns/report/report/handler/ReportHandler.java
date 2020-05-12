package pattern.patterns.report.report.handler;

import org.apache.shiro.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import pattern.patterns.report.report.context.ReportContexts;
import pattern.patterns.report.report.template.ReportStateTemplate;
import pattern.patterns.report.spi.esb.HeadRetArray;
import pattern.patterns.report.spi.esb.exception.ErrorCodeDefinition;
import pattern.patterns.report.spi.esb.exception.EsbException;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbRes;
import pattern.patterns.report.spi.esb.report.ReportResultEsbRes;
import pattern.patterns.report.spi.report.context.IReportContext;
import pattern.patterns.report.spi.report.handler.IReportHandler;
import pattern.patterns.report.util.Threads;

import lombok.extern.slf4j.Slf4j;

/*
 * 	主要处理业务异常及展示日志
 *	如果需要拓展请使用ReportHandlerAdaptor、ReportHandlerWrapper
 * 	如果需要对具体的Context处理，实现类修改泛型范围
 * 	作为代价就是Executor也要建立多个实例
 *  ReportExecutor<LimitApplyContext> limitReportExecutor
 *			=new ReportExecutor<LimitApplyContext>(
 *				new ReportHandlerWrapper<LimitApplyContext, IReportContext<?>>(
 *						new ReportHandler()) {});
 *	目前使用的是
 *		IReportExecutor<IReportContext<?>> reportExecutor=
 *			=new ReportExecutor<IReportContext<?>>(
 *				new ReportHandler());
 *
 * 基于事件的On处理
 */
@Slf4j
@Component
public class ReportHandler implements IReportHandler<IReportContext<?>> {
	
	@Autowired
	ReportContexts reportContexts;
	
	static final String BLANK_REPORT_TEMPLATE_FILEPATH="template/blankCreditReport.json";
	
	static final String BLANK_REPORT_CREATE_DATETIME_JSONPATH="$.Document.PRH.PA01.PA01A.PA01AR01";
	
	static final String ERR_MSG_ON_BLANK_REPORT_INSERT_FAIL="征信白户模板报文插入异常!";
	
	private void checkCacheOnFail(IReportContext<?> context) {
		if(context.hasRetryTimes()) {
			context.handle(context0->{
				reportContexts.cache(context0.getCacheId(),context0);
			});
		}else {
			context.handle(context0->{
				reportContexts.expireCache(context0.getCacheId(),context0.getClass());
			});
		}
	}
	
	private void checkCacheOnSuccess(IReportContext<?> context) {
		context.handle(context0->{
			reportContexts.expireCache(context0.getCacheId(),context0.getClass());
		});
	}
	
	@Override
	public void onReportQueryApplySuccess(IReportContext<?> context) throws EsbException {
		HeadRetArray retArray=context.getReportApplyEsbRes().getHead().getHeadRetArray();
		if(ReportStateTemplate.isSuccess(retArray)) {
			ReportApplyEsbRes esbRes=context.getReportApplyEsbRes().getBody().getResponse();
			context.setSeqNo(esbRes.getAPLCN_TXN_SEQ_NO());
			log.info("征信报文查询申请成功:[idCard=({}),seqNo=({})]",context.getIdCard(),context.getSeqNo());
			Threads.sleep(context.getWaitTimeOnGettingReportSeq());
		}else if(ReportStateTemplate.customIsNotExist(retArray)|| ReportStateTemplate.customInfoWrong(retArray)) {
			context.setRetryForApply(0);
			log.info("征信报文查询申请错误，用户信息异常，终止重试:[idCard=({}),seqNo=({})]",context.getIdCard(),context.getSeqNo());
			throw new EsbException(retArray.getRetCode(), retArray.getRetMsg());
		}else if(ReportStateTemplate.reportSysUserWrong(retArray)) {
			log.info("征信报文查询申请异常，征信系统异常:[idCard=({}),seqNo=({})]",context.getIdCard(),context.getSeqNo());
			throw new EsbException(retArray.getRetCode(), retArray.getRetMsg());
		}else {
			log.info("征信报文查询申请异常:[idCard=({}),seqNo=({}),errMsg=({})]",context.getIdCard(),context.getSeqNo(),retArray.getRetMsg());
			throw new EsbException(ErrorCodeDefinition.REPORT_ESB_APPLY_FAIL);
		}
	}

	@Override
	public void onReportResultQuerySuccess(IReportContext<?> context) throws EsbException {
		HeadRetArray retArray=context.getReportResultEsbRes().getHead().getHeadRetArray();
		if(ReportStateTemplate.isSuccess(retArray)) {
			ReportResultEsbRes esbRes=context.getReportResultEsbRes().getBody().getResponse();
			context.setReport(
					Base64.decodeToString(esbRes.getJson()));
			log.info("征信报文查询成功:[idCard=({}),seqNo=({}),report=({})]",context.getIdCard(),context.getSeqNo(),context.getReport());
		}else if(ReportStateTemplate.customIsNotExist(retArray)) {
			try {
				//征信小白模板	懒得改成common.util.resources还要验证
				Object defaultReport = JSONObject.parse(Resources.asCharSource(
						Resources.getResource(BLANK_REPORT_TEMPLATE_FILEPATH), Charsets.UTF_8).read()); 
				JSONPath.set(defaultReport, BLANK_REPORT_CREATE_DATETIME_JSONPATH, "20200101");
				context.setReport(defaultReport.toString());
			}catch (Exception e) {
				log.info(ERR_MSG_ON_BLANK_REPORT_INSERT_FAIL,e);
				throw new EsbException(retArray.getRetCode(),ERR_MSG_ON_BLANK_REPORT_INSERT_FAIL);
			}
			log.info("征信报文查无此人，认为是征信白户:[idCard=({}),seqNo=({}),report=({})]",context.getIdCard(),context.getSeqNo(),context.getReport());
		}else if(ReportStateTemplate.customInfoWrong(retArray)) {
			context.setRetryForResult(0);
			log.info("征信报文查询错误，用户信息异常，终止重试:[idCard=({}),seqNo=({})]",context.getIdCard(),context.getSeqNo());
			throw new EsbException(retArray.getRetCode(),retArray.getRetMsg());
		}else if(ReportStateTemplate.reportSysUserWrong(retArray)) {
			log.info("征信报文查询异常，征信系统异常:[idCard=({}),seqNo=({})]",context.getIdCard(),context.getSeqNo());
			throw new EsbException(retArray.getRetCode(),retArray.getRetMsg());
		}else {
			log.info("征信报文查询异常:[idCard=({}),seqNo=({})]",context.getIdCard(),context.getSeqNo());
			throw new EsbException(ErrorCodeDefinition.REPORT_ESB_QUERY_FAIL);
		}
	}

	@Override
	public void onReportSyncResolveAppSuccess(IReportContext<?> context) throws EsbException {
		log.info("征信报告同步成功:[idCard=({})]", context.getIdCard());
		context.setReportSyncToResolveSuccess(true);
		checkCacheOnSuccess(context);
	}

	@Override
	public void onReportQueryApplyFail(IReportContext<?> context, Exception e) throws EsbException {
		log.info("征信报文查询申请请求异常:[idCard=({}),unuseRetryTimes=({}),errMsg=({})]",
				context.getIdCard(),context.getRetryTimes(),e.getMessage());
		context.reportApplyQueryFail();
		checkCacheOnFail(context);
		if(e instanceof EsbException) {
			throw (EsbException) e;
		}else {
			throw new EsbException(ErrorCodeDefinition.REPORT_ESB_APPLY_FAIL);
		}
	}

	@Override
	public void onReportResultQueryFail(IReportContext<?> context, Exception e) throws EsbException {
		log.info("征信报文结果查询请求异常:[idCard=({}),unuseRetryTimes=({}),errMsg=({})]",
				context.getIdCard(),context.getRetryTimes(),e.getMessage());
		context.reportResultQueryFail();
		checkCacheOnFail(context);
		if(e instanceof EsbException) {
			throw (EsbException) e;
		}else {
			throw new EsbException(ErrorCodeDefinition.REPORT_ESB_QUERY_FAIL);
		}
	}

	@Override
	public void onReportSyncResolveAppFail(IReportContext<?> context, Exception e) throws EsbException {
		log.info("征信报文同步异常:[idCard=({}),unuseRetryTimes=({}),errMsg=({})]",
				context.getIdCard(),context.getRetryTimes(),e.getMessage());
		context.reportSyncApplyFail();
		checkCacheOnFail(context);
		if(e instanceof EsbException) {
			throw (EsbException) e;
		}else {
			throw new EsbException(ErrorCodeDefinition.REPORT_RESOLVE_APP_SYNC_FAIL);
		}
	}
}
