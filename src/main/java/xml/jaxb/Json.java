package xml.jaxb;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author payno
 * @date 2019/9/2 15:02
 * @description
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Json {
    private String jsonString;
}
