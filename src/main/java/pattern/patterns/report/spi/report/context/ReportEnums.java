package pattern.patterns.report.spi.report.context;

import lombok.AllArgsConstructor;
import lombok.Getter;

public final class ReportEnums {
	@AllArgsConstructor
	@Getter
	public enum ID_TP_CD{
		户口簿("110005"),
		中华人民共和国普通护照("110023"),
		中华人民共和国商务护照("110025"),
		中华人民共和国外交护照("110026"),
		港澳居民来往内地通行证("110019"),
		台湾同胞来往内地通行证("110021"),
		外国人居留证("110029"),
		警官证("110033"),
		香港身份证("110053"),
		澳门身份证("110055"),
		台湾身份证("110057"),
		其他证件("119999"),
		第二代居民身份证("110001"),
		第一代居民身份证("110002"),
		临时居民身份证("110003"),
		军官证("110031"),
		军人士兵证("110037");
		String typeId;
	}
	
	@AllArgsConstructor
	@Getter
	public enum QRY_RSN{
		贷后管理("01"),
		贷款审批("02"),
		信用卡审批("03"),
		异议核查("05"),
		担保资格审查("08"),
		司法调查("09"),
		公积金提取复核查询("16"),
		股指期货开户("18"),
		特约商户实名审查("19"),
		保前审查("20"),
		保后管理("21"),
		法人代表等资信审查("22"),
		客户准入资格审查("23"),
		融资审批("24"),
		资信审查("25"),
		额度审批("26");
		String typeId;
	}
	
	@AllArgsConstructor
	@Getter
	public enum QRY_POLC{
		本地("1"),
		远程("2"),
		本地优先("3");
		String typeId;
	}
	
	@AllArgsConstructor
	@Getter
	public enum RSLT_TP{
		XML结果报文("10"),
		JSON结果报文("20"),
		HTML结果报文("30"),
		PDF结果报文("40"),
		XML和HTML结果报文("50"),
		JSON和HTML结果报文("60");
		String typeId;
	}
}
