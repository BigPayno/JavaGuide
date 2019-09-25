package utils.io.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author payno
 * @date 2019/8/7 20:45
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExcelVoEventListener<T extends BaseRowModel> extends AnalysisEventListener<T> {
    private List<T> tList = new ArrayList<>();

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        tList.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.err.println("doAfterAllAnalysed...");
    }
}
