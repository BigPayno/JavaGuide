package pattern.patterns.report.spi.service.template;

import java.io.File;

import com.alibaba.fastjson.JSONObject;
import pattern.patterns.report.spi.esb.exception.EsbException;

public interface IResolveAppTemplate {
	JSONObject queryResolvedReport(String idCard,String mobile)
		throws Exception;
	JSONObject resolveReport(String userName, String mobile, String idCard, String reportJson)
		throws EsbException;
	JSONObject queryResolvedGjjData(String idCard,String orderSn,String channel)
		throws EsbException;
	void sendWarningBatchResultFile(File file);
}
