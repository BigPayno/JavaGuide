package poi.util;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.Workbook;
import utils.io.encode.ByteEncoder;
import utils.io.encode.Charsets;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author payno
 * @date 2020/4/30 08:58
 * @description
 *      懒得看了，垃圾
 */
public class ExcelTest {

    public static void main(String[] args) throws Exception{
        List<T> tList = new ArrayList<>();
        for(int i=0;i<5;i++){
            tList.add(new T("t",i));
        }
        List<ExcelHeaderInfo> infos = ImmutableList.of(
                new ExcelHeaderInfo(1,1,0,0,"id"),
                new ExcelHeaderInfo(1,1,1,1,"name")
        );

        Workbook workbook=new ExcelBuilder()
                .setList(tList)
                .setExcelHeaderInfos(infos)
                .getWorkbook();

        File file = Paths.get("d:/aa.xlsx").toFile();
        workbook.write(
                Files.asByteSink(file).openBufferedStream());
        ByteEncoder.encodeFile(file, Charsets.ISO_8859_1,Charsets.UTF_8);
    }
}
