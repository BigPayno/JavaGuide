package jsonpath;


import com.google.common.io.Files;
import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import utils.charset.Charsets;

import java.nio.file.Paths;
import java.util.List;

/**
 * @author payno
 * @date 2020/5/7 09:47
 * @description
 *
 * JsonSmartJsonProvider (default)
 * JacksonJsonProvider
 * JacksonJsonNodeJsonProvider
 * GsonJsonProvider
 * JsonOrgJsonProvider
 */
public class JsonConfigGuide {

    private static int parse(String val){
        return val==null?-1:Integer.parseInt(val);
    }

    public static void main(String[] args) throws Exception{
        String json = Files.asCharSource(
                Paths.get("C:\\Users\\DELL\\Documents\\个人征信报告20191226-样例报文.json").toFile(), Charsets.GBK).read();
        Configuration pathCfg=Configuration.defaultConfiguration()
                //  返回Path而非Value，可以结合..实现更复杂的功能
                .addOptions(Option.AS_PATH_LIST).jsonProvider(new GsonJsonProvider()).mappingProvider(new GsonMappingProvider());
        Configuration cfg =Configuration.defaultConfiguration()
                // 如果Path指向的Value不存在，返回null而非抛出错误
                .addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL).jsonProvider(new GsonJsonProvider()).mappingProvider(new GsonMappingProvider());
        DocumentContext pathContext = JsonPath.using(pathCfg).parse(json);
        DocumentContext context = JsonPath.using(cfg).parse(json);


        boolean hit = false;
        List<String> paths=pathContext.read("$..creditBaseInfoBo[?(@.acctTypeCode in ['D1','R1','R4'])]",new TypeRef<List<String>>(){});
        for(String path:paths){
            int npb=parse(
                    context.read(JsonPaths.parent(path)+".newPerformanceBo.fiveCateCode",String.class));
            int lpb=parse(
                    context.read(JsonPaths.parent(path)+".lateMonthlyPerformanceBo.fiveCateCode",String.class));
            if(npb==1&&lpb==2){
                hit = true;
            }
        }
        if(hit){
            System.err.println("Hit");
        }
    }
}
