package pattern.patterns.report.spi.esb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Service")
public class V2EsbRes<T>{
	@XmlElement(name = "Head")
    private V2EsbResHead head;
    @XmlElement(name = "Body")
    private V2EsbResBody<T> body;
    
    public static <T> V2EsbRes<T> of(V2EsbResHead head,T response){
    	V2EsbRes<T> rEsbReq=new V2EsbRes<T>();
    	rEsbReq.setHead(head);
    	rEsbReq.setBody(V2EsbResBody.of(response));
		return rEsbReq;
    }
}
