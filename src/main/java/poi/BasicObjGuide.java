package poi;

import org.apache.poi.hssf.usermodel.*;

/**
 * @author payno
 * @date 2020/4/29 16:43
 * @description
 */
public class BasicObjGuide {
    public static void main(String[] args) {
        HSSFWorkbook excel = new HSSFWorkbook();
        HSSFSheet sheet = excel.createSheet("Sheet");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        HSSFCellStyle cellStyle = excel.createCellStyle();
        HSSFDataFormat dataFormat = excel.createDataFormat();
    }
}
