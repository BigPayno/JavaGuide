package fastjson;

import com.alibaba.fastjson.JSONPath;
import com.google.common.io.Files;
import utils.charset.Charsets;

import java.nio.file.Paths;

/**
 * @author payno
 * @date 2020/4/30 09:54
 * @description
 */
public class JSONPathTest {
    public static void main(String[] args) throws Exception{
        String report= Files.asCharSource(Paths.get("d:/潍坊征信2.0真实报文.json").toFile(), Charsets.UTF_8).read();
        Object obj=JSONPath.read(report,"$..acctTypeCode");
        System.out.println(obj);
    }
}
