package pattern.patterns.report.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisTaskType {
	LIMIT_APPLY("riskTask:::limitApply"),
	LIMIT_APPLY_CALLBACK("riskTask:::limitApply:::callback"),
	WARNING_APPLY_SEASON("riskTask:::warningApply:::season");
	String redisZSetKey;
}
