package fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import utils.charset.Charsets;
import utils.json.JSON;
import utils.json.ObjWrapper;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author payno
 * @date 2020/4/30 09:54
 * @description
 */
public class JSONPathTest {

    private static boolean rule001(JSONObject report){
        JSONArray noLoopCreditAccountBoLs=JSON.eval(report,"$.body.noLoopCreditAccountBoLs").get(JSONArray.class);
        JSONArray loopQuotaAccountBoLs=JSON.eval(report,"$.body.loopQuotaAccountBoLs").get(JSONArray.class);
        JSONArray loopCreditAccountBoLs=JSON.eval(report,"$.body.loopCreditAccountBoLs").get(JSONArray.class);
        for(List<Object> boList: Arrays.asList(noLoopCreditAccountBoLs,loopQuotaAccountBoLs,loopCreditAccountBoLs)){
            for (Object bo:boList ){
                Integer lpb =JSON.eval(bo,"$.lateMonthlyPerformanceBo.fiveCateCode")
                        .tryMap(Integer::parseInt).get(Integer.class);
                Integer npb =JSON.eval(bo,"$.newPerformanceBo.fiveCateCode")
                        .tryMap(Integer::parseInt).get(Integer.class);
                if(lpb!=null&&npb!=null&&lpb.intValue()==2&&npb.intValue()==1){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception{
        JSONObject report= JSONObject.parseObject(
                Files.asCharSource(Paths.get("C:\\Users\\DELL\\Documents\\个人征信报告20191226-样例报文.json").toFile(), Charsets.GBK).read());
        System.out.println(JSON.eval(report,"$."));
    }
}
