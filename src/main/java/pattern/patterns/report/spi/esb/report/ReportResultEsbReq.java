package pattern.patterns.report.spi.esb.report;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pattern.patterns.report.spi.report.context.ReportEnums;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="request")
public class ReportResultEsbReq {
	//应用交易流水号
	@XmlElement
	String APLCN_TXN_SEQ_NO;
	//结果类型
	@XmlElement
	String RSLT_TP;
	//认证标识
	@XmlElement
	String AUT_IND;
	public void setRSLT_TP(ReportEnums.RSLT_TP json结果报文) {
		this.RSLT_TP=json结果报文.getTypeId();
	}
}
