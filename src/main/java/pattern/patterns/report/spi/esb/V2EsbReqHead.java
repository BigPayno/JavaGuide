package pattern.patterns.report.spi.esb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Head")
public class V2EsbReqHead {
	 @XmlElement(name = "TRAN_CODE")
	    private String tranCode;
	    @XmlElement(name = "SERVICE_ID")
	    private String serviceId;
	    @XmlElement(name = "GLB_SEQ_NO")
	    private String glbSeqNo;
	    @XmlElement(name = "USER_ID")
	    private String userId;
	    @XmlElement(name = "CUSTOMER_ID")
	    private String customerId;
	    @XmlElement(name = "BRANCH_ID")
	    private String branchId;
	    @XmlElement(name = "AUTH_TELLER")
	    private String authTeller;
	    @XmlElement(name = "RECHECK_TELLER")
	    private String recheckTeller;
	    @XmlElement(name = "SOURCE_SYSID")
	    private String sourceSysId;
	    @XmlElement(name = "CHANNEL_ID")
	    private String channelId;
	    @XmlElement(name = "SOURCE_SEQ_NO")
	    private String sourceSeqNo;
	    @XmlElement(name = "SYS_DATE")
	    private String sysDate;
	    @XmlElement(name = "SYS_TIMESTAMP")
	    private String sysTimestamp;
	    @XmlElement(name = "SERVICE_IP")
	    private String serviceIp;
	    @XmlElement(name = "SOURCE_BRANCH_NO")
	    private String sourceBranchNo;
	    @XmlElement(name = "DEST_BRANCH_NO")
	    private String destBranchNo;
	    @XmlElement(name = "MACVAL")
	    private String macVal;
	    @XmlElement(name = "Bak1")
	    private String bak;
	    @XmlElement(name = "Bak2")
	    private String bakTwo;
	    @XmlElement(name = "Bak3")
	    private String bakThree;
	    public static V2EsbReqHead of(String tranCode,String serviceId) {
	    	V2EsbReqHead head=new V2EsbReqHead();
	    	head.setTranCode(tranCode);
	    	head.setServiceId(serviceId);
	    	return head;
	    }
}
