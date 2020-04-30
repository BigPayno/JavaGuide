package poi;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
public class Test {

    public static void main(String[] args) throws IOException {
        File from = new File("D:\\350 all parameters.par.txt");
        File to = new File("D:\\350 all parameters.par.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet xsSheet = wb.createSheet("350 all parameters");
        List<String> lines = Files.asCharSource(from, Charsets.UTF_8).readLines();
        for (int i =0;i<lines.size();i++){
            XSSFRow xsRow1 = xsSheet.createRow(i);
            String[] s= lines.get(i).split("\t");
            for (int j=0; j<s.length; i++) {
                XSSFCell xsCell = xsRow1.createCell(j);
                // 只取数字或者字母
                xsCell.setCellValue(CharMatcher.javaLetterOrDigit().retainFrom(s[j]));
            }
        }
        // 1.7 try with 语句
        try (OutputStream os =   Files.asByteSink(to).openBufferedStream()){
            wb.write(os);
        }
    }
}
