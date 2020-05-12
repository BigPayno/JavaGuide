package pattern.patterns.report.spi.service.template;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface ISysDateTemplate {
	Date getDate();
	
	String getFormatDate();
	String getFormatDate(long plusDays);
	String getFormatDateTime(Date date);
	String getFormatDateTime();
	String getFormatDateTime(long plusDays);
	
	LocalDateTime getLocalDateTime(Date date);
	LocalDateTime getLocalDateTime();
	LocalDateTime getLocalDateTime(long plusDays);
	
	LocalDate parse(String localDate);
}
