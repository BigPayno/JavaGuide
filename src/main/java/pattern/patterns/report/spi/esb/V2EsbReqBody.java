package pattern.patterns.report.spi.esb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import pattern.patterns.report.spi.esb.report.ReportApplyEsbReq;
import pattern.patterns.report.spi.esb.report.ReportResultEsbReq;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Body")
@XmlSeeAlso({
	ReportApplyEsbReq.class,
	ReportResultEsbReq.class
})
public class V2EsbReqBody<T> {
	@XmlAnyElement(lax = true)
	private T request;
	
	public static <T> V2EsbReqBody<T> of(T t){
		V2EsbReqBody<T> body=new V2EsbReqBody<T>();
		body.setRequest(t);
		return body;
	}
}
