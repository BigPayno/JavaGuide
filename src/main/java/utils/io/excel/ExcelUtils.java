package utils.io.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;

/**
 * @author payno
 * @date 2019/8/8 17:49
 * @description 1.读与写
 * 2.少量与分批次读写
 */
public class ExcelUtils {
    /**
     * readExcel
     *
     * @param filePath 文件路径
     * @param tClass   读取成的实体类
     * @param start    起始行{表头占几行}
     * @author: payno
     * @time: 2019/8/8 18:13
     * @description:
     * @return: java.util.List<T>
     */
    public static <T extends BaseRowModel> List<T> readExcel(String filePath, Class<T> tClass, int start) {
        File file = new File(filePath);
        ExcelVoEventListener<T> excelVoEventListener = new ExcelVoEventListener<>();
        try (InputStream in = new BufferedInputStream(FileUtils.openInputStream(file))) {
            ExcelReader excelReader = new ExcelReader(in, null, excelVoEventListener);
            Sheet sheet = new Sheet(1, start, tClass);
            excelReader.read(sheet);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return excelVoEventListener.getTList();
    }

    /**
     * writeExcel
     *
     * @param filePath 文件路径可以不存在，会自动辨别
     * @param tClass   要写入的类型
     * @param tList    要写入的list
     * @param start    起始行
     * @author: payno
     * @time: 2019/8/8 18:14
     * @description:
     * @return: void
     */
    public static <T extends BaseRowModel> void writeExcel(String filePath, Class<T> tClass, List<T> tList, int start) {
        File file = new File(filePath);
        try (OutputStream out = new BufferedOutputStream(FileUtils.openOutputStream(file))) {
            ExcelWriter excelWriter = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            Sheet sheet = new Sheet(1, start, tClass);
            excelWriter.write(tList, sheet);
            excelWriter.finish();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * readExcel
     *
     * @param filePath
     * @param start
     * @author: payno
     * @time: 2019/8/14 10:24
     * @description:
     * @return: java.util.List<java.lang.Object>
     */
    public static List<Object> readExcel(String filePath, int start) {
        File file = new File(filePath);
        ExcelObjectEventListener excelObjectEventListener = new ExcelObjectEventListener();
        try (InputStream in = new BufferedInputStream(FileUtils.openInputStream(file))) {
            ExcelReader excelReader = new ExcelReader(in, null, excelObjectEventListener);
            Sheet sheet = new Sheet(1, start);
            excelReader.read(sheet);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return excelObjectEventListener.getRows();
    }

    /**
     * writerExcel
     *
     * @param filePath
     * @param start
     * @param rowList
     * @author: payno
     * @time: 2019/8/14 10:08
     * @description:
     * @return: void
     */
    public static void writerExcel(String filePath, int start, List<List<String>> rowList) {
        File file = new File(filePath);
        try (OutputStream out = new BufferedOutputStream(FileUtils.openOutputStream(file))) {
            ExcelWriter excelWriter = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            Sheet sheet = new Sheet(1, start);
            excelWriter.write0(rowList, sheet);
            excelWriter.finish();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
