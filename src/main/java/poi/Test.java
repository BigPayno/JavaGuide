package poi;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) throws IOException {
        File from = new File("D:\\350 all parameters.par.txt");
        File to = new File("D:\\350 all parameters.par.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet xsSheet = wb.createSheet("350 all parameters");
        // GUAVA多了一行空的
        List<String> lines = Files.asCharSource(from, Charsets.UTF_8).readLines().stream()
                .filter(line->!Strings.isNullOrEmpty(CharMatcher.javaIsoControl().removeFrom(line)))
                .collect(Collectors.toList());
        //List<String> lines = java.nio.file.Files.readAllLines(JsonPaths.get("D:\\350 all parameters.par.txt\""),Charsets.UTF_8);
        for (int i =0;i<lines.size();i++){
            XSSFRow xsRow1 = xsSheet.createRow(i);
            String[] s= lines.get(i).split("\t");
            for (int j=0; j<s.length; j++) {
                XSSFCell xsCell = xsRow1.createCell(j);
                // 只取数字或者字母
                xsCell.setCellValue(i==0?
                        CharMatcher.javaIsoControl().removeFrom(s[j]).substring(2):
                        CharMatcher.javaIsoControl().removeFrom(s[j])
                );
            }
        }
        // 1.7 try with 语句
        try (OutputStream os =   Files.asByteSink(to).openBufferedStream()){
            wb.write(os);
        }
    }
}
