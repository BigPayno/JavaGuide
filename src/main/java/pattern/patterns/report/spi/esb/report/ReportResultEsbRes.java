package pattern.patterns.report.spi.esb.report;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="response")
public class ReportResultEsbRes {
	@XmlElement
	String xml;
	@XmlElement
	String html;
	@XmlElement
	String json;
}
