package poi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author payno
 * @date 2020/4/29 17:03
 * @description
 */
@Getter
@AllArgsConstructor
public enum ExcelFormat {
    FORMAT_INTEGER("INTEGER"),
    FORMAT_DOUBLE("DOUBLE"),
    FORMAT_PERCENT("PERCENT"),
    FORMAT_DATE("DATE");

    private String value;
}
