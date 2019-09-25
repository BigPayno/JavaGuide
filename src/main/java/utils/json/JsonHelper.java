package utils.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import utils.charset.Charsets;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author payno
 * @date 2019/9/4 16:46
 * @description
 */
public class JsonHelper {
    public static Float readFloat(String gjjData,String path){
        String record=readString(gjjData, path);
        return (record==null)?null:Float.valueOf(record);
    }
    public static Integer readInt(String gjjData,String path){
        String record=readString(gjjData, path);
        return (record==null)?null:Integer.valueOf(record);
    }
    public static BigDecimal readNumber(String gjjData,String path){
        String record=readString(gjjData, path);
        return (record==null)?null:BigDecimal.valueOf(Long.valueOf(record));
    }
    public static String readString(String gjjData,String path){
        //如果没有，返回null
        return (String) JSONPath.read(gjjData,path);
    }
    public static List<String> readList(String gjjData,String path){
        return (List<String>) JSONPath.read(gjjData,path);
    }
    public static boolean isBlankOrNull(String string){
        if ((string==null)||(StringUtils.isBlank(string))){
            return true;
        }
        return false;
    }
    public static int countNotBlankOrNull(List<String> list){
        //返回list中有效数据的个数
        return (int)list.stream().filter(s -> !isBlankOrNull(s)).count();
    }
}
