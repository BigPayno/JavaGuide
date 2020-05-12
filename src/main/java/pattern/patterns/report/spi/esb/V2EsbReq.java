package pattern.patterns.report.spi.esb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Service")
public class V2EsbReq<T>{
	@XmlElement(name = "Head")
    private V2EsbReqHead head;
    @XmlElement(name = "Body")
    private V2EsbReqBody<T> body;
    
    public static <T> V2EsbReq<T> of(V2EsbReqHead head,T request){
    	V2EsbReq<T> rEsbReq=new V2EsbReq<T>();
    	rEsbReq.setHead(head);
    	rEsbReq.setBody(V2EsbReqBody.of(request));
		return rEsbReq;
    }
}
