package utils.io.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResourceType {
	 WAR_RESOURCE("get resource from war dir"),
	 JAR_RESOURCE("get resource from jar"),
	 FILE_RESOURCE("get resource from nio");
	 private String description;
}
