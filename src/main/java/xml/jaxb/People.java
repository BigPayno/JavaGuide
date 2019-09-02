package xml.jaxb;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author payno
 * @date 2019/9/2 14:47
 * @description
 */
@Data
@XmlRootElement(name = "p")
@XmlAccessorType(XmlAccessType.FIELD)
public class People {
    @XmlElement(name = "perName")
    private String name="payno";
}
