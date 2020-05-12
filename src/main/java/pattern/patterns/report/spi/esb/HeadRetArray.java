package pattern.patterns.report.spi.esb;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="RET_ARRAY")
public class HeadRetArray {
    @XmlElement(name = "RET_CODE")
    private String retCode;
    @XmlElement(name = "RET_MSG")
    private String retMsg;
}
