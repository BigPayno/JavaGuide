package utils.io.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author payno
 * @date 2019/8/14 09:42
 * @description
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ExcelObjectEventListener extends AnalysisEventListener<Object> {
    private List<Object> rows = new ArrayList<>();

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        rows.add(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.err.println("doAfterAllAnalysed...");
    }
}
