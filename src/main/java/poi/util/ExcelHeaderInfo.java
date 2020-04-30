package poi.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author payno
 * @date 2020/4/29 17:05
 * @description
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ExcelHeaderInfo {
    /**
     * 标题的首行坐标
     */
    private int firstRow;
    /**
     * 标题的末行坐标
     */
    private int lastRow;
    /**
     * 标题的首列坐标
     */
    private int firstCol;
    /**
     * 标题的末列坐标
     */
    private int lastCol;
    /**
     * 标题
     */
    private String title;
}