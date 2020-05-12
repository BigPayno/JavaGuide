package pattern.patterns.report.spi.esb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import pattern.patterns.report.spi.esb.report.ReportApplyEsbRes;
import pattern.patterns.report.spi.esb.report.ReportResultEsbRes;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Body")
@XmlSeeAlso({
	ReportApplyEsbRes.class,
	ReportResultEsbRes.class
})
public class V2EsbResBody<T> {
	//序列化的时候去掉命名空间
	@XmlAnyElement(lax = true)
	private T response;
	
	public static <T> V2EsbResBody<T> of(T t){
		V2EsbResBody<T> body=new V2EsbResBody<T>();
		body.setResponse(t);
		return body;
	}
}
