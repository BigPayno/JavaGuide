package xml.jaxb;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * @author payno
 * @date 2019/9/2 14:53
 * @description
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Json.class})
public class Request<T> {
    @XmlAnyElement(lax = true)
    private T request;
}
