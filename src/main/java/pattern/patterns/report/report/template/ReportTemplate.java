package pattern.patterns.report.report.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import pattern.patterns.report.spi.esb.V2EsbReq;
import pattern.patterns.report.spi.esb.V2EsbReqHead;
import pattern.patterns.report.spi.esb.V2EsbRes;
import pattern.patterns.report.spi.esb.context.IV2EsbProxy;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbReq;
import pattern.patterns.report.spi.esb.report.ReportApplyEsbRes;
import pattern.patterns.report.spi.esb.report.ReportResultEsbReq;
import pattern.patterns.report.spi.esb.report.ReportResultEsbRes;
import pattern.patterns.report.spi.report.template.IReportTemplate;
import pattern.patterns.report.spi.service.template.IResolveAppTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ReportTemplate implements IReportTemplate {

	@Autowired
	IResolveAppTemplate resolveAppProxy;
	
	@Autowired
	IV2EsbProxy v2EsbProxy;
	
	private int REPORT_SYNC_STATUS=0;

	@Override
	public boolean findCacheReportFromResolveApp(String idCard,String mobile) {
		try {
			JSONObject response = resolveAppProxy.queryResolvedReport(idCard,mobile);
			if (
				JSONPath.eval(response, "data.data")!=null&& 
				REPORT_SYNC_STATUS==(Integer)JSONPath.eval(response, "data.status")
			) {
				log.info("数据平台历史征信数据:[idCard=({}),report=({})]", idCard, response);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public V2EsbRes<ReportApplyEsbRes> sendReportQueryApplyToEsb(ReportApplyEsbReq reportApplyEsbReq) throws Exception {
		V2EsbReq<ReportApplyEsbReq> request=V2EsbReq.of(
				V2EsbReqHead.of("40001","ESBS018000000576"), reportApplyEsbReq);
		V2EsbRes<ReportApplyEsbRes> response=v2EsbProxy
				.send(request, ReportApplyEsbRes.class);
		return response;
	}

	@Override
	public V2EsbRes<ReportResultEsbRes> findReportResultFromEsb(ReportResultEsbReq reportResultEsbReq) throws Exception {
		V2EsbReq<ReportResultEsbReq> request=V2EsbReq.of(
				V2EsbReqHead.of("40002","ESBS018000000577"), reportResultEsbReq);
		V2EsbRes<ReportResultEsbRes> response=v2EsbProxy
				.send(request, ReportResultEsbRes.class);
		return response;
	}

	@Override
	public String syncReportToResolveApp(String userName, String mobile, String idCard, String reportJson) throws Exception{
		return resolveAppProxy
				.resolveReport(userName, mobile, idCard, reportJson)
				.toJSONString();
	}
}
