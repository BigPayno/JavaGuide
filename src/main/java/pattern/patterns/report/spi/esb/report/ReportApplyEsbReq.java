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
public class ReportApplyEsbReq {
	//证件类型
	@XmlElement
	String ID_TP_CD;
	//证件号码
	@XmlElement
	String ID_NO;
	//中文名称
	@XmlElement
	String CN_NM;
	//查询原因
	@XmlElement
	String QRY_RSN;
	//查询策略
	@XmlElement
	String QRY_POLC;
	//认证标识
	@XmlElement
	String AUT_IND;
	public void setID_TP_CD(ReportEnums.ID_TP_CD type) {
		this.ID_TP_CD=type.getTypeId();
	}
	public void setQRY_RSN(ReportEnums.QRY_RSN type) {
		this.QRY_RSN=type.getTypeId();
	}
	public void setQRY_POLC(ReportEnums.QRY_POLC type) {
		this.QRY_POLC=type.getTypeId();
	}
}
